package com.droid.silverbullet.data.di

import com.droid.silverbullet.data.repository.CurrentWeatherRepositoryImpl
import com.droid.silverbullet.data.source.cache.CurrentWeatherCacheRepositoryImpl
import com.droid.silverbullet.data.source.remote.CurrentWeatherRemoteRepositoryImpl
import com.droid.silverbullet.domain.repository.CurrentWeatherRepository
import com.droid.silverbullet.domain.repository.source.CurrentWeatherCacheRepository
import com.droid.silverbullet.domain.repository.source.CurrentWeatherRemoteRepository
import dagger.Module
import dagger.Provides

/**
 * Project OWeather.
 *
 * Created by Rhony Abdullah Siagian on 2019-11-19.
 */
@Module
class DataModule {

    @Provides
    fun provideCurrentWeatherRepoCache(
        cacheImpl: CurrentWeatherCacheRepositoryImpl
    ): CurrentWeatherCacheRepository = cacheImpl

    @Provides
    fun provideCurrentWeatherRepoRemote(
        remoteImpl: CurrentWeatherRemoteRepositoryImpl
    ): CurrentWeatherRemoteRepository = remoteImpl

    @Provides
    fun provideCurrentWeatherRepository(
        repoImpl: CurrentWeatherRepositoryImpl
    ): CurrentWeatherRepository = repoImpl
}
