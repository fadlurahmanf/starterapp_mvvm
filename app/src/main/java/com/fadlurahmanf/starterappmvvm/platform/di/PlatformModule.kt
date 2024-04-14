package com.fadlurahmanf.starterappmvvm.platform.di

import com.fadlurahmanf.starterappmvvm.platform.data.repositories.PlatformRepository
import com.fadlurahmanf.starterappmvvm.platform.data.repositories.PlatformRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class PlatformModule {

    @Provides
    fun providePlatformRepository(): PlatformRepository {
        return PlatformRepositoryImpl()
    }
}