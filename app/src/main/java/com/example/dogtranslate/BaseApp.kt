package com.example.dogtranslate

import com.example.dogtranslate.di.AppComponent
import com.example.dogtranslate.di.DaggerAppComponent
import com.squareup.leakcanary.BuildConfig
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber

class BaseApp : DaggerApplication() {

    lateinit var instance: BaseApp

    override fun onCreate() {
        super.onCreate()
        instance = this
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val component: AppComponent = DaggerAppComponent.builder().application(this).build()
        component.inject(this)
        return component
    }
}