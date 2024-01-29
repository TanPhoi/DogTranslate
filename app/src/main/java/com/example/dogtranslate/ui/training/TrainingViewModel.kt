package com.example.dogtranslate.ui.training

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.dogtranslate.data.local.AppPreferences
import com.example.dogtranslate.data.remote.RemoteRepository
import com.example.dogtranslate.data.remote.model.TrainingData
import com.example.dogtranslate.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class TrainingViewModel @Inject constructor(
    private val remoteRepository: RemoteRepository
) : BaseViewModel() {
    var trainingList = MutableLiveData<TrainingData>()

    init {
        getAllTraining()
    }

    private fun getAllTraining() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val training = remoteRepository.getAllTraining()
                trainingList.postValue(training)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}