package com.example.dogtranslate.ui.sounds

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.dogtranslate.data.local.AppPreferences
import com.example.dogtranslate.ui.base.BaseViewModel
import javax.inject.Inject

class SoundsViewModel @Inject constructor(
    private val appPreferences: AppPreferences

) : BaseViewModel() {

    init {

    }
}