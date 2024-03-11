package com.fadlurahmanf.starterappmvvm.others.di.modules

import com.fadlurahmanf.starterappmvvm.network.api.IdentityApi
import com.fadlurahmanf.starterappmvvm.network.datasources.GuestIdentityDatasourceImpl
import dagger.Module
import dagger.Provides

@Module(includes = [ApiModule::class])
class NetworkDatasourceModule {

    @Provides
    fun provideIdentityDatasourceModule(identityApi: IdentityApi): GuestIdentityDatasourceImpl {
        return GuestIdentityDatasourceImpl(identityApi)
    }
}