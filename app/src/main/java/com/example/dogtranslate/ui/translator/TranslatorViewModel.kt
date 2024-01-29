package com.example.dogtranslate.ui.translator

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.dogtranslate.data.local.LocalRepository
import com.example.dogtranslate.data.local.model.Dog
import com.example.dogtranslate.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class TranslatorViewModel @Inject constructor(
) : BaseViewModel() {
    var isLoading = MutableLiveData<Boolean>()
    init {
    }

}