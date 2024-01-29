package com.example.dogtranslate.ui.intro

import com.example.dogtranslate.data.local.AppPreferences
import com.example.dogtranslate.ui.base.BaseViewModel
import javax.inject.Inject

class IntroViewModel @Inject constructor(
    private val appPreferences: AppPreferences

) : BaseViewModel() {
    init {

    }

    fun saveFirstScreen(firstScreen: Boolean) {
        appPreferences.saveFirstScreen(firstScreen)
    }
}