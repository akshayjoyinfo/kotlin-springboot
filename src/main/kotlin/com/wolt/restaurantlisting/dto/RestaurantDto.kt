package com.wolt.restaurantlisting.dto

import com.fasterxml.jackson.annotation.JsonIgnore
import java.util.*

class RestaurantDto(
    var blurhash: String,
    var launch_date: Date,
    var location: List<Double>,
    var name: String,
    var online: Boolean,
    var popularity: Double,

    // do not want to expose this over the http reponse
    @JsonIgnore
    var calculated_distance:Double
) {
}