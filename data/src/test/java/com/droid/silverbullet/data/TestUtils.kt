package com.droid.silverbullet.data

import io.ktor.client.engine.mock.MockEngineConfig
import io.ktor.http.ContentType
import io.ktor.http.Headers
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
