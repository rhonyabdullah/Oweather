package com.droid.silverbullet.common

import java.text.SimpleDateFormat
import java.util.*

/**
 * Project OWeather.
 *
 * Created by Rhony Abdullah Siagian on 2019-11-21.
 */
object DateParser {

    private val sdfEEEE by lazy { SimpleDateFormat("EEEE", Locale.US) }
    private val sdfDDMMMMyyyy by lazy { SimpleDateFormat("dd MMMM yyyy", Locale.US) }

    fun getDayOfWeek(date: Date): String = sdfEEEE.format(date).toBahasaDay()

    fun getDdMMMMyyyy(date: Date): String = sdfDDMMMMyyyy.format(date)

    private fun String.toBahasaDay(): String = when (this) {
        "Monday" -> "Senin"
        "Tuesday" -> "Selasa"
        "Wednesday" -> "Rabu"
        "Thursday" -> "Kamis"
        "Friday" -> "Jum'at"
        "Saturday" -> "Sabtu"
        "Sunday" -> "Minggu"
        else -> throw RuntimeException("Unknown kind of day!")
    }
}
