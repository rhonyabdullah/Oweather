package com.droid.silverbullet.domain.usecase

import com.droid.silverbullet.common.model.data.DailyWeatherData
import com.droid.silverbullet.domain.repository.CurrentWeatherRepository
import javax.inject.Inject

/**
 * Project OWeather.
 *
 * Created by Rhony Abdullah Siagian on 2019-11-23.
 */
class GetDailyWeatherByGeoUseCase @Inject constructor(
    private val repository: CurrentWeatherRepository
) : UseCase<DailyWeatherData, GetDailyWeatherByGeoUseCase.Params>(){

    override suspend fun build(params: Params?): UseCaseResponse<DailyWeatherData> {
        return repository.getDailyByGeographic(params!!.latitude, params!!.longitude)
    }

    data class Params(val latitude: Double, val longitude: Double)
}
