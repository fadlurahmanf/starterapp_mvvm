package com.fadlurahmanf.starterappmvvm.example.data.repositories

import android.app.Activity
import android.app.PendingIntent
import android.content.Context

interface ExampleNotificationRepository {
    fun askNotificationPermission(
        activity: Activity,
        onShouldShowRequestPermissionRationale: () -> Unit,
        onCompleteCheckPermission: (isGranted: Boolean, result: Int) -> Unit,
    )

    fun showNotification(
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
        networkImage: String,
        pendingIntent: PendingIntent? = null,
    )

    /** example for notification with sound */
    fun showChatNotification(
        context: Context,
        id: Int,
        title: String,
        message: String,
        pendingIntent: PendingIntent? = null
    )

    fun showGroupedNotification(
        context: Context,
        id: Int,
    )
}