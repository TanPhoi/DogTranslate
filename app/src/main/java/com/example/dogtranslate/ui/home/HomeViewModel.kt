package com.example.dogtranslate.ui.home

import androidx.lifecycle.viewModelScope
import com.example.dogtranslate.data.local.AppPreferences
import com.example.dogtranslate.data.local.LocalRepository
import com.example.dogtranslate.data.local.model.Rating
import com.example.dogtranslate.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val localRepository: LocalRepository,
    private val appPreferences: AppPreferences
) : BaseViewModel() {
    init {

    }

    fun insertRating(rating: Rating) {
        viewModelScope.launch(Dispatchers.IO) {

            localRepository.insertRating(rating)
        }
    }

    fun saveRatingPreference(rating: Boolean) {
        appPreferences.saveRatingPreference(rating)
    }

    fun getRating(): Boolean {
        return appPreferences.getRating()
    }
}