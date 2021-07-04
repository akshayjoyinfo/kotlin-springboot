package com.wolt.restaurantlisting.service.impl

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.wolt.restaurantlisting.dto.response.RestaurantList
import com.wolt.restaurantlisting.service.RestaurantService
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject

@Service
class RestaurantServiceImpl(
    private val restTemplate: RestTemplate
) : RestaurantService {

    //url pointing to Current github Raw Content
    override fun getAllRestaurants(restaurant_json_url: String): RestaurantList {
        val mapper = jacksonObjectMapper()
        val responseContent: String =
            restTemplate.getForObject(restaurant_json_url)

        return mapper
            .readValue(responseContent)
    }
}