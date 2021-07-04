package com.wolt.restaurantlisting.service.impl

import com.wolt.restaurantlisting.config.RestaurantListConfig
import com.wolt.restaurantlisting.dto.RestaurantDto
import com.wolt.restaurantlisting.dto.response.DiscoveryRestaurantDto
import com.wolt.restaurantlisting.dto.response.DiscoverySectionDto
import com.wolt.restaurantlisting.service.DiscoveryService
import com.wolt.restaurantlisting.service.RestaurantService
import com.wolt.restaurantlisting.utils.FileUtil
import com.wolt.restaurantlisting.utils.GeoLocationUtil
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class DiscoveryServiceImpl(
    val restaurantService: RestaurantService,
    val listingConfig: RestaurantListConfig
) : DiscoveryService {
    // Rules
    // 1. First get all 1.5 Km near restaurants
    // 2. then get all restaurants in each section sub category (near, new, close)
    // 3. then order by the online desc (true > false) for each sub section
    // 4. why?? online sort is last because we need to get best results with distance later final collection will apply auto
    // sort online first then offline
    override fun findAllNearByRestaurantsByWithInMeters(longitude: Double, latitude: Double): DiscoveryRestaurantDto {
        var restaurants = FileUtil.getRestaurantsFromResources()

        // this is basically filtered by very first rules within 1.5 km
        var nearByRestaurants = getAllNearByRestaurants(longitude,latitude, restaurants)

        var popularityRestaurants = nearByRestaurants.sortedWith(
            compareByDescending<RestaurantDto> { it.popularity }
        ).sortedByDescending { it.online }

        var newRestaurants = nearByRestaurants.filter { it.launch_date >= java.sql.Date.valueOf(LocalDateTime.now().minusMonths(4).toLocalDate()) }
            .sortedByDescending { it.launch_date }
            .sortedByDescending { it.online }

        var closerResaurants = nearByRestaurants
            .sortedBy { it.calculated_distance }
            .sortedByDescending { it.online }

        var finalRestaurantSections = DiscoveryRestaurantDto(
            sections = mutableListOf()
        )

        if(popularityRestaurants.any()){
            finalRestaurantSections.sections?.add(
                DiscoverySectionDto(
                    title = "Popular Restaurants",
                    restaurants =popularityRestaurants.take(10)
                )
            )
        }

        if(newRestaurants.any()){
            finalRestaurantSections.sections?.add(
                DiscoverySectionDto(
                    title = "New Restaurants",
                    restaurants =newRestaurants.take(10)
                )
            )
        }

        if(closerResaurants.any()){
            finalRestaurantSections.sections?.add(
                DiscoverySectionDto(
                    title = "Nearby Restaurants",
                    restaurants =closerResaurants.take(10)
                )
            )
        }


        return finalRestaurantSections

    }

    /*
    Rules:
    1. Get All Near by restaurants within 1.5 KM
     */
    private fun getAllNearByRestaurants(longitude: Double, latitude: Double, sourceRestaurants: List<RestaurantDto>)
            : List<RestaurantDto> {

        // calculate distance first and apply it on dto
        sourceRestaurants
            .map {
                it.calculated_distance = GeoLocationUtil.distance(it.location[1], it.location[0], latitude, longitude)
            }.toList()

        // then filter by distance column with distance_threshold
        return sourceRestaurants.filter {
            it.calculated_distance < listingConfig.distance_threshold
        }
    }

}