package com.fadlurahmanf.starterappmvvm.storage.others.di

import android.content.Context
import com.fadlurahmanf.starterappmvvm.storage.data.datasources.AppExampleLocalDatasource
import com.fadlurahmanf.starterappmvvm.storage.data.datasources.AppExampleLocalDatasourceImpl
import dagger.Module
import dagger.Provides

@Module
class StorageModule {

    @Provides
    fun provideAppExampleLocalDatasource(context: Context): AppExampleLocalDatasource {
        return AppExampleLocalDatasourceImpl(context)
    }
}