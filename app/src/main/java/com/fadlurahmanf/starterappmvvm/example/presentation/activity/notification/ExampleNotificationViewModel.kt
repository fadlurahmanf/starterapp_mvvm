package com.fadlurahmanf.starterappmvvm.example.presentation.activity.notification

import android.app.PendingIntent
import android.content.Context
import com.fadlurahmanf.starterappmvvm.call.domain.CallNotificationService
import com.fadlurahmanf.starterappmvvm.core.layout.BaseViewModel
import com.fadlurahmanf.starterappmvvm.core.notification.domain.NotificationService
import javax.inject.Inject

class ExampleNotificationViewModel @Inject constructor(
    private val notificationService: NotificationService,
    private val callNotificationService: CallNotificationService,
) : BaseViewModel() {

    fun checkIsPermissionGranted(context: Context) =
        notificationService.areNotificationEnabledAndGranted(context)

    fun askNotificationPermission(
        activity: ExampleNotificationActivity, onGranted: () -> Unit,
        onShouldShowRequestPermissionRationale: () -> Unit,
        onLaunchPermission: () -> Unit,
    ) = notificationService.askNotificationPermissionPermission(
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
        notificationService.showNotification(context, id, title, message, pendingIntent)

    fun showChatNotification(
        context: Context,
        id: Int,
        title: String,
        message: String
    ) =
        notificationService.showChatNotification(context, id, title, message, null)

    fun showImageNotification(
        context: Context,
        id: Int,
        title: String,
        message: String,
        imageUrl: String,
    ) =
        notificationService.showImageNotification(context, id, title, message, imageUrl)

    fun showIncomingCallNotification(
        context: Context,
        id: Int,
        callerName: String,
        phoneNumberCaller: String
    ) = callNotificationService.showIncomingCallNotification(
        context,
        id,
        callerName,
        phoneNumberCaller
    )

    fun cancelIncomingCallNotification(context: Context, id: Int) =
        callNotificationService.cancelIncomingCallNotification(context, id)
}