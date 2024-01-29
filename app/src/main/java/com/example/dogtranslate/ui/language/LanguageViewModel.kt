package com.example.dogtranslate.ui.language

import com.example.dogtranslate.data.local.AppPreferences
import com.example.dogtranslate.ui.base.BaseViewModel
import javax.inject.Inject

class LanguageViewModel @Inject constructor(
    private val appPreferences: AppPreferences
) : BaseViewModel() {
    init {

    }

    fun saveLanguagePreference(languageCode: String) {
        appPreferences.saveLanguagePreference(languageCode)
    }

    fun getLanguagePreference(): String {
        return appPreferences.getLocale()
    }
}