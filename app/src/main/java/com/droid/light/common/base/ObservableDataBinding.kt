package com.droid.light.common.base

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry

/**
 * Project regal.
 *
 * Created by Rhony Abdullah Siagian on 2019-11-22.
 */
interface ObservableDataBinding : Observable {

    /**
     * Marks this implementation with [Transient] annotation,
     * more info @see [link](https://bit.ly/2WPgmSc)
     */
    var callbacks: PropertyChangeRegistry

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        callback?.let { callbacks.add(it) }
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        callback?.let { callbacks.remove(it) }
    }

    /**
     * Notifies listeners that all properties of this instance have changed.
     */
    fun notifyChange() = callbacks.notifyCallbacks(this, 0, null)

    /**
     * Notifies listeners that a specific property has changed. The getter for the property
     * that changes should be marked with [Bindable] to generate a field in
     * `BR` to be used as `fieldId`.
     *
     * @param fieldId The generated BR id for the Bindable field.
     */
    fun notifyPropertyChanged(fieldId: Int) = callbacks.notifyCallbacks(this, fieldId, null)
}
