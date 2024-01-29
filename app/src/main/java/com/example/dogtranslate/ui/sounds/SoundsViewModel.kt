package com.example.dogtranslate.ui.sounds

import androidx.lifecycle.MutableLiveData
import com.example.dogtranslate.ui.base.BaseViewModel
import javax.inject.Inject

class SoundsViewModel @Inject constructor(

) : BaseViewModel() {
    var isLoading = MutableLiveData<Boolean>()
    init {

    }
}