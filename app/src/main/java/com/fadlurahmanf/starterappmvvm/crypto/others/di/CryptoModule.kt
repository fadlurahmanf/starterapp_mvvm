package com.fadlurahmanf.starterappmvvm.crypto.others.di

import com.fadlurahmanf.starterappmvvm.crypto.data.repositories.CryptoAESRepository
import com.fadlurahmanf.starterappmvvm.crypto.data.repositories.CryptoAESRepositoryImpl
import com.fadlurahmanf.starterappmvvm.crypto.data.repositories.CryptoAESV2Repository
import com.fadlurahmanf.starterappmvvm.crypto.data.repositories.CryptoAESV2RepositoryImpl
import com.fadlurahmanf.starterappmvvm.crypto.data.repositories.CryptoED25119Repository
import com.fadlurahmanf.starterappmvvm.crypto.data.repositories.CryptoED25119RepositoryImpl
import com.fadlurahmanf.starterappmvvm.crypto.data.repositories.CryptoRSARepositoryImpl
import com.fadlurahmanf.starterappmvvm.crypto.data.repositories.CryptoRSARepository
import com.fadlurahmanf.starterappmvvm.crypto.data.repositories.CryptoRSAV2Repository
import com.fadlurahmanf.starterappmvvm.crypto.data.repositories.CryptoRSAV2RepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class CryptoModule {

    @Provides
    fun provideCryptoRSARepository(): CryptoRSARepository {
        return CryptoRSARepositoryImpl()
    }

    @Provides
    fun provideCryptoRSAV2Repository(): CryptoRSAV2Repository {
        return CryptoRSAV2RepositoryImpl()
    }

    @Provides
    fun provideCryptoAESRepository(): CryptoAESRepository {
        return CryptoAESRepositoryImpl()
    }

    @Provides
    fun provideCryptoAESV2Repository(): CryptoAESV2Repository {
        return CryptoAESV2RepositoryImpl()
    }

    @Provides
    fun provideCryptoED25119Repository(): CryptoED25119Repository {
        return CryptoED25119RepositoryImpl()
    }
}