package com.fadlurahmanf.starterappmvvm.call.di

import com.fadlurahmanf.starterappmvvm.call.domain.repository.CallNotificationRepository
import com.fadlurahmanf.starterappmvvm.call.domain.repository.CallNotificationRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class CallModule {
    @Provides
    fun provideCallNotificationService(): CallNotificationRepository {
        return CallNotificationRepositoryImpl()
    }
}