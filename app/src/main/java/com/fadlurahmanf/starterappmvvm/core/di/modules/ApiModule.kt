package com.fadlurahmanf.starterappmvvm.core.di.modules

import android.content.Context
import com.fadlurahmanf.starterappmvvm.BuildConfig
import com.fadlurahmanf.starterappmvvm.core.network.others.NetworkUtilities
import com.fadlurahmanf.starterappmvvm.core.network.api.IdentityApi
import dagger.Module
import dagger.Provides

@Module
class ApiModule {

    private val networkUtilities = NetworkUtilities()

    @Provides
    fun provideIdentityNetwork(context: Context): IdentityApi {
        return networkUtilities.createGuestIdentityNetwork(
            networkUtilities.okHttpClientBuilder()
                .addInterceptor(networkUtilities.getChuckerInterceptor(context).build())
                .build()
        )
    }
}