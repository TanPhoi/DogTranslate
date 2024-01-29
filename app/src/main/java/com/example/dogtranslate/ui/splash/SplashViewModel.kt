package com.example.dogtranslate.ui.splash

import com.example.dogtranslate.data.local.AppPreferences
import com.example.dogtranslate.ui.base.BaseViewModel
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    private val appPreferences: AppPreferences
) : BaseViewModel() {
    init {

    }

    fun getFirstScreen(): Boolean {
        return appPreferences.getFirstScreen()
    }

}