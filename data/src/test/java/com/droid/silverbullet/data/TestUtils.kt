package com.droid.silverbullet.data

import com.droid.silverbullet.domain.repository.source.CurrentWeatherRemoteRepository
import io.ktor.client.engine.mock.MockEngineConfig
import io.ktor.client.engine.mock.respond
import io.ktor.client.engine.mock.respondError
import io.ktor.http.ContentType
import io.ktor.http.Headers
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf

/**
 * Project OWeather.
 *
 * Created by Rhony Abdullah Siagian on 2019-11-19.
 */
internal val mockEngineConfig: MockEngineConfig.() -> Unit
    get() = {
        addHandler { request ->
            when (request.url.encodedPath) {
                testPath(CurrentWeatherRemoteRepository.PATH_WEATHER) -> {
                    if (request.url.parameters.contains("lat", latitudeTest.toString())
                        && request.url.parameters.contains("lon", longitudeTest.toString())
                    ) {
                        respond(getJson(JSON_CURRENT_WEATHER_DATA), headers = getDefaultHeaders())
                    } else {
                        respondError(status = HttpStatusCode.BadRequest)
                    }
                }
                else -> error("Unhandled request on ${request.url}")
            }
        }
    }

private fun getDefaultHeaders(): Headers {
    return headersOf("Content-Type", ContentType.Application.Json.toString())
}

private fun MockEngineConfig.getJson(path: String): String {
    return String(javaClass.classLoader!!.getResourceAsStream(path).readBytes())
}

private fun testPath(path: String): String = "/$path"

private const val JSON_CURRENT_WEATHER_DATA = "current_weather_data.json"

internal const val latitudeTest = -6.22
internal const val longitudeTest = 106.86
internal const val appIdTest = "f10384a8d369de1963d1b35d7297a69e"
internal const val unitsWeatherTest = "metric"
internal const val langTest = "lang"
