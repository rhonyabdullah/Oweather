package com.droid.silverbullet.data

import com.droid.silverbullet.domain.usecase.UseCaseResponse
import io.ktor.client.features.ClientRequestException

/**
 * Project OWeather.
 *
 * Created by Rhony Abdullah Siagian on 2019-11-19.
 */
internal fun Throwable.toErrorResponse(): UseCaseResponse.Error = when (this) {
    is ClientRequestException -> UseCaseResponse.Error(response.status.description, response.status.value)
    else -> UseCaseResponse.Error(message.orEmpty(), 404)
}

internal fun String.toAppIdParam() = "appid" to this
internal fun String.toUnitsParam() = "units" to this
internal fun String.toLangParam() = "lang" to this
internal fun Double.toLatitudeParam() = "lat" to this.toString()
internal fun Double.toLongitudeParam() = "lon" to this.toString()

