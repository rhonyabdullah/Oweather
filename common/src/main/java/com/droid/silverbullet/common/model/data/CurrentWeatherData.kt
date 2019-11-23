package com.droid.silverbullet.common.model.data

import com.droid.silverbullet.common.Constant
import com.droid.silverbullet.common.DateParser
import com.droid.silverbullet.common.extensions.toDate
import com.droid.silverbullet.common.model.entity.CurrentWeatherEntity
import kotlin.math.round

/**
 * Project OWeather.
 *
 * Created by Rhony Abdullah Siagian on 2019-11-21.
 */
data class CurrentWeatherData(
    val city: String,
    val temperature: Int,
    val day: String,
    val date: String,
    val cloudType: String,
    val cloudDesc: String
) {
    companion object {
        fun parseEntity(entity: CurrentWeatherEntity): CurrentWeatherData {
            val date = (entity.dt ?: System.currentTimeMillis()).toDate()
            return CurrentWeatherData(
                city = entity.name.orEmpty(),
                temperature = round(entity.main?.temp ?: 0.0).toInt(),
                day = DateParser.getDayOfWeek(date),
                date = DateParser.getDdMMMMyyyy(date),
                cloudType = entity.weather.orEmpty().lastOrNull()?.main ?: Constant.WEATHER_TYPE_CLEAR,
                cloudDesc = entity.weather.orEmpty().lastOrNull()?.description.orEmpty()
            )
        }
    }
}
