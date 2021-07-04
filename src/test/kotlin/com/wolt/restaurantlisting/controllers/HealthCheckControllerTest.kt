package com.wolt.restaurantlisting.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@SpringBootTest
@AutoConfigureMockMvc
internal class HealthCheckControllerTest @Autowired constructor(
    val mockMvc :MockMvc
){

    @Nested
    @DisplayName("GET /health")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class GetHealthCheck {
        
        @Test
        fun `should return Health Endpoint 200 Ok`(){

            mockMvc.get("/health")
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$.status") { value("Healthy") }
            }
        }

        @Test
        fun `should return 404 for invalid health endpoint 'healh'`(){

            mockMvc.get("/healh")
                .andDo { print() }
                .andExpect {
                    status { isNotFound() }
                }
        }
    }
}