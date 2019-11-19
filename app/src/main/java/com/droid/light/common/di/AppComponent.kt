package com.droid.light.common.di

import com.droid.light.LightActivity
import com.droid.light.common.di.AppComponent.ActivityModule
import com.droid.light.common.di.module.AppModule
import com.droid.light.common.di.module.FragmentModule
import com.droid.light.common.di.scope.ActivityScope
import dagger.Component
import dagger.Module
import dagger.android.AndroidInjector
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication
import javax.inject.Singleton


/**
 * Project OWeather.
 *
 * Created by Rhony Abdullah Siagian on 2019-11-19.
 */
@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, AppModule::class, ActivityModule::class])
interface AppComponent : AndroidInjector<DaggerApplication> {

    @Component.Factory
    abstract class Factory : AndroidInjector.Factory<DaggerApplication>

    @Module
    abstract class ActivityModule {
        @ActivityScope
        @ContributesAndroidInjector(modules = [FragmentModule::class])
        internal abstract fun provideActivity(): LightActivity
    }
}
