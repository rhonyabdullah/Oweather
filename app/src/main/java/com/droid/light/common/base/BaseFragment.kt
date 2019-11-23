package com.droid.light.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.droid.light.common.base.viewmodel.BaseViewModel
import com.droid.light.common.di.scope.PresentationScope
import dagger.android.support.DaggerFragment
import javax.inject.Inject

/**
 * Project OWeather.
 *
 * Created by Rhony Abdullah Siagian on 2019-11-19.
 */
abstract class BaseFragment<VM : BaseViewModel<*>, B : ViewDataBinding> : DaggerFragment() {

    @get:LayoutRes protected abstract val layoutId: Int

    @Inject @PresentationScope protected lateinit var viewModel: VM
    protected lateinit var binding: B

    protected abstract fun initComponents()
    protected abstract fun bindViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.bind(inflater.inflate(layoutId, container, false))!!
        binding.lifecycleOwner = this
        return binding.root
    }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initComponents()
        bindViewModels()
    }

}
