package com.example.dogtranslate.data.remote

import com.example.dogtranslate.data.remote.model.TrainingData
import com.example.dogtranslate.data.remote.service.TrainingService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteRepository @Inject constructor(
    private val trainingService: TrainingService
) {

    suspend fun getAllTraining() : TrainingData{
        return trainingService.getAllTraining()
    }
}