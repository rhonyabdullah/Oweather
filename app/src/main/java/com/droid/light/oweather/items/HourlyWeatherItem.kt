package com.droid.light.oweather.items

import androidx.databinding.Bindable
import com.droid.light.R
import com.droid.light.common.base.BindableListItem
import com.droid.light.common.utils.ObservableViewBackground
import com.droid.light.databinding.ListItemHourlyWeatherBinding

/**
 * Project OWeather.
 *
 * Created by Rhony Abdullah Siagian on 2019-11-23.
 */
class HourlyWeatherItem(
    @get:Bindable val time: String,
    @get:Bindable val temperature: Int,
    @get:Bindable val cloudIcon: ObservableViewBackground
) : BindableListItem<HourlyWeatherItem, ListItemHourlyWeatherBinding>() {

    override val layoutRes: Int get() = R.layout.list_item_hourly_weather

    override fun onBindView(binding: ListItemHourlyWeatherBinding) {
        binding.item = this
    }
}
