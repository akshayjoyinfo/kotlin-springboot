package com.wolt.restaurantlisting.utils

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class GeoLocationUtilTest{

    @Test
    fun `should calculate distance between two same points should be zero`(){

        var distance = GeoLocationUtil.distance(60.17137,24.937382,60.17137,24.937382)
        assertThat(distance).isEqualTo(0.0)
    }

    //distance calculated with https://www.geodatasource.com/distance-calculator
    @Test
    fun `should calculate distance between two geo location points`(){

        var distance = GeoLocationUtil.distance(64.15,24.26,64.16,24.27)
        assertThat(distance).isEqualTo(1.2129564887416049)
    }
}