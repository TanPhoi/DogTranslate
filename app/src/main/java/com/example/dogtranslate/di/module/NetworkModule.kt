package com.example.dogtranslate.di.module

import android.os.Build
import com.example.dogtranslate.BuildConfig
import com.example.dogtranslate.data.remote.NetworkInterceptor
import com.example.dogtranslate.data.remote.service.TrainingService
import com.example.dogtranslate.util.AppConfig
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class NetworkModule {
    companion object {
        private const val BASE_URL_NEW = "https://dogapi.dog/api/v2/"
    }

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_NEW)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    @Singleton
    @Provides
    fun provideTrainingService(retrofit: Retrofit): TrainingService =
        retrofit.create(TrainingService::class.java)

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG){
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        }
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(NetworkInterceptor())
            .connectTimeout(AppConfig.Constant.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(AppConfig.Constant.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .build()
    }
}