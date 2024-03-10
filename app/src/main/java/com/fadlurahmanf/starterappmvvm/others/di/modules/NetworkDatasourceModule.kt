package com.fadlurahmanf.starterappmvvm.others.di.modules

import com.fadlurahmanf.starterappmvvm.others.network.api.IdentityApi
import com.fadlurahmanf.starterappmvvm.others.network.datasources.GuestIdentityDatasourceImpl
import dagger.Module
import dagger.Provides

@Module(includes = [ApiModule::class])
class NetworkDatasourceModule {

    @Provides
    fun provideIdentityDatasourceModule(identityApi: IdentityApi): GuestIdentityDatasourceImpl {
        return GuestIdentityDatasourceImpl(identityApi)
    }
}