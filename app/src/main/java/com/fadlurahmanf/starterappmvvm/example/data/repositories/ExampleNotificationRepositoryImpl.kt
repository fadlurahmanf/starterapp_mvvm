package com.fadlurahmanf.starterappmvvm.example.data.repositories

import android.app.Activity
import android.app.PendingIntent
import android.content.Context
import android.media.RingtoneManager
import android.net.Uri
import com.fadlurahmanf.starterappmvvm.BuildConfig
import com.fadlurahmanf.starterappmvvm.R
import com.fadlurahmanf.starterappmvvm.core.notification.data.NotificationRepository
import com.fadlurahmanf.starterappmvvm.core.notification.data.NotificationRepositoryImpl
import com.fadlurahmanf.starterappmvvm.core.notification.data.dto.model.ItemGroupedNotificationModel
import javax.inject.Inject

class ExampleNotificationRepositoryImpl @Inject constructor(
    private val notificationRepository: NotificationRepository
) : ExampleNotificationRepository {
    companion object {
        const val GENERAL_CHANNEL_ID = NotificationRepositoryImpl.GENERAL_CHANNEL_ID
        const val GENERAL_CHANNEL_NAME = NotificationRepositoryImpl.GENERAL_CHANNEL_NAME
        const val GENERAL_CHANNEL_DESCRIPTION =
            NotificationRepositoryImpl.GENERAL_CHANNEL_DESCRIPTION
        const val CHAT_CHANNEL_ID = "CHAT"
        const val CHAT_CHANNEL_NAME = "Percapakan"
        const val CHAT_CHANNEL_DESCRIPTION =
            "Notifikasi Percakapan"
    }

    private fun createGeneralNotificationChannel(context: Context) {
        val sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        notificationRepository.createNotificationChannel(
            context,
            channelId = GENERAL_CHANNEL_ID,
            channelName = GENERAL_CHANNEL_NAME,
            channelDescription = GENERAL_CHANNEL_DESCRIPTION,
            sound = sound
        )
    }

    override fun askNotificationPermission(
        activity: Activity,
        onShouldShowRequestPermissionRationale: () -> Unit,
        onCompleteCheckPermission: (isGranted: Boolean, result: Int) -> Unit,
    ) = notificationRepository.askNotificationPermission(
        activity = activity,
        onShouldShowRequestPermissionRationale = onShouldShowRequestPermissionRationale,
        onCompleteCheckPermission = onCompleteCheckPermission,
    )

    override fun showNotification(
        context: Context,
        id: Int,
        title: String,
        message: String,
        pendingIntent: PendingIntent?
    ) {
        createGeneralNotificationChannel(context)
        notificationRepository.showCustomNotification(
            context,
            id = id,
            channelId = GENERAL_CHANNEL_ID,
            title = title,
            message = message,
            smallIcon = R.drawable.il_logo_bankmas,
            pendingIntent = pendingIntent,
        )
    }

    override fun showImageNotification(
        context: Context,
        id: Int,
        title: String,
        message: String,
        networkImage: String,
        pendingIntent: PendingIntent?
    ) {
        createGeneralNotificationChannel(context)
        notificationRepository.showGeneralImageNotification(
            context,
            id = id,
            title = title,
            message = message,
            imageUrl = networkImage,
            smallIcon = R.drawable.il_logo_bankmas,
        )
    }

    override fun showChatNotification(
        context: Context,
        id: Int,
        title: String,
        message: String,
        pendingIntent: PendingIntent?
    ) {
        val sound =
            Uri.parse("android.resource://" + context.packageName + "/" + R.raw.pop_up_alert_notification)
        notificationRepository.createNotificationChannel(
            context,
            channelId = CHAT_CHANNEL_ID,
            channelName = CHAT_CHANNEL_NAME,
            channelDescription = CHAT_CHANNEL_DESCRIPTION,
            sound = sound,
        )
        notificationRepository.showCustomNotification(
            context,
            id = id,
            channelId = CHAT_CHANNEL_ID,
            title = title,
            message = message,
            smallIcon = R.drawable.il_logo_bankmas,
            pendingIntent = pendingIntent,
        )
    }

    override fun showGroupedNotification(
        context: Context,
        id: Int,
    ) {
        val sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        notificationRepository.createNotificationChannel(
            context,
            channelId = GENERAL_CHANNEL_ID,
            channelName = GENERAL_CHANNEL_NAME,
            channelDescription = GENERAL_CHANNEL_DESCRIPTION,
            sound = sound,
        )
        notificationRepository.showGroupedNotification(
            context,
            id = id,
            channelId = GENERAL_CHANNEL_ID,
            smallIcon = R.drawable.il_logo_bankmas,
            groupKey = "${BuildConfig.APPLICATION_ID}.CHAT",
            bigContentTitle = "Big Content Title",
            summaryText = "Summary Text",
            itemLine = listOf(
                "Item Line 1", "Item Line 2"
            ),
            itemNotifications = listOf(
                ItemGroupedNotificationModel(
                    id = 1,
                    title = "Item 1",
                    message = "Message 1"
                ),
                ItemGroupedNotificationModel(
                    id = 2,
                    title = "Item 2",
                    message = "Message 2"
                ),
            )
        )
    }
}