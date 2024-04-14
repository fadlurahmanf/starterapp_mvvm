package com.fadlurahmanf.starterappmvvm.core.notification.domain

import android.app.Activity
import android.app.PendingIntent
import android.content.Context
import android.content.pm.PackageManager

interface NotificationRepository {
    fun areNotificationEnabledAndGranted(context: Context): Boolean
    /**
     * Determine whether <em>you</em> have been granted a particular permission.
     * @return isResult: [PackageManager.PERMISSION_GRANTED] if you have the
     * permission, or [PackageManager.PERMISSION_DENIED] if not.
     */
    fun askNotificationPermission(
        activity: Activity,
        onShouldShowRequestPermissionRationale: () -> Unit,
        onCompleteCheckPermission: (isGranted: Boolean, result: Int) -> Unit
    )

    fun showNotification(
        context: Context,
        id: Int,
        title: String,
        message: String,
        pendingIntent: PendingIntent? = null,
    )

    // chat with different sound
    fun showChatNotification(
        context: Context,
        id: Int,
        title: String,
        message: String,
        pendingIntent: PendingIntent? = null,
    )

    fun showImageNotification(
        context: Context,
        id: Int,
        title: String,
        message: String,
        imageUrl: String,
    )

    fun cancelNotification(context: Context, id: Int)
}