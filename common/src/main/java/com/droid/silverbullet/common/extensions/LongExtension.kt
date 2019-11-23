package com.droid.silverbullet.common.extensions

import java.util.*

/**
 * Project OWeather.
 *
 * Created by Rhony Abdullah Siagian on 2019-11-21.
 */
fun Long.toDate(): Date = Date(this * 1000)
