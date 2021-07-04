package com.wolt.restaurantlisting

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.web.client.RestTemplate

@SpringBootApplication
@OpenAPIDefinition(info = Info(title = "Wolt Restaurant Listing API", version = "1.0",
    description = "Restaurant Listing APIs"))
class RestaurantListingApplication{
    @Bean
    fun restTemplate(builder: RestTemplateBuilder): RestTemplate = builder.build()
}

fun main(args: Array<String>) {
    runApplication<RestaurantListingApplication>(*args)
}
