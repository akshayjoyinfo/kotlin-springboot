package com.wolt.restaurantlisting.controllers

import com.wolt.restaurantlisting.dto.response.DiscoveryRestaurantDto
import com.wolt.restaurantlisting.service.DiscoveryService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid
import javax.validation.constraints.Max
import javax.validation.constraints.Min

@RestController
@RequestMapping("/api/v1/discovery")
@Validated
class DiscoveryController(
    private val discoveryService: DiscoveryService
) {
    @GetMapping
    @Operation(description = "Discover restaurants near by within your 1.5 KM radius", tags = ["Discovery"],
        summary = "Get All Restaurants")
    fun discoverRestaurants(@RequestParam("lat") @Valid @Max(90) @Min(-90) lat: Double,
                            @RequestParam("lon") @Valid @Max(180) @Min(-180) lon: Double)
            : ResponseEntity<DiscoveryRestaurantDto> {
        return ResponseEntity<DiscoveryRestaurantDto>(
            discoveryService.findAllNearByRestaurantsByWithInMeters(lon, lat), HttpStatus.OK
        )
    }
}