package com.fadlurahmanf.starterappmvvm.core.notification.domain

import android.app.Activity
import android.app.PendingIntent
import android.content.Context

interface NotificationService {
    fun areNotificationEnabledAndGranted(context: Context): Boolean
    fun askNotificationPermissionPermission(
        activity: Activity,
        onGranted: () -> Unit,
        onShouldShowRequestPermissionRationale: () -> Unit,
        onLaunchPermission: () -> Unit
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