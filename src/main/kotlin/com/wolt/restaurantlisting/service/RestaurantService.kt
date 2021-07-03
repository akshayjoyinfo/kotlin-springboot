package com.wolt.restaurantlisting.service

import com.wolt.restaurantlisting.dto.RestaurantDto
import com.wolt.restaurantlisting.dto.response.RestaurantList

interface RestaurantService {

    fun ingestRestaurants(restaurantList: RestaurantList):Boolean

    fun getAllRestaurants(): List<RestaurantDto>

}