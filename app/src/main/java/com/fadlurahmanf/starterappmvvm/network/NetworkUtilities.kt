package com.fadlurahmanf.starterappmvvm.network

import com.fadlurahmanf.starterappmvvm.BuildConfig
import com.fadlurahmanf.starterappmvvm.network.api.IdentityApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkUtilities {
    private val merchantBaseUrl = BuildConfig.BASE_MERCHANT_URL

    fun okHttpClientBuilder(): OkHttpClient.Builder {
        return OkHttpClient.Builder()
    }

    fun createGuestIdentityNetwork(okHttpClient: OkHttpClient): IdentityApi {
        return Retrofit.Builder().baseUrl("${merchantBaseUrl}/identity-service/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(IdentityApi::class.java)
    }
}