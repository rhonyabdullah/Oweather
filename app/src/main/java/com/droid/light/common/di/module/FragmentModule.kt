package com.droid.light.common.di.module

import com.droid.light.common.di.scope.FragmentScope
import com.droid.light.oweather.OWeatherFragment
import com.droid.light.oweather.OWeatherModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Project OWeather.
 *
 * Created by Rhony Abdullah Siagian on 2019-11-19.
 */
@Module
abstract class FragmentModule {

    @FragmentScope
    @ContributesAndroidInjector(modules = [OWeatherModule::class])
    abstract fun provideFragmentOWeather(): OWeatherFragment

}
