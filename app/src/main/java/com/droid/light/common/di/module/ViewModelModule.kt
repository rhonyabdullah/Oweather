package com.droid.light.common.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.droid.light.common.base.viewmodel.ViewModelFactory
import com.droid.light.common.di.scope.ViewModelKey
import com.droid.light.oweather.OWeatherViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Project OWeather.
 *
 * Created by Rhony Abdullah Siagian on 2019-11-19.
 */
@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(OWeatherViewModel::class)
    abstract fun bindOWeatherViewModel(viewModel: OWeatherViewModel): ViewModel

}
