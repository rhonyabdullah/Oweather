package com.droid.light.oweather

import com.droid.light.common.base.EventWrapper
import com.droid.light.common.base.viewmodel.BaseViewModel
import com.droid.silverbullet.common.model.data.CurrentWeatherData
import com.droid.silverbullet.common.model.data.CurrentWeatherHourlyData
import com.droid.silverbullet.common.model.data.DailyWeatherData
import com.droid.silverbullet.domain.usecase.GetCurrentWeatherByGeoUseCase
import com.droid.silverbullet.domain.usecase.GetHourlyByGeoUseCase
import com.droid.silverbullet.domain.usecase.GetDailyWeatherByGeoUseCase
import com.droid.silverbullet.domain.usecase.UseCaseResponse
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Project OWeather.
 *
 * Created by Rhony Abdullah Siagian on 2019-11-22.
 */
class OWeatherViewModel @Inject constructor(
    private val getCurrentWeatherGeoUseCase: GetCurrentWeatherByGeoUseCase,
    private val getHourlyByGeoUseCase: GetHourlyByGeoUseCase,
    private val getWeeklyWeatherByGeoUseCase: GetDailyWeatherByGeoUseCase
) : BaseViewModel<OWeatherViewModel.State>() {

    sealed class State {
        data class OnLoading(val isLoading: Boolean) : State()
        data class ShowError(val message: String) : State()
        data class ShowCurrentWeather(val data: CurrentWeatherData) : State()
        data class ShowHourlyWeather(val data: CurrentWeatherHourlyData) : State()
        data class ShowWeeklyWeather(val data: DailyWeatherData) : State()
        object ShouldRequestLocation : State()
    }

    sealed class Event {
        object RetrieveWeatherData : Event()
        data class OnLocationReceived(val locationData: LocationData) : Event()
    }

    private var storedLocation: LocationData? = null

    override val handler: CoroutineExceptionHandler
        get() = CoroutineExceptionHandler { _, throwable ->
            _state.value = EventWrapper(State.ShowError(throwable.message.orEmpty()))
        }

    fun onEventReceived(event: Event) {
        when (event) {
            Event.RetrieveWeatherData -> retrieveWeatherData()
            is Event.OnLocationReceived -> storedLocation = event.locationData
        }
    }

    private fun retrieveWeatherData() {
        if (storedLocation == null) {
            _state.value = EventWrapper(State.ShouldRequestLocation)
            return
        }
        retrieveCurrentWeather()
        retrieveHourlyWeather()
        retrieveWeeklyWeather()
    }

    private fun retrieveCurrentWeather() = launch(jobHandler) {
        try {
            _state.value = EventWrapper(State.OnLoading(true))
            when (val result = getCurrentWeatherGeoUseCase.execute(
                GetCurrentWeatherByGeoUseCase.Params(storedLocation!!.first, storedLocation!!.second)
            )) {
                is UseCaseResponse.Success -> {
                    _state.value = EventWrapper(State.ShowCurrentWeather(result.value))
                }
                is UseCaseResponse.Error -> resolveError(result)
            }
        } finally {
            _state.value = EventWrapper(State.OnLoading(false))
        }
    }

    private fun retrieveHourlyWeather() = launch(jobHandler) {
        try {
            _state.value = EventWrapper(State.OnLoading(true))
            when (val result = getHourlyByGeoUseCase.execute(
                GetHourlyByGeoUseCase.Params(storedLocation!!.first, storedLocation!!.second)
            )) {
                is UseCaseResponse.Success -> {
                    _state.value = EventWrapper(State.ShowHourlyWeather(result.value))
                }
                is UseCaseResponse.Error -> resolveError(result)
            }
        } finally {
            _state.value = EventWrapper(State.OnLoading(false))
        }
    }

    private fun retrieveWeeklyWeather() = launch(jobHandler) {
        try {
            _state.value = EventWrapper(State.OnLoading(true))
            when (val result = getWeeklyWeatherByGeoUseCase.execute(
                GetDailyWeatherByGeoUseCase.Params(storedLocation!!.first, storedLocation!!.second)
            )) {
                is UseCaseResponse.Success -> {
                    _state.value = EventWrapper(State.ShowWeeklyWeather(result.value))
                }
                is UseCaseResponse.Error -> resolveError(result)
            }
        } finally {
            _state.value = EventWrapper(State.OnLoading(false))
        }
    }

    private fun resolveError(error: UseCaseResponse.Error) {
        _state.value = EventWrapper(State.ShowError(error.message))
    }
}

typealias LocationData = Pair<Latitude, Longitude>
typealias Latitude = Double
typealias Longitude = Double
