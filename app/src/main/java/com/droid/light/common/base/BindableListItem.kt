package com.droid.light.common.base

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.PropertyChangeRegistry
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.mikepenz.fastadapter.items.AbstractItem

/**
 * Project OWeather.
 *
 * Created by Rhony Abdullah Siagian on 2019-11-23.
 */
abstract class BindableListItem<Item : AbstractItem<*>, B : ViewDataBinding> :
    AbstractItem<BindableListItem<Item, B>.ViewHolder>(), ObservableDataBinding {

    @Transient override var callbacks: PropertyChangeRegistry = PropertyChangeRegistry()

    abstract fun onBindView(binding: B)

    override val type: Int get() = hashCode()

    override fun bindView(holder: ViewHolder, payloads: MutableList<Any>) {
        super.bindView(holder, payloads)
        onBindView(holder.binding)
    }

    override fun unbindView(holder: ViewHolder) {
        super.unbindView(holder)
        holder.binding.unbind()
    }

    override fun getViewHolder(v: View): ViewHolder = ViewHolder(v)

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding: B = DataBindingUtil.bind(itemView)!!
    }
}
