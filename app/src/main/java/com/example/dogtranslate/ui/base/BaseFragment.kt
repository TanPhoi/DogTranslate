package com.example.dogtranslate.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment<T : ViewDataBinding, M : BaseViewModel> : DaggerFragment() {
    protected var binding: T? = null
    protected var viewModel: M? = null

    @Inject
    protected lateinit var viewModelFactory: ViewModelProvider.Factory

    @LayoutRes
    protected abstract fun layoutRes(): Int
    protected abstract fun viewModelClass(): Class<M>
    protected abstract fun initView()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutRes(), container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.lifecycleOwner = viewLifecycleOwner
        viewModel = ViewModelProvider(this, viewModelFactory)[viewModelClass()]
        initView()
    }

    protected fun <T> observeLiveData(liveData: LiveData<T>, observer: Observer<T>) {
        viewModel?.observeLiveData(liveData, observer)
    }

    protected fun <T> removeLiveDataObserver(liveData: LiveData<T>) {
        viewModel?.removeLiveDataObserver(liveData)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding?.lifecycleOwner = null
        binding = null
        viewModel = null
    }
}
