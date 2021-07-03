package com.wolt.restaurantlisting.repository

import com.wolt.restaurantlisting.domain.entities.Restaurant
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RestaurantRepository : JpaRepository<Restaurant, Int> {
}