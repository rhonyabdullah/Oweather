package com.droid.light.oweather.items

import androidx.databinding.Bindable
import com.droid.light.R
import com.droid.light.common.base.BindableListItem
import com.droid.light.common.utils.ObservableViewBackground
import com.droid.light.databinding.ListItemDailyWeatherBinding

/**
 * Project OWeather.
 *
 * Created by Rhony Abdullah Siagian on 2019-11-23.
 */
class DailyWeatherItem(
    @get:Bindable val day: String,
    @get:Bindable val temperature: Int,
    @get:Bindable val cloudIcon: ObservableViewBackground
) : BindableListItem<DailyWeatherItem, ListItemDailyWeatherBinding>() {

    override val layoutRes: Int get() = R.layout.list_item_daily_weather

    override fun onBindView(binding: ListItemDailyWeatherBinding) {
        binding.item = this
    }
}
