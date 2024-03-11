package com.fadlurahmanf.starterappmvvm.others.di.modules

import com.fadlurahmanf.starterappmvvm.network.NetworkUtilities
import com.fadlurahmanf.starterappmvvm.network.api.IdentityApi
import dagger.Module
import dagger.Provides

@Module
class ApiModule {

    private val networkUtilities = NetworkUtilities()

    @Provides
    fun provideIdentityNetwork(): IdentityApi {
        return networkUtilities.createGuestIdentityNetwork(
            networkUtilities.okHttpClientBuilder().build()
        )
    }
}