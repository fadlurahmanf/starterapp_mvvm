package com.fadlurahmanf.starterappmvvm.core.notification.others

import com.fadlurahmanf.starterappmvvm.core.notification.domain.NotificationService
import com.fadlurahmanf.starterappmvvm.core.notification.domain.NotificationServiceImpl
import dagger.Module
import dagger.Provides

@Module
class NotificationModule {
    @Provides
    fun provideNotificationService(): NotificationService {
        return NotificationServiceImpl()
    }
}