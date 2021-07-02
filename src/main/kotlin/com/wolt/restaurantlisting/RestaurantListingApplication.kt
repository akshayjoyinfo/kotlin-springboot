package com.wolt.restaurantlisting

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
@OpenAPIDefinition(info = Info(title = "Wolt Restuarant Listing API", version = "1.0",
    description = "Restaurant Listing APIs"))
class RestaurantListingApplication

fun main(args: Array<String>) {
    runApplication<RestaurantListingApplication>(*args)
}
