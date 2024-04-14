package com.fadlurahmanf.starterappmvvm.storage.others.di

import android.content.Context
import com.fadlurahmanf.starterappmvvm.crypto.data.repositories.CryptoAESRepository
import com.fadlurahmanf.starterappmvvm.crypto.others.di.CryptoModule
import com.fadlurahmanf.starterappmvvm.storage.data.datasources.AppExampleLocalDatasource
import com.fadlurahmanf.starterappmvvm.storage.data.datasources.AppExampleLocalDatasourceImpl
import com.fadlurahmanf.starterappmvvm.storage.data.datasources.AppSharedPrefLocalDatasource
import com.fadlurahmanf.starterappmvvm.storage.data.datasources.AppSharedPrefLocalDatasourceImpl
import dagger.Module
import dagger.Provides

@Module(includes = [CryptoModule::class])
class StorageModule {

    @Provides
    fun provideAppExampleLocalDatasource(context: Context): AppExampleLocalDatasource {
        return AppExampleLocalDatasourceImpl(context)
    }

    @Provides
    fun provideAppSharedPrefLocalDatasource(
        context: Context,
        aesRepository: CryptoAESRepository
    ): AppSharedPrefLocalDatasource {
        return AppSharedPrefLocalDatasourceImpl(context, aesRepository)
    }
}