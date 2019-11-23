package com.droid.silverbullet.domain.repository

import com.droid.silverbullet.common.model.data.CurrentWeatherData
import com.droid.silverbullet.common.model.data.CurrentWeatherHourlyData
import com.droid.silverbullet.common.model.data.DailyWeatherData
import com.droid.silverbullet.domain.repository.source.CurrentWeatherCacheRepository
import com.droid.silverbullet.domain.repository.source.CurrentWeatherRemoteRepository
import com.droid.silverbullet.domain.usecase.UseCaseResponse

/**
 * Project OWeather.
 *
 * Created by Rhony Abdullah Siagian on 2019-11-21.
 */
interface CurrentWeatherRepository {

    val cache: CurrentWeatherCacheRepository
    val remote: CurrentWeatherRemoteRepository

    suspend fun getWeatherByGeographic(
        latitude: Double,
        longitude: Double
    ): UseCaseResponse<CurrentWeatherData>

    suspend fun getHourlyByGeographic(
        latitude: Double,
        longitude: Double
    ): UseCaseResponse<CurrentWeatherHourlyData>

    suspend fun getDailyByGeographic(
        latitude: Double,
        longitude: Double
    ): UseCaseResponse<DailyWeatherData>
}
