package com.droid.silverbullet.common.model.data

import com.droid.silverbullet.common.Constant
import com.droid.silverbullet.common.model.entity.DailyWeatherEntity
import kotlin.math.round

/**
 * Project OWeather.
 *
 * Created by Rhony Abdullah Siagian on 2019-11-23.
 */
data class DailyWeatherData(
    val weeklyWeatherList: List<WeeklyWeather>
) {
    data class WeeklyWeather(
        val dayOfWeek: String,
        val temperature: Int,
        val cloudType: String
    )

    companion object {
        fun parseEntity(entities: List<DailyWeatherEntity>): DailyWeatherData {
            val weeklyList = entities.map { entity ->
                WeeklyWeather(
                    dayOfWeek = entity.day.orEmpty(),
                    temperature = round(entity.main?.temp ?: 0.0).toInt(),
                    cloudType = entity.weather.orEmpty().lastOrNull()?.main ?: Constant.WEATHER_TYPE_CLEAR
                )
            }
            return DailyWeatherData(weeklyList)
        }
    }
}
