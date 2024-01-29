package com.example.dogtranslate.ui.main

import com.example.dogtranslate.data.local.AppPreferences
import com.example.dogtranslate.ui.base.BaseViewModel
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val appPreferences: AppPreferences
) : BaseViewModel() {
    init {

    }

    fun getLanguagePreference(): String {
        return appPreferences.getLocale()
    }
}