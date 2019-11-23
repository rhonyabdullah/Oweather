package com.droid.silverbullet.common.model.data

import com.droid.silverbullet.common.Constant
import com.droid.silverbullet.common.model.entity.CurrentWeatherHourlyEntity
import kotlin.math.round

/**
 * Project OWeather.
 *
 * Created by Rhony Abdullah Siagian on 2019-11-23.
 */
data class CurrentWeatherHourlyData(
    val weatherInHourList: List<WeatherInHour>
) {

    data class WeatherInHour(
        val timeInHour: String,
        val temperature: Int,
        val cloudType: String
    )

    companion object {
        fun parseEntity(entities: List<CurrentWeatherHourlyEntity>): CurrentWeatherHourlyData {
            val weatherList = entities.map { entity ->
                WeatherInHour(
                    timeInHour = entity.time.orEmpty(),
                    temperature = round(entity.main?.temp ?: 0.0).toInt(),
                    cloudType = entity.weather.orEmpty().lastOrNull()?.main ?: Constant.WEATHER_TYPE_CLEAR
                )
            }
            return CurrentWeatherHourlyData(weatherList)
        }
    }
}
