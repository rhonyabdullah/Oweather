package com.droid.silverbullet.data.di

import android.content.SharedPreferences
import com.droid.silverbullet.data.mockEngineConfig
import dagger.Module
import dagger.Provides
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.features.json.GsonSerializer
import io.ktor.client.features.json.JsonFeature
import org.mockito.Mockito
import javax.inject.Singleton

/**
 * Project OWeather.
 *
 * Created by Rhony Abdullah Siagian on 2019-11-19.
 */
@Module
class TestDataModule {

    @Singleton
    @Provides
    fun provideKtorClient(): HttpClient = HttpClient(MockEngine) {
        install(JsonFeature) { serializer = GsonSerializer() }
        engine(mockEngineConfig)
    }

    @Singleton
    @Provides
    fun provideSharedPreference(): SharedPreferences = Mockito.mock(SharedPreferences::class.java)
}
