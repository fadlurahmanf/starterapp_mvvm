package com.fadlurahmanf.starterappmvvm.core.notification

import com.github.fadlurahmanfdev.core_notification.data.repositories.NotificationRepository
import com.github.fadlurahmanfdev.core_notification.data.repositories.NotificationRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class NotificationModule {
    @Provides
    fun provideNotificationService(): NotificationRepository {
        return NotificationRepositoryImpl()
    }
}