package com.droid.silverbullet.data.di

import com.droid.silverbullet.data.BaseTest
import com.droid.silverbullet.data.repository.CurrentWeatherRepositoryImplTest
import com.droid.silverbullet.data.source.remote.CurrentWeatherRemoteRepositoryImplTest
import dagger.Component
import javax.inject.Singleton

/**
 * Project OWeather.
 *
 * Created by Rhony Abdullah Siagian on 2019-11-19.
 */
@Singleton
@Component(modules = [DataModule::class, TestDataModule::class])
interface TestDataComponent {
    fun inject(baseTest: BaseTest)
    fun inject(test: CurrentWeatherRemoteRepositoryImplTest)
    fun inject(test: CurrentWeatherRepositoryImplTest)
}
