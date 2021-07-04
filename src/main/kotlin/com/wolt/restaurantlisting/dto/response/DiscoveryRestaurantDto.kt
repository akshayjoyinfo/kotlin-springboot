package com.wolt.restaurantlisting.dto.response

import com.wolt.restaurantlisting.dto.RestaurantDto


data class DiscoverySectionDto (
    var title: String,
    var restaurants: List<RestaurantDto>?=null
)

data class DiscoveryRestaurantDto(
    var sections: MutableList<DiscoverySectionDto>?=null
)