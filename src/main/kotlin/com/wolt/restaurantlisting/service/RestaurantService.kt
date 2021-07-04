package com.wolt.restaurantlisting.service

import com.wolt.restaurantlisting.dto.RestaurantDto
import com.wolt.restaurantlisting.dto.response.RestaurantList

interface RestaurantService {

    fun getAllRestaurants(restaurant_json_url: String): RestaurantList

}