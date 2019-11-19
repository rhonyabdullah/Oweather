package com.droid.light.common.base.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.droid.light.common.base.EventWrapper
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

/**
 * Project OWeather.
 *
 * Created by Rhony Abdullah Siagian on 2019-11-19.
 */
abstract class BaseViewModel<State: Any> : ViewModel(), CoroutineScope {

    private val supervisorJob = SupervisorJob()
    internal abstract val handler: CoroutineExceptionHandler
    internal val jobHandler: CoroutineContext get() = handler + Job((supervisorJob))

    @Suppress("PropertyName")
    protected val _state: MutableLiveData<EventWrapper<State>> = MutableLiveData()
    internal val state: LiveData<EventWrapper<State>> get() = _state

    override val coroutineContext: CoroutineContext = Dispatchers.Main

    override fun onCleared() {
        super.onCleared()
        supervisorJob.cancel()
    }
}