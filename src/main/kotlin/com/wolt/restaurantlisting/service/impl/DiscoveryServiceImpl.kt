package com.wolt.restaurantlisting.service.impl

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.wolt.restaurantlisting.dto.response.DiscoveryRestaurantDto
import com.wolt.restaurantlisting.dto.response.DiscoverySectionDto
import com.wolt.restaurantlisting.service.DiscoveryService
import com.wolt.restaurantlisting.service.RestaurantService
import com.wolt.restaurantlisting.utils.FileUtil
import io.swagger.v3.oas.integration.StringOpenApiConfigurationLoader.LOGGER
import org.springframework.core.io.ClassPathResource
import org.springframework.core.io.Resource
import org.springframework.stereotype.Service
import org.springframework.util.FileCopyUtils
import java.io.IOException
import java.io.InputStream
import java.nio.charset.StandardCharsets

@Service
class DiscoveryServiceImpl(
    val restaurantService: RestaurantService
) : DiscoveryService {
    override fun findAllNearByRestaurantsByWithInMeters(longitude: Double?, latitude: Double?): DiscoveryRestaurantDto {
        var restaurants = FileUtil.getRestaurantsFromResources();
        return DiscoveryRestaurantDto(
            sections = mutableListOf(
                DiscoverySectionDto(
                    title = "Test Works",
                    restaurants=restaurants
                )
            )
        )

    }

}