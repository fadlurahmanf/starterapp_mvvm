package com.fadlurahmanf.starterappmvvm.core.di.modules

import com.fadlurahmanf.starterappmvvm.core.network.NetworkUtilities
import com.fadlurahmanf.starterappmvvm.core.network.api.IdentityApi
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