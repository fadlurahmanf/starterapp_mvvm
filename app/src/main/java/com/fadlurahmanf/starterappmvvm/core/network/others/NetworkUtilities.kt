package com.fadlurahmanf.starterappmvvm.core.network.others

import android.content.Context
import com.chuckerteam.chucker.api.BodyDecoder
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import com.fadlurahmanf.starterappmvvm.BuildConfig
import com.fadlurahmanf.starterappmvvm.core.network.api.IdentityApi
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okio.ByteString
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkUtilities {
    private val merchantBaseUrl = BuildConfig.BASE_MERCHANT_URL

    fun getChuckerInterceptor(context: Context): ChuckerInterceptor.Builder {
        val chuckerCollector = ChuckerCollector(
            context = context,
            showNotification = false,
            retentionPeriod = RetentionManager.Period.ONE_DAY
        )

        return ChuckerInterceptor.Builder(context)
            .collector(chuckerCollector)
            .maxContentLength(Long.MAX_VALUE)
            .alwaysReadResponseBody(true)
            .createShortcut(false)
    }

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