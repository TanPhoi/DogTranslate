package com.example.dogtranslate.ui.language

import android.content.Context
import android.content.res.Configuration
import android.os.Build
import java.util.Locale

object LocaleHelper {
    fun setLocale(context: Context, languageCode: String) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)
        val configuration = Configuration()
        configuration.setLocale(locale)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            context.resources.updateConfiguration(configuration, context.resources.displayMetrics)
        } else {
            context.createConfigurationContext(configuration)
        }
    }
}