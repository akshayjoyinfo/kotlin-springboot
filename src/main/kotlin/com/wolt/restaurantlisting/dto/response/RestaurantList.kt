package com.wolt.restaurantlisting.dto.response


import com.wolt.restaurantlisting.dto.RestaurantDto

data class RestaurantList(
    val restaurants : List<RestaurantDto>
)