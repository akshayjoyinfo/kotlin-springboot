package com.wolt.restaurantlisting.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get


@SpringBootTest
@AutoConfigureMockMvc
internal class RestaurantControllerTest @Autowired constructor(
    val mockMvc: MockMvc,
    val objectMapper: ObjectMapper
) {

    @Nested
    @DisplayName("GET /api/v1/restaurants")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class GetRestaurants {

        @Test
        fun `should GET 200 Ok for all restaurants from JSON without url supplied`(){
            mockMvc.get("/api/v1/restaurants")
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$.restaurants.length()") { value(100) }
            }

        }

        @Test
        fun `should GET 200 Ok for all restaurants with valid URL json supplied`(){
            mockMvc.get("/api/v1/restaurants?restaurant_json_url=https://raw.githubusercontent.com/woltapp/summer2021-internship/main/restaurants.json")
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$.restaurants.length()") { value(100) }
            }
        }
    }
}