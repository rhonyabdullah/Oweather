package com.droid.light.oweather

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.droid.light.BR
import com.droid.light.R
import com.droid.light.common.customview.RecyclerViewBindingData
import com.droid.light.common.extensions.toCloudTypeDrawableInt
import com.droid.light.common.utils.ObservableViewBackground
import com.droid.light.oweather.items.DailyWeatherItem
import com.droid.light.oweather.items.HourlyWeatherItem
import com.droid.silverbullet.common.model.data.CurrentWeatherData
import com.droid.silverbullet.common.model.data.CurrentWeatherHourlyData
import com.droid.silverbullet.common.model.data.DailyWeatherData
import javax.inject.Inject

/**
 * Project OWeather.
 *
 * Created by Rhony Abdullah Siagian on 2019-11-22.
 *
 * Todo, BR Property marked as error but still runable, this is bug from databinding!
 */
class OWeatherBindingModel @Inject constructor() : BaseObservable() {

    private val TAG get() = OWeatherBindingModel::class.java.simpleName

    @get:Bindable
    var city = ""
        private set(value) {
            field = value
            notifyPropertyChanged(BR.city)
        }

    @get:Bindable
    var temperature = 0
        private set(value) {
            field = value
            notifyPropertyChanged(BR.temperature)
        }

    @get:Bindable
    var day = ""
        private set(value) {
            field = "${value},"
            notifyPropertyChanged(BR.day)
        }

    @get:Bindable
    var date = ""
        private set(value) {
            field = value
            notifyPropertyChanged(BR.date)
        }

    @get:Bindable
    val cloudType = ObservableViewBackground(drawableResource = R.mipmap.ic_launcher_round)

    @get:Bindable
    var cloudDescription = ""
        private set(value) {
            field = value
            notifyPropertyChanged(BR.cloudDescription)
        }

    @get:Bindable
    var hourlyData: RecyclerViewBindingData = listOf()
        private set(value) {
            field = value
            notifyPropertyChanged(BR.hourlyData)
        }

    @get:Bindable
    var dailyData: RecyclerViewBindingData = listOf()
        private set(value) {
            field = value
            notifyPropertyChanged(BR.dailyData)
        }

    fun applyWeatherData(data: CurrentWeatherData) {
        city = data.city
        temperature = data.temperature
        day = data.day
        date = data.date
        cloudDescription = data.cloudDesc
        cloudType.drawableResource = data.cloudType.toCloudTypeDrawableInt(true)
    }

    fun applyHourlyData(data: CurrentWeatherHourlyData) {
        if (data.weatherInHourList.isEmpty()) return
        hourlyData = data.weatherInHourList.map {
            HourlyWeatherItem(
                time = it.timeInHour,
                temperature = it.temperature,
                cloudIcon = ObservableViewBackground(drawableResource = it.cloudType.toCloudTypeDrawableInt())
            )
        }
    }

    fun applyWeeklyData(data: DailyWeatherData) {
        if (data.weeklyWeatherList.isEmpty()) return
        dailyData = data.weeklyWeatherList.map {
            DailyWeatherItem(
                day = it.dayOfWeek,
                temperature = it.temperature,
                cloudIcon = ObservableViewBackground(drawableResource = it.cloudType.toCloudTypeDrawableInt())
            )
        }
    }
}
