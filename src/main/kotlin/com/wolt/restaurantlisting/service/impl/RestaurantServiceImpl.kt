package com.wolt.restaurantlisting.service.impl

import com.wolt.restaurantlisting.domain.entities.Restaurant
import com.wolt.restaurantlisting.dto.response.RestaurantList
import com.wolt.restaurantlisting.repository.RestaurantRepository
import com.wolt.restaurantlisting.service.RestaurantService
import org.springframework.stereotype.Service

@Service
class RestaurantServiceImpl(
    private val restaurantRepository: RestaurantRepository,
) : RestaurantService {

    override fun ingestRestaurants(restaurantList: RestaurantList): Boolean {
        restaurantRepository.deleteAll()
        val restaurantEntities = restaurantList.restaurants
            .map {
                rest -> Restaurant.toEntity(rest)
            }

        try {
            restaurantRepository.saveAll(restaurantEntities)
        }catch (ex:Exception){
            return false
        }
        return true
    }
}