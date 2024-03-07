package com.fadlurahmanf.starterappmvvm.others.di.modules

import com.fadlurahmanf.starterappmvvm.others.network.NetworkUtilities
import com.fadlurahmanf.starterappmvvm.others.network.api.IdentityApi
import dagger.Module
import dagger.Provides

@Module
class NetworkModule {

    private val networkUtilities = NetworkUtilities()

    @Provides
    fun provideIdentityNetwork(): IdentityApi {
        return networkUtilities.createIdentityNetwork(
            networkUtilities.okHttpClientBuilder().build()
        )
    }
}