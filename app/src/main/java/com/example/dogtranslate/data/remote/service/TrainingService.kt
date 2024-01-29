package com.example.dogtranslate.data.remote.service

import com.example.dogtranslate.data.remote.model.TrainingData
import retrofit2.http.GET
import retrofit2.http.Query

interface TrainingService {

    @GET("breeds")
    suspend fun getAllTraining(): TrainingData

}