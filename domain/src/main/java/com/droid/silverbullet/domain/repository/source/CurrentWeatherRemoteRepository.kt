package com.droid.silverbullet.domain.repository.source

import com.droid.silverbullet.common.model.entity.CurrentWeatherEntity

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

}
