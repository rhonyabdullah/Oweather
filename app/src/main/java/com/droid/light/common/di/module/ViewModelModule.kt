package com.droid.light.common.di.module

import androidx.lifecycle.ViewModelProvider
import com.droid.light.common.base.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module

/**
 * Project OWeather.
 *
 * Created by Rhony Abdullah Siagian on 2019-11-19.
 */
@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}
