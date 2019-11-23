package com.droid.light.common.customview

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.droid.light.R
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.IItem
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.items.AbstractItem

/**
 * Project OWeather.
 *
 * Created by Rhony Abdullah Siagian on 2019-11-23.
 */
class BindableRecyclerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr), RecyclerViewBinding {

    private val itemAdapter: ItemAdapter<IItem<*>> = ItemAdapter()
    private val fastAdapter: FastAdapter<IItem<*>> = FastAdapter.with(itemAdapter)

    init {
        super.setAdapter(fastAdapter)

        val typedValue = context.obtainStyledAttributes(attrs, R.styleable.BindableRecyclerView)

        val orientation = typedValue.getInteger(R.styleable.BindableRecyclerView_orientation, VERTICAL)
        if (orientation == VERTICAL || orientation == HORIZONTAL) {
            layoutManager = LinearLayoutManager(context, orientation, false)
        } else throw RuntimeException("Unknown orientation")

        typedValue.recycle()
    }

    override fun getAdapter(): FastAdapter<IItem<*>> = fastAdapter

    override fun setAdapter(adapter: Adapter<*>?) {
        throw IllegalArgumentException("setAdapter within BindableRecyclerView not allowed!")
    }

    override fun setItems(items: List<AbstractItem<*>>) {
        itemAdapter.setNewList(items)
        fastAdapter.notifyAdapterDataSetChanged()
        scheduleLayoutAnimation()
    }
}

internal interface RecyclerViewBinding {
    fun setItems(items: RecyclerViewBindingData)
}

internal typealias RecyclerViewBindingData = List<AbstractItem<*>>
