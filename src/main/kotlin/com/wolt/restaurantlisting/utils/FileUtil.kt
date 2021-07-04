package com.wolt.restaurantlisting.utils

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.wolt.restaurantlisting.dto.RestaurantDto
import com.wolt.restaurantlisting.dto.response.RestaurantList
import org.springframework.core.io.ClassPathResource
import org.springframework.core.io.Resource
import org.springframework.util.FileCopyUtils
import java.io.InputStream
import java.nio.charset.StandardCharsets

class FileUtil {
    companion object{
        fun getRestaurantsFromResources(): List<RestaurantDto>{
            val resource: Resource = ClassPathResource("static/restaurants.json")
            val inputStream: InputStream = resource.getInputStream()

            val bdata = FileCopyUtils.copyToByteArray(inputStream)
            val data = String(bdata, StandardCharsets.UTF_8)

            val mapper = jacksonObjectMapper()
            var restaurantList = mapper.readValue<RestaurantList>(data)
            return restaurantList.restaurants
        }
    }
}