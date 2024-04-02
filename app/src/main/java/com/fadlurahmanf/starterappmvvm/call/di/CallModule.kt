package com.fadlurahmanf.starterappmvvm.call.di

import com.fadlurahmanf.starterappmvvm.call.domain.CallNotificationService
import com.fadlurahmanf.starterappmvvm.call.domain.CallNotificationServiceImpl
import com.fadlurahmanf.starterappmvvm.core.notification.domain.NotificationService
import com.fadlurahmanf.starterappmvvm.core.notification.others.NotificationModule
import dagger.Module
import dagger.Provides

@Module(includes = [NotificationModule::class])
class CallModule {
    @Provides
    fun provideCallNotificationService(notificationService: NotificationService): CallNotificationService {
        return CallNotificationServiceImpl(notificationService)
    }
}