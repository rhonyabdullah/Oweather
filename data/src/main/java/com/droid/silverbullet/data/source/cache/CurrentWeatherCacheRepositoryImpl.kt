package com.droid.silverbullet.data.source.cache

import android.content.SharedPreferences
import com.droid.silverbullet.domain.repository.source.CurrentWeatherCacheRepository
import javax.inject.Inject

/**
 * Project OWeather.
 *
 * Created by Rhony Abdullah Siagian on 2019-11-21.
 */
class CurrentWeatherCacheRepositoryImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : CurrentWeatherCacheRepository {
}