package com.fadlurahmanf.starterappmvvm.example.presentation.activity.notification

import android.app.PendingIntent
import android.content.Context
import com.fadlurahmanf.starterappmvvm.call.domain.repository.CallNotificationRepository
import com.fadlurahmanf.starterappmvvm.core.layout.BaseViewModel
import com.fadlurahmanf.starterappmvvm.core.notification.domain.NotificationRepository
import javax.inject.Inject

class ExampleNotificationViewModel @Inject constructor(
    private val notificationRepository: NotificationRepository,
    private val callNotificationRepository: CallNotificationRepository,
) : BaseViewModel() {

    fun checkIsPermissionGranted(context: Context) =
        notificationRepository.areNotificationEnabledAndGranted(context)

    fun askNotificationPermission(
        activity: ExampleNotificationActivity, onGranted: () -> Unit,
        onShouldShowRequestPermissionRationale: () -> Unit,
        onLaunchPermission: () -> Unit,
    ) = notificationRepository.askNotificationPermissionPermission(
        activity,
        onGranted,
        onShouldShowRequestPermissionRationale,
        onLaunchPermission
    )

    fun showSimpleNotification(
        context: Context,
        id: Int,
        title: String,
        message: String,
        pendingIntent: PendingIntent
    ) =
        notificationRepository.showNotification(context, id, title, message, pendingIntent)

    fun showChatNotification(
        context: Context,
        id: Int,
        title: String,
        message: String
    ) =
        notificationRepository.showChatNotification(context, id, title, message, null)

    fun showImageNotification(
        context: Context,
        id: Int,
        title: String,
        message: String,
        imageUrl: String,
    ) =
        notificationRepository.showImageNotification(context, id, title, message, imageUrl)

    fun showIncomingCallNotification(
        context: Context,
        id: Int,
        callerName: String,
        phoneNumberCaller: String
    ) = callNotificationRepository.showIncomingCallNotification(
        context,
        id,
        callerName,
        phoneNumberCaller
    )

    fun cancelIncomingCallNotification(context: Context, id: Int) =
        callNotificationRepository.cancelIncomingCallNotification(context, id)
}