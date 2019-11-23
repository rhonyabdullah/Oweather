package com.droid.light.common.utils

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.databinding.BaseObservable

/**
 * Project OWeather.
 *
 * Created by Rhony Abdullah Siagian on 2019-11-22.
 */
class ObservableViewBackground(
    @DrawableRes drawableResource: Int? = null,
    @ColorRes colorResource: Int? = null,
    @ColorInt colorValue: Int? = null,
    drawable: Drawable? = null,
    bitmap: Bitmap? = null
) : BaseObservable() {

    @DrawableRes var drawableResource: Int? = drawableResource
        set(value) {
            field = value
            notifyChange()
        }

    @ColorRes var colorResource: Int? = colorResource
        set(value) {
            field = value
            notifyChange()
        }

    @ColorInt var colorValue: Int? = colorValue
        set(value) {
            field = value
            notifyChange()
        }

    var drawable: Drawable? = drawable
        set(value) {
            field = value
            notifyChange()
        }

    var bitmap: Bitmap? = bitmap
        set(value) {
            field = value
            notifyChange()
        }
}
