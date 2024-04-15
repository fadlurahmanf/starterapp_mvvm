package com.fadlurahmanf.starterappmvvm.example.presentation.activity.notification

import android.app.PendingIntent
import android.content.Context
import com.fadlurahmanf.starterappmvvm.call.domain.repository.CallNotificationRepository
import com.fadlurahmanf.starterappmvvm.core.shared.layout.BaseViewModel
import com.fadlurahmanf.starterappmvvm.core.notification.data.repositories.NotificationRepository
import com.fadlurahmanf.starterappmvvm.example.data.repositories.ExampleNotificationRepository
import javax.inject.Inject

class ExampleNotificationViewModel @Inject constructor(
    private val callNotificationRepository: CallNotificationRepository,
    private val exampleNotificationRepository: ExampleNotificationRepository,
    private val notificationRepository: NotificationRepository,
) : BaseViewModel() {

    fun askNotificationPermission(
        activity: ExampleNotificationActivity,
        onShouldShowRequestPermissionRationale: () -> Unit,
        onCompleteCheckPermission: (isGranted: Boolean, result: Int) -> Unit
    ) = exampleNotificationRepository.askNotificationPermission(
        activity,
        onShouldShowRequestPermissionRationale,
        onCompleteCheckPermission
    )

    fun showNotification(
        context: Context,
        id: Int,
        title: String,
        message: String,
        pendingIntent: PendingIntent
    ) =
        exampleNotificationRepository.showNotification(
            context,
            id,
            title = title,
            message = message,
            pendingIntent = pendingIntent,
        )

    fun showImageNotification(
        context: Context,
        id: Int,
        title: String,
        message: String,
        imageUrl: String,
    ) =
        exampleNotificationRepository.showImageNotification(
            context,
            id,
            title = title,
            message = message,
            networkImage = imageUrl,
        )

    fun showCustomSoundNotification(
        context: Context,
        id: Int,
        title: String,
        message: String
    ) = exampleNotificationRepository.showChatNotification(
        context,
        id = id,
        title = title,
        message = message,
        pendingIntent = null,
    )

    fun showGroupedNotification(
        context: Context,
        id: Int,
    ) = exampleNotificationRepository.showGroupedNotification(context, id = id)

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