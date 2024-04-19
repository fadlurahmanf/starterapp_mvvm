package com.fadlurahmanf.starterappmvvm.example.domain.usecases

import android.app.PendingIntent
import android.content.Context

interface ExampleNotificationUseCase {
    fun askNotificationPermission(
        context: Context,
        onCompleteCheckPermission: (isGranted: Boolean) -> Unit
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

    fun showCustomSoundNotification(
        context: Context,
        id: Int,
        title: String,
        message: String,
        pendingIntent: PendingIntent? = null,
    )
}