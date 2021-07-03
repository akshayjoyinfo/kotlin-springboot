package com.wolt.restaurantlisting.service

import com.wolt.restaurantlisting.dto.response.RestaurantList

interface RestaurantService {
    fun ingestRestaurants(restaurantList: RestaurantList):Boolean;

}