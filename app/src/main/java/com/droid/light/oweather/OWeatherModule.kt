package com.droid.light.oweather

import androidx.lifecycle.ViewModelProviders
import com.droid.light.common.base.viewmodel.ViewModelFactory
import com.droid.light.common.di.scope.PresentationScope
import dagger.Module
import dagger.Provides

/**
 * Project OWeather.
 *
 * Created by Rhony Abdullah Siagian on 2019-11-22.
 */
@Module
class OWeatherModule {

    @Provides
    @PresentationScope
    fun provideViewModel(factory: ViewModelFactory, fragment: OWeatherFragment): OWeatherViewModel {
        return ViewModelProviders.of(fragment, factory).get(OWeatherViewModel::class.java)
    }
}
