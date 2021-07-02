package com.wolt.restaurantlisting.controllers

import com.wolt.restaurantlisting.dto.HealthStatus
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/health")

class HealthCheckController {

    @GetMapping
    @Operation(description = "HealthCheck Endpoint for Restaurant Listing API", tags = arrayOf("Health"),
                summary = "HealthCheck Endpoint")
    fun getHealthCheck():ResponseEntity<HealthStatus>{
        return ResponseEntity<HealthStatus>(
            HealthStatus("Healthy"), HttpStatus.OK
        )
    }
}