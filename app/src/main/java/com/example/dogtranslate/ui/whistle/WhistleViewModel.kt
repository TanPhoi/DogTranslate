package com.example.dogtranslate.ui.whistle

import androidx.lifecycle.MutableLiveData
import com.example.dogtranslate.ui.base.BaseViewModel
import javax.inject.Inject

class WhistleViewModel @Inject constructor(

) : BaseViewModel() {
    var isLoading = MutableLiveData<Boolean>()
    init {

    }
}