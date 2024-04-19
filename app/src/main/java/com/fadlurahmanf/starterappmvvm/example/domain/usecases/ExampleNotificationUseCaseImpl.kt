package com.fadlurahmanf.starterappmvvm.example.domain.usecases

import android.app.PendingIntent
import android.content.Context
import android.util.Log
import com.fadlurahmanf.starterappmvvm.example.data.repositories.ExampleNotificationRepository

class ExampleNotificationUseCaseImpl(
    private val exampleNotificationRepository: ExampleNotificationRepository
) : ExampleNotificationUseCase {

    override fun askNotificationPermission(
        context: Context,
        onCompleteCheckPermission: (isGranted: Boolean) -> Unit
    ) = exampleNotificationRepository.askNotificationPermission(context, onCompleteCheckPermission)

    override fun showNotification(
        context: Context,
        id: Int,
        title: String,
        message: String,
        pendingIntent: PendingIntent?
    ) = exampleNotificationRepository.showNotification(
        context,
        id = id,
        title = title,
        message = message,
        pendingIntent = pendingIntent,
    )

    override fun showImageNotification(
        context: Context,
        id: Int,
        title: String,
        message: String,
        networkImage: String,
        pendingIntent: PendingIntent?
    ) = exampleNotificationRepository.showImageNotification(
        context,
        id = id,
        title = title,
        message = message,
        networkImage = networkImage,
        pendingIntent = null,
    )

    override fun showCustomSoundNotification(
        context: Context,
        id: Int,
        title: String,
        message: String,
        pendingIntent: PendingIntent?
    ) = exampleNotificationRepository.showChatNotification(
        context,
        id = id,
        title = title,
        message = message
    )
}