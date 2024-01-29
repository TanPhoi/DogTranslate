package com.example.dogtranslate.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.dogtranslate.data.local.model.Rating

@Dao
interface RatingDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertRating(rating : Rating)
}