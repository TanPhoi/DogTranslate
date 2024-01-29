package com.example.dogtranslate.di

import android.app.Application
import com.example.dogtranslate.BaseApp
import com.example.dogtranslate.di.module.ActivityBindingModule
import com.example.dogtranslate.di.module.AppModule
import com.example.dogtranslate.di.module.DatabaseModule
import com.example.dogtranslate.di.module.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, AppModule::class, AndroidSupportInjectionModule::class, ActivityBindingModule::class, DatabaseModule::class])
interface AppComponent : AndroidInjector<BaseApp> {

    override fun inject(application: BaseApp)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}