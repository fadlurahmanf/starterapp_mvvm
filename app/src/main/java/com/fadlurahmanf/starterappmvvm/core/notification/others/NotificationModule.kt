package com.fadlurahmanf.starterappmvvm.core.notification.others

import com.fadlurahmanf.starterappmvvm.core.notification.data.NotificationRepository
import com.fadlurahmanf.starterappmvvm.core.notification.data.NotificationRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class NotificationModule {
    @Provides
    fun provideNotificationService(): NotificationRepository {
        return NotificationRepositoryImpl()
    }
}