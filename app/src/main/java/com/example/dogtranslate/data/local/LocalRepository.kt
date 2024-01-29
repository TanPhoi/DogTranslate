package com.example.dogtranslate.data.local

import com.example.dogtranslate.data.local.dao.RatingDao
import com.example.dogtranslate.data.local.model.Dog
import com.example.dogtranslate.data.local.model.Rating
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalRepository @Inject constructor(
    private val ratingDao: RatingDao
) {

    suspend fun insertRating(rating : Rating){
        ratingDao.insertRating(rating)
    }
}