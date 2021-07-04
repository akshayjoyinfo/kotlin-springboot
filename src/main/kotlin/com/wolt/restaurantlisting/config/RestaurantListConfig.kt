package com.wolt.restaurantlisting.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration


@Configuration()
class RestaurantListConfig(
    @Value("\${wolt.restaurant.listing.distance}")val distance_threshold: Double
) {
}