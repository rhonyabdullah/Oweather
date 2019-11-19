package com.droid.light

import androidx.annotation.CallSuper
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule

/**
 * Project OWeather.
 *
 * Created by Rhony Abdullah Siagian on 2019-11-19
 */
abstract class BaseTest : LifecycleOwner {

    protected val DELAY_MOCK get() = 500L

    private lateinit var lifeCycleRegistry: LifecycleRegistry

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = CoroutineRule()

    @get:Rule
    var rule = InstantTaskExecutorRule()

    override fun getLifecycle(): Lifecycle = lifeCycleRegistry

    @CallSuper
    @Before
    open fun setUp() {
        lifeCycleRegistry = LifecycleRegistry(this)
        lifeCycleRegistry.currentState = Lifecycle.State.STARTED
    }

    @After
    open fun tearDown() {
        lifeCycleRegistry.currentState = Lifecycle.State.DESTROYED
    }

}
