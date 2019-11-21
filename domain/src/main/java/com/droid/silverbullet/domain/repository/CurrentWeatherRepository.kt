package com.droid.silverbullet.domain.repository

import com.droid.silverbullet.common.model.data.CurrentWeatherData
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

    suspend fun getByGeographic(): UseCaseResponse<CurrentWeatherData>
}
