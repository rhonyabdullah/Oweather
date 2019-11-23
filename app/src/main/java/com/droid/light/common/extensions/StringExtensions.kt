package com.droid.light.common.extensions

import androidx.annotation.DrawableRes
import com.droid.light.R
import com.droid.silverbullet.common.Constant

/**
 * Project OWeather.
 *
 * Created by Rhony Abdullah Siagian on 2019-11-23.
 */
@DrawableRes
internal fun String.toCloudTypeDrawableInt(isWhiteMode: Boolean = false): Int = when (this) {
    Constant.WEATHER_TYPE_CLEAR -> R.drawable.ic_sun
    Constant.WEATHER_TYPE_CLOUD, Constant.WEATHER_TYPE_HAZE -> {
        if (isWhiteMode) R.drawable.ic_cloud_white else R.drawable.ic_cloud
    }
    Constant.WEATHER_TYPE_RAIN -> if (isWhiteMode) R.drawable.ic_rain_white else R.drawable.ic_rain
    Constant.WEATHER_TYPE_THUNDER, Constant.WEATHER_TYPE_EXTREME -> {
        if (isWhiteMode) R.drawable.ic_thunder_white else R.drawable.ic_thunder
    }
    else -> throw RuntimeException("Unknown weather type")
}
