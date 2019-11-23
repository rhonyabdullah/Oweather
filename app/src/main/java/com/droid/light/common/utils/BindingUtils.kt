package com.droid.light.common.utils

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.droid.light.R
import com.droid.light.common.customview.RecyclerViewBinding
import com.droid.light.common.customview.RecyclerViewBindingData
import com.google.android.material.card.MaterialCardView

/**
 * Project OWeather.
 *
 * Created by Rhony Abdullah Siagian on 2019-11-19
 */
@BindingAdapter(value = ["imageRound"], requireAll = true)
fun loadImageUrlRound(imageView: ImageView, imageRoundUrl: String?) {
    imageRoundUrl?.let { url ->
        loadUrl(
            imageView,
            imageUrl = url,
            transformation = ImageTransformation.CropCircle
        )
    }
}

@BindingAdapter(value = ["android:tint"], requireAll = false)
fun setImageViewTint(view: ImageView, color: String?) {
    color?.let { ImageViewCompat.setImageTintList(view, ColorStateList.valueOf(Color.parseColor(it))) }
}

@BindingAdapter(value = ["data"], requireAll = false)
fun setRecyclerViewBindingData(rv: RecyclerView, data: RecyclerViewBindingData) {
    if (rv is RecyclerViewBinding) rv.setItems(data)
}

@BindingAdapter(value = ["srcCompat"], requireAll = false)
fun setSrcCompat(view: AppCompatImageView, obs: ObservableViewBackground) = setBackground(view, obs)

@BindingAdapter(value = ["android:background"], requireAll = false)
fun setBackground(view: View, observable: ObservableViewBackground) {
    observable.drawableResource?.let {
        when (view) {
            is AppCompatImageView -> view.setImageResource(it)
            else -> view.setBackgroundResource(it)
        }
    }
    observable.colorResource?.let {
        val color = ContextCompat.getColor(view.context, it)
        when (view) {
            is MaterialCardView -> view.setCardBackgroundColor(color)
            else -> view.setBackgroundColor(color)
        }
    }
    observable.colorValue?.let {
        view.setBackgroundColor(it)
    }
    observable.drawable?.let {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) view.setBackgroundDrawable(it)
        else view.background = it
    }
    observable.bitmap?.let {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            view.setBackgroundDrawable(BitmapDrawable(it))
        } else view.background = BitmapDrawable(view.context.resources, it)
    }
}

private fun loadUrl(
    imageView: ImageView, imageUrl: String = "",
    @DrawableRes imageId: Int = R.mipmap.ic_launcher_round,
    transformation: ImageTransformation? = null, usePlaceholder: Boolean = true
) {
    val requestManager = Glide.with(imageView)
    var builder = if (imageUrl.isNotEmpty() && imageUrl.isNotBlank()) {
        requestManager.load(imageUrl)
    } else requestManager.load(imageId)

    if (usePlaceholder) {
        builder = builder.placeholder(R.mipmap.ic_launcher_round)
        builder = builder.error(R.mipmap.ic_launcher_round)
    }

    when (transformation) {
        ImageTransformation.CropCircle -> builder.circleCrop().into(imageView)
        else -> builder.centerCrop().into(imageView)
    }
}

private sealed class ImageTransformation {
    object CropCircle : ImageTransformation()
}
