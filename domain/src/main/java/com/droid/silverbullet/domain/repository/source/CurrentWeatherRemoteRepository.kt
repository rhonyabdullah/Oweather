package com.droid.silverbullet.domain.repository.source

import com.droid.silverbullet.common.model.entity.CurrentWeatherEntity
import com.droid.silverbullet.common.model.entity.CurrentWeatherHourlyEntity
import com.droid.silverbullet.common.model.entity.DailyWeatherEntity

/**
 * Project OWeather.
 *
 * Created by Rhony Abdullah Siagian on 2019-11-21.
 */
interface CurrentWeatherRemoteRepository {

    suspend fun getCurrentWeatherByGeo(
        latitude: Double,
        longitude: Double,
        appId: String,
        units: String,
        lang: String
    ): CurrentWeatherEntity

    suspend fun getHourlyWeatherByGeo(
        latitude: Double,
        longitude: Double,
        appId: String,
        units: String,
        lang: String
    ): List<CurrentWeatherHourlyEntity>

    suspend fun getDailyWeatherByGeo(
        latitude: Double,
        longitude: Double,
        appId: String,
        units: String,
        lang: String
    ): List<DailyWeatherEntity>

    companion object {
        const val PATH_WEATHER = "weather"
        const val PATH_FORECAST = "forecast"
    }

}
