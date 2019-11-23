package com.droid.silverbullet.data.source.remote

import com.droid.silverbullet.common.model.entity.CurrentWeatherEntity
import com.droid.silverbullet.common.model.entity.CurrentWeatherHourlyEntity
import com.droid.silverbullet.common.model.entity.DailyWeatherEntity
import com.droid.silverbullet.data.*
import com.droid.silverbullet.domain.repository.source.CurrentWeatherRemoteRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import javax.inject.Inject


/**
 * Project OWeather.
 *
 * Created by Rhony Abdullah Siagian on 2019-11-21.
 */
@Suppress("PrivatePropertyName")
class CurrentWeatherRemoteRepositoryImpl @Inject constructor(
    private val client: HttpClient
) : CurrentWeatherRemoteRepository {

    private val pathWeather get() = CurrentWeatherRemoteRepository.PATH_WEATHER

    override suspend fun getCurrentWeatherByGeo(
        latitude: Double,
        longitude: Double,
        appId: String,
        units: String,
        lang: String
    ): CurrentWeatherEntity {
        val params = mapOf(
            latitude.toLatitudeParam(), longitude.toLongitudeParam(),
            appId.toAppIdParam(), units.toUnitsParam(), lang.toLangParam()
        )
        return client.get(pathWeather, getRequest(params))
    }

    override suspend fun getHourlyWeatherByGeo(
        latitude: Double,
        longitude: Double,
        appId: String,
        units: String,
        lang: String
    ): List<CurrentWeatherHourlyEntity> {
        val typeToken = object : TypeToken<List<CurrentWeatherHourlyEntity>>() {}.type
        return Gson().fromJson(client.getJson(JSON_CURRENT_WEATHER_HOULRY), typeToken)
    }

    override suspend fun getDailyWeatherByGeo(
        latitude: Double,
        longitude: Double,
        appId: String,
        units: String,
        lang: String
    ): List<DailyWeatherEntity> {
        val typeToken = object : TypeToken<List<DailyWeatherEntity>>() {}.type
        return Gson().fromJson(client.getJson(JSON_DAILY_WEATHER), typeToken)
    }

    private fun getRequest(params: Map<String, String>): HttpRequestBuilder.() -> Unit = {
        url {
            host = BuildConfig.BASE_URL
            params.map { parameters.append(it.key, it.value) }
        }
    }

    /**
     * Mock cheating :)
     */
    private fun HttpClient.getJson(path: String): String {
        return String(javaClass.classLoader!!.getResourceAsStream(path).readBytes())
    }

    private val JSON_CURRENT_WEATHER_HOULRY get() = "current_weather_hourly.json"
    private val JSON_DAILY_WEATHER get() = "daily_weather.json"
}
