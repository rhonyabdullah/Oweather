package com.droid.silverbullet.data.di

import dagger.Module
import dagger.Provides
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.json.GsonSerializer
import io.ktor.client.features.json.JsonFeature
import javax.inject.Singleton

/**
 * Project OWeather.
 *
 * Created by Rhony Abdullah Siagian on 2019-11-19.
 */
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideKtorClient(): HttpClient = HttpClient(Android) {
        install(JsonFeature) {
            serializer = GsonSerializer()
        }
    }
}
