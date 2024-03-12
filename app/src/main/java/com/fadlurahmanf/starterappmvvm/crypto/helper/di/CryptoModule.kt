package com.fadlurahmanf.starterappmvvm.crypto.helper.di

import com.fadlurahmanf.starterappmvvm.crypto.data.repositories.CryptoAESRepository
import com.fadlurahmanf.starterappmvvm.crypto.data.repositories.CryptoAESRepositoryImpl
import com.fadlurahmanf.starterappmvvm.crypto.data.repositories.CryptoED25119Repository
import com.fadlurahmanf.starterappmvvm.crypto.data.repositories.CryptoED25119RepositoryImpl
import com.fadlurahmanf.starterappmvvm.crypto.data.repositories.CryptoRSARSARepositoryImpl
import com.fadlurahmanf.starterappmvvm.crypto.data.repositories.CryptoRSARepository
import dagger.Module
import dagger.Provides

@Module
class CryptoModule {

    @Provides
    fun provideCryptoRSARepository(): CryptoRSARepository {
        return CryptoRSARSARepositoryImpl()
    }

    @Provides
    fun provideCryptoAESRepository(): CryptoAESRepository {
        return CryptoAESRepositoryImpl()
    }

    @Provides
    fun provideCryptoED25119Repository(): CryptoED25119Repository {
        return CryptoED25119RepositoryImpl()
    }
}