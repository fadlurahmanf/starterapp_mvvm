package com.fadlurahmanf.starterappmvvm.others.network

import com.fadlurahmanf.starterappmvvm.BuildConfig
import com.fadlurahmanf.starterappmvvm.others.network.api.IdentityApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit

class NetworkUtilities {
    private val merchantBaseUrl = BuildConfig.BASE_MERCHANT_URL

    fun okHttpClientBuilder(): OkHttpClient.Builder {
        return OkHttpClient.Builder()
    }

    fun createIdentityNetwork(okHttpClient: OkHttpClient): IdentityApi {
        return Retrofit.Builder().baseUrl("${merchantBaseUrl}/identity-service")
            .client(okHttpClient)
            .build()
            .create(IdentityApi::class.java)
    }
}