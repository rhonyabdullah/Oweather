package com.droid.silverbullet.data

import com.droid.silverbullet.domain.usecase.UseCaseResponse
import io.ktor.client.features.ClientRequestException
import io.ktor.client.request.HttpRequestBuilder

/**
 * Project OWeather.
 *
 * Created by Rhony Abdullah Siagian on 2019-11-19.
 */
typealias DefaultHttpRequestBuilder = HttpRequestBuilder.() -> Unit

internal fun Throwable.toErrorResponse(): UseCaseResponse.Error = when(this) {
    is ClientRequestException -> UseCaseResponse.Error(response.status.description, response.status.value)
    else -> UseCaseResponse.Error(message.orEmpty(), 404)
}
