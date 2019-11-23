package com.droid.silverbullet.domain.usecase

import com.droid.silverbullet.common.model.data.CurrentWeatherHourlyData
import com.droid.silverbullet.domain.repository.CurrentWeatherRepository
import javax.inject.Inject

/**
 * Project OWeather.
 *
 * Created by Rhony Abdullah Siagian on 2019-11-23.
 */
class GetHourlyByGeoUseCase @Inject constructor(
    private val repository: CurrentWeatherRepository
) : UseCase<CurrentWeatherHourlyData, GetHourlyByGeoUseCase.Params>() {

    override suspend fun build(params: Params?): UseCaseResponse<CurrentWeatherHourlyData> {
        return repository.getHourlyByGeographic(params!!.latitude, params.longitude)
    }

    data class Params(val latitude: Double, val longitude: Double)
}
