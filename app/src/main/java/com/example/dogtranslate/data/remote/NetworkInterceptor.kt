package com.example.dogtranslate.data.remote

import android.content.Context
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class NetworkInterceptor : Interceptor {

    @Inject
    lateinit var context: Context

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("CLIENTAPIKEY", "f35d9d6a-ac79-48a6-9e26-f11872d08baf")
            .build()
        return chain.proceed(request)
    }
}