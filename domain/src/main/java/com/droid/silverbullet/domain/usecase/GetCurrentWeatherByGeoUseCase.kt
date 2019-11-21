package com.droid.silverbullet.domain.usecase

import com.droid.silverbullet.common.model.data.CurrentWeatherData
import com.droid.silverbullet.domain.repository.CurrentWeatherRepository
import javax.inject.Inject

/**
 * Project OWeather.
 *
 * Created by Rhony Abdullah Siagian on 2019-11-21.
 */
class GetCurrentWeatherByGeoUseCase @Inject constructor(
    private val repository: CurrentWeatherRepository
) : UseCase<CurrentWeatherData, GetCurrentWeatherByGeoUseCase.Params>() {

    override suspend fun build(params: Params?): UseCaseResponse<CurrentWeatherData> {
        return repository.getByGeographic()
    }

    data class Params(val latitude: Double, val longitude: Double)
}
