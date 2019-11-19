package com.droid.light.common.utils

import android.content.res.ColorStateList
import android.graphics.Color
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.core.widget.ImageViewCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.droid.light.R

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
