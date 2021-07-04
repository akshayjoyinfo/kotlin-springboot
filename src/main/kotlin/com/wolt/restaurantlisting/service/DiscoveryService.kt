package com.wolt.restaurantlisting.service

import com.wolt.restaurantlisting.dto.response.DiscoveryRestaurantDto

interface DiscoveryService {

    fun findAllNearByRestaurantsByWithInMeters(
        longitude: Double?,
        latitude: Double?
    ): DiscoveryRestaurantDto
}