package com.droid.silverbullet.data

import androidx.annotation.CallSuper
import com.droid.silverbullet.data.di.DaggerTestDataComponent
import com.droid.silverbullet.data.di.TestDataComponent
import org.junit.After
import org.junit.Before

/**
 * Project OWeather.
 *
 * Created by Rhony Abdullah Siagian on 2019-11-19.
 */
abstract class BaseTest {

    protected lateinit var testComponent: TestDataComponent

    @CallSuper
    @Before
    open fun setUp() {
        testComponent = DaggerTestDataComponent
            .builder()
            .build()
        testComponent.inject(this)
    }

    @After
    open fun tearDown() {

    }
}
