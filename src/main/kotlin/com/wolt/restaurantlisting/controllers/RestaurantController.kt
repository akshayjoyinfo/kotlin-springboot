package com.wolt.restaurantlisting.controllers

import com.wolt.restaurantlisting.dto.RestaurantDto
import com.wolt.restaurantlisting.dto.response.RestaurantList
import com.wolt.restaurantlisting.service.RestaurantService
import io.swagger.v3.oas.annotations.Operation
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.Valid
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@RestController
@Validated
@RequestMapping("/api/v1/restaurants")
class RestaurantController(
    private val restaurantService: RestaurantService
) {
    var logger: Logger = LoggerFactory.getLogger(RestaurantController::class.java)


    @GetMapping
    @Operation(description = "Get All Restaurants in the Wolt, used default endpoint here" +
            " https://raw.githubusercontent.com/woltapp/summer2021-internship/main/restaurants.json ", tags = ["Restaurants"],
        summary = "Get All Restaurants")
    fun getAllRestaurants(@RequestParam(defaultValue = "https://raw.githubusercontent.com/woltapp/summer2021-internship/main/restaurants.json") @Valid @NotBlank @NotNull @NotEmpty restaurant_json_url: String
    ): ResponseEntity<RestaurantList>{
        val restaurants = restaurantService.getAllRestaurants(restaurant_json_url)

        return ResponseEntity<RestaurantList>(
            restaurants, HttpStatus.OK
        )
    }
}