package com.fadlurahmanf.starterappmvvm.example.presentation.activity.notification

import android.app.PendingIntent
import android.content.Context
import com.fadlurahmanf.starterappmvvm.core.layout.BaseViewModel
import com.fadlurahmanf.starterappmvvm.core.notification.domain.NotificationService
import javax.inject.Inject

class ExampleNotificationViewModel @Inject constructor(
    private val notificationService: NotificationService
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

    fun showSimpleNotification(context: Context, id: Int, title: String, message: String, pendingIntent: PendingIntent) =
        notificationService.showNotification(context, id, title, message, pendingIntent)
}