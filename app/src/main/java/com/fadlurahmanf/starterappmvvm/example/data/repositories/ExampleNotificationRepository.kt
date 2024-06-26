package com.fadlurahmanf.starterappmvvm.example.data.repositories

import android.app.Activity
import android.app.PendingIntent
import android.content.Context

interface ExampleNotificationRepository {
    fun askNotificationPermission(
        context: Context,
        onCompleteCheckPermission: (isGranted: Boolean) -> Unit,
    )

    fun createGeneralNotificationChannel(context: Context)
    fun createChatNotificationChannel(context: Context)

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