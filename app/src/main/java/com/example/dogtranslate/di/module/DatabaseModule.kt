package com.example.dogtranslate.di.module

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import androidx.room.Room
import com.example.dogtranslate.data.local.LocalDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(appContext: Context): LocalDatabase {
        return Room
            .databaseBuilder(
                appContext.applicationContext,
                LocalDatabase::class.java,
                "localDb"
            )
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideRatingDao(db: LocalDatabase) =
        db.ratingDao()

    @Singleton
    @Provides
    fun provideAppPreference(appContext: Context): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(appContext)
    }

}