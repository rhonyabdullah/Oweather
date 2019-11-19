package com.droid.light.common.base

/**
 * Project OWeather.
 *
 * Created by Rhony Abdullah Siagian on 2019-11-19
 */
open class EventWrapper<out T>(private val content: T) {

    private var hasBeenHandled = false

    /**
     * Returns the content and prevents its use again.
     */
    fun getEventIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }
}
