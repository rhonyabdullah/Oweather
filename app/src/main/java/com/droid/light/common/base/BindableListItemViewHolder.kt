package com.droid.light.common.base

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * Project OWeather.
 *
 * Created by Rhony Abdullah Siagian on 2019-11-19
 */
abstract class BindableListItemViewHolder<out B : ViewDataBinding>(itemView: View) :
    RecyclerView.ViewHolder(itemView) {
    val binding: B = DataBindingUtil.bind(itemView)!!
}
