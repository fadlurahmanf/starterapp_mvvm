package com.fadlurahmanf.starterappmvvm.others.network

import com.fadlurahmanf.starterappmvvm.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit

class NetworkUtilities {
    private val merchantBaseUrl = BuildConfig.BASE_MERCHANT_URL

    fun createIdentityNetwork(): Retrofit {
        return Retrofit.Builder().baseUrl("${merchantBaseUrl}/identity-service")
            .client(OkHttpClient.Builder().build()).build()
    }
}