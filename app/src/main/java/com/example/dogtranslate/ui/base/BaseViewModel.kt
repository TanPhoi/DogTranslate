package com.example.dogtranslate.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {
    private val liveDataObservers = mutableMapOf<LiveData<*>, Observer<*>>()

    // Phương thức để đăng ký Observer cho LiveData cụ thể
    fun <T> observeLiveData(liveData: LiveData<T>, observer: Observer<T>) {
        liveDataObservers[liveData] = observer
        liveData.observeForever(observer)
    }

    // Phương thức để hủy đăng ký Observer cho LiveData cụ thể
    fun <T> removeLiveDataObserver(liveData: LiveData<T>) {
        liveDataObservers[liveData]?.let {
            liveData.removeObserver(it as Observer<in T>)
            liveDataObservers.remove(liveData)
        }
    }

    // Hủy đăng ký tất cả Observer
    override fun onCleared() {
        super.onCleared()
        for ((liveData, observer) in liveDataObservers) {
            liveData.removeObserver(observer as Observer<in Any>)
        }
        liveDataObservers.clear()
    }
}
