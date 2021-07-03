package com.wolt.restaurantlisting.controllers

import com.wolt.restaurantlisting.dto.RestaurantDto
import com.wolt.restaurantlisting.dto.response.RestaurantList
import com.wolt.restaurantlisting.service.RestaurantService
import io.swagger.v3.oas.annotations.Operation
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/restaurants")
class RestaurantController(
    private val restaurantService: RestaurantService
) {
    var logger: Logger = LoggerFactory.getLogger(RestaurantController::class.java)

    @PostMapping
    @Operation(description = "Ingest Restaurants in Bulk with specified Model", tags = ["Restaurants"],
        summary = "Bulk Ingest Restaurants")
    fun ingestRestaurants(@RequestBody restaurantsData:RestaurantList):ResponseEntity<Boolean> {

        restaurantService.ingestRestaurants(
            restaurantList = restaurantsData
        )

        return ResponseEntity<Boolean>(
            true,HttpStatus.OK
        )
    }

    @GetMapping
    @Operation(description = "Get All Restaurants in the Wolt", tags = ["Restaurants"],
        summary = "Get All Restaurants")
    fun getAllRestaurants(): ResponseEntity<RestaurantList>{
        val restaurants = restaurantService.getAllRestaurants()

        return ResponseEntity<RestaurantList>(
            RestaurantList(
                restaurants
            ), HttpStatus.OK
        )
    }
}