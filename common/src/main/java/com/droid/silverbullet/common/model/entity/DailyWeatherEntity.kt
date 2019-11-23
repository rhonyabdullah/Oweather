package com.droid.silverbullet.common.model.entity

data class DailyWeatherEntity(
    val day: String? = null,
    val main: Main? = null,
    val weather: List<Weather?>? = null
) {
    data class Main(
        val grnd_level: Int? = null,
        val humidity: Int? = null,
        val pressure: Int? = null,
        val sea_level: Int? = null,
        val temp: Double? = null,
        val temp_max: Double? = null,
        val temp_min: Double? = null
    )

    data class Weather(
        val description: String? = null,
        val icon: String? = null,
        val id: Int? = null,
        val main: String? = null
    )
}