package com.example.dogtranslate.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.dogtranslate.data.local.dao.RatingDao
import com.example.dogtranslate.data.local.model.Dog
import com.example.dogtranslate.data.local.model.Rating

@Database(
    entities = [Rating::class],
    version = 1,
    exportSchema = false
)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun ratingDao(): RatingDao
}