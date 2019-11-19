package com.droid.light.common.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.droid.light.common.base.EventWrapper

/**
 * Project OWeather.
 *
 * Created by Rhony Abdullah Siagian on 2019-11-19
 */
inline fun <T> LifecycleOwner.subscribeFilterNull(liveData: LiveData<T>, crossinline block: (T) -> Unit) {
    liveData.observe(this, Observer { it?.let { value -> block(value) } })
}

inline fun <T> LifecycleOwner.subscribeSingleLiveEvent(
    liveData: LiveData<EventWrapper<T>>, crossinline onEventUnhandled: (T) -> Unit
) {
    liveData.observe(this, Observer { it?.getEventIfNotHandled()?.let(onEventUnhandled) })
}
