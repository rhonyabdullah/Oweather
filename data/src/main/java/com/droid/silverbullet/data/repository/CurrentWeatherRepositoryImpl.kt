package com.droid.silverbullet.data.repository

import com.droid.silverbullet.common.model.data.CurrentWeatherData
import com.droid.silverbullet.common.model.data.CurrentWeatherHourlyData
import com.droid.silverbullet.common.model.data.DailyWeatherData
import com.droid.silverbullet.data.BuildConfig
import com.droid.silverbullet.data.toErrorResponse
import com.droid.silverbullet.domain.repository.CurrentWeatherRepository
import com.droid.silverbullet.domain.repository.source.CurrentWeatherCacheRepository
import com.droid.silverbullet.domain.repository.source.CurrentWeatherRemoteRepository
import com.droid.silverbullet.domain.usecase.UseCaseResponse
import javax.inject.Inject

/**
 * Project OWeather.
 *
 * Created by Rhony Abdullah Siagian on 2019-11-21.
 */
class CurrentWeatherRepositoryImpl @Inject constructor(
    override val cache: CurrentWeatherCacheRepository,
    override val remote: CurrentWeatherRemoteRepository
) : CurrentWeatherRepository {

    override suspend fun getWeatherByGeographic(
        latitude: Double,
        longitude: Double
    ): UseCaseResponse<CurrentWeatherData> {
        return try {
            val entity = remote.getCurrentWeatherByGeo(
                latitude = latitude,
                longitude = longitude,
                appId = BuildConfig.APP_ID,
                units = BuildConfig.UNITS,
                lang = BuildConfig.LANG
            )
            UseCaseResponse.Success(CurrentWeatherData.parseEntity(entity))
        } catch (e: Throwable) {
            e.toErrorResponse()
        }
    }

    override suspend fun getHourlyByGeographic(
        latitude: Double,
        longitude: Double
    ): UseCaseResponse<CurrentWeatherHourlyData> {
        return try {
            val entity = remote.getHourlyWeatherByGeo(
                latitude = latitude,
                longitude = longitude,
                appId = BuildConfig.APP_ID,
                units = BuildConfig.UNITS,
                lang = BuildConfig.LANG
            )
            UseCaseResponse.Success(CurrentWeatherHourlyData.parseEntity(entity))
        } catch (e: Throwable) {
            e.toErrorResponse()
        }
    }

    override suspend fun getDailyByGeographic(
        latitude: Double,
        longitude: Double
    ): UseCaseResponse<DailyWeatherData> {
        return try {
            val entity = remote.getDailyWeatherByGeo(
                latitude = latitude,
                longitude = longitude,
                appId = BuildConfig.APP_ID,
                units = BuildConfig.UNITS,
                lang = BuildConfig.LANG
            )
            UseCaseResponse.Success(DailyWeatherData.parseEntity(entity))
        } catch (e: Throwable) {
            e.toErrorResponse()
        }
    }
}
