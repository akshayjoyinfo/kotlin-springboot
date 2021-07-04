package com.wolt.restaurantlisting.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Assertions.*
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

internal class DiscoveryControllerTest @Autowired constructor(
    val mockMvc: MockMvc,
    val objectMapper: ObjectMapper){

    @Nested
    @DisplayName("GET /api/v1/discovery")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class DiscoveryControllerTest {

        @Test
        fun `should Get 200 Ok for Valid Latitude & Longitude`(){
            mockMvc.get("/api/v1/discovery?lat=20&lon=20")
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$.sections.length()") { value(0) }
                }
        }

        @Test
        fun `should Get 200 Ok and Restaurants Sections for NearBy and Popular Restaurants`(){
            mockMvc.get("/api/v1/discovery?lat=60.1709&lon=24.941")
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$.sections.length()") { value(2) }
                    jsonPath("$.sections[0].restaurants.length()") { value(10) }
                    jsonPath("$.sections[1].restaurants.length()") { value(10) }
                }
        }

        @Test
        fun `should Get 400 BAD_REQUEST for Invalid Lat and Lon`(){
            mockMvc.get("/api/v1/discovery?lat=2323232&lon=4534543")
                .andDo { print() }
                .andExpect {
                    status { isBadRequest() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                }
        }

        @Test
        fun `should Get 400 BAD_REQUEST for random inputs with String values for Lat & Lon`(){
            mockMvc.get("/api/v1/discovery?lat=asd&lon=dsgsgsd")
                .andDo { print() }
                .andExpect {
                    status { isBadRequest() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                }
        }
    }
}