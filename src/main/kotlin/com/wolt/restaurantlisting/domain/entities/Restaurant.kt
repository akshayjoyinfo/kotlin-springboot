package com.wolt.restaurantlisting.domain.entities

import com.wolt.restaurantlisting.dto.RestaurantDto
import java.util.*
import javax.persistence.*

@Entity
@Table(name="restaurants")
class Restaurant (
    @Id
    @SequenceGenerator(
        name = "restaurant_id_seq",
        sequenceName = "restaurant_id_seq",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "restaurant_id_seq"
    )
    var id: Int?=null,
    var blurhash: String,
    var launch_date: Date,
    var longitude: Double,
    var latitude : Double,
    var name: String,
    var online: Boolean,
    var popularity: Double
){

    fun toDto(): RestaurantDto = RestaurantDto(
        blurhash = blurhash,
        launch_date = launch_date,
        location = listOf(longitude, latitude),
        name = name,
        online = online,
        popularity = popularity
    )


    companion object {

        fun toEntity(restaurantDto: RestaurantDto) = Restaurant(
            blurhash = restaurantDto.blurhash,
            launch_date = restaurantDto.launch_date,
            longitude = restaurantDto.location[0],
            latitude = restaurantDto.location[1],
            name = restaurantDto.name,
            online = restaurantDto.online,
            popularity = restaurantDto.popularity
        )
    }
}