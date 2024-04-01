package com.fadlurahmanf.starterappmvvm.core.di.modules

import com.fadlurahmanf.starterappmvvm.core.network.api.IdentityApi
import com.fadlurahmanf.starterappmvvm.core.network.datasources.GuestIdentityDatasourceImpl
import dagger.Module
import dagger.Provides

@Module(includes = [ApiModule::class])
class NetworkDatasourceModule {

    @Provides
    fun provideIdentityDatasourceModule(identityApi: IdentityApi): GuestIdentityDatasourceImpl {
        return GuestIdentityDatasourceImpl(identityApi)
    }
}