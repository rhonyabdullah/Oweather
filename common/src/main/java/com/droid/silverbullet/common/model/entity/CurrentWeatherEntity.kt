package com.droid.silverbullet.common.model.entity

/**
 * Project OWeather.
 *
 * Created by Rhony Abdullah Siagian on 2019-11-21.
 */
data class CurrentWeatherEntity(
    val coord: Coord? = null,
    val weather: List<Weather?>? = null,
    val base: String? = null,
    val main: Main? = null,
    val visibility: Int? = null,
    val wind: Wind? = null,
    val clouds: Clouds? = null,
    val dt: Int? = null,
    val sys: Sys? = null,
    val timezone: Int? = null,
    val id: Int? = null,
    val name: String? = null,
    val cod: Int? = null
) {
    data class Coord(
        val lon: Double? = null,
        val lat: Double? = null
    )

    data class Weather(
        val id: Int? = null,
        val main: String? = null,
        val description: String? = null,
        val icon: String? = null
    )

    data class Main(
        val temp: Double? = null,
        val pressure: Int? = null,
        val humidity: Int? = null,
        val temp_min: Double? = null,
        val temp_max: Int? = null
    )

    data class Wind(
        val speed: Double? = null,
        val deg: Int? = null
    )

    data class Clouds(
        val all: Int? = null
    )

    data class Sys(
        val type: Int? = null,
        val id: Int? = null,
        val country: String? = null,
        val sunrise: Int? = null,
        val sunset: Int? = null
    )
}
