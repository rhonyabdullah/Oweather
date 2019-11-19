package com.droid.light.common.di.scope

import javax.inject.Qualifier

/**
 * Project OWeather.
 *
 * Created by Rhony Abdullah Siagian on 2019-11-15.
 *
 * This scope is used to prevent multibinding on dagger compiler at presentation layer
 */
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class PresentationScope
