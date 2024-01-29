package com.example.dogtranslate.data.local

import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppPreferences @Inject constructor(private val preferences: SharedPreferences) {
    fun saveLanguagePreference(languageCode: String) {
        preferences.edit().putString("language", languageCode).apply()
    }

    fun getLocale(): String {
        return preferences.getString("language", "en") ?: ""
    }

    fun saveRatingPreference(rating: Boolean) {
        preferences.edit().putBoolean("rating", rating).apply()
    }

    fun getRating(): Boolean {
        return preferences.getBoolean("rating", false) ?: false
    }

    fun saveFirstScreen(firstScreen: Boolean) {
        preferences.edit().putBoolean("first_screen", firstScreen).apply()
    }

    fun getFirstScreen(): Boolean {
        return preferences.getBoolean("first_screen", false) ?: false
    }


}