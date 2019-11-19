package com.droid.light.common.di.module

import android.content.Context
import android.content.SharedPreferences
import com.droid.light.BuildConfig
import com.droid.silverbullet.data.di.DataModule
import com.droid.silverbullet.data.di.NetworkModule
import dagger.Module
import dagger.Provides
import dagger.android.support.DaggerApplication
import javax.inject.Singleton

/**
 * Project OWeather.
 *
 * Created by Rhony Abdullah Siagian on 2019-11-19.
 */
@Module(includes = [ViewModelModule::class, DataModule::class, NetworkModule::class])
class AppModule {

    @Provides
    @Singleton
    fun providePreference(application: DaggerApplication): SharedPreferences {
        return application.getSharedPreferences(
            BuildConfig.APPLICATION_ID.plus(BuildConfig.VERSION_NAME), Context.MODE_PRIVATE
        )
    }

}
