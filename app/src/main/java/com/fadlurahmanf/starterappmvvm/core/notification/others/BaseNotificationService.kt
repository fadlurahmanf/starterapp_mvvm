package com.fadlurahmanf.starterappmvvm.core.notification.others

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import androidx.core.app.NotificationCompat
import com.fadlurahmanf.starterappmvvm.R

abstract class BaseNotificationService {
    private lateinit var notificationManager: NotificationManager

    companion object {
        const val GENERAL_CHANNEL_ID = "GENERAL"
        const val GENERAL_CHANNEL_NAME = "Umum"
        const val GENERAL_CHANNEL_DESCRIPTION = "Notifikasi Umum"
        const val CHAT_CHANNEL_ID = "CHAT"
        const val CHAT_CHANNEL_NAME = "Percakapan"
        const val CHAT_CHANNEL_DESCRIPTION = "Percakapan"
        const val VOIP_CHANNEL_ID = "VOIP"
        const val VOIP_CHANNEL_NAME = "Panggilan"
        const val VOIP_CHANNEL_DESCRIPTION = "Panggilan"
        const val MEDIA_CHANNEL_ID = "MEDIA"
        const val MEDIA_CHANNEL_NAME = "Media"
        const val MEDIA_CHANNEL_DESCRIPTION = "Media"
    }

    fun getNotificationManager(context: Context): NotificationManager {
        if (!this::notificationManager.isInitialized) {
            notificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        }
        return notificationManager
    }

    fun createGeneralNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val allChannels = getNotificationManager(context).notificationChannels
            var generalChannel: NotificationChannel? = null
            for (element in allChannels) {
                if (element.id == GENERAL_CHANNEL_ID) {
                    generalChannel = element
                    break
                }
            }
            if (generalChannel != null) return
            val soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            val channel = NotificationChannel(
                GENERAL_CHANNEL_ID,
                GENERAL_CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                this.description = GENERAL_CHANNEL_DESCRIPTION
                setSound(soundUri, null)
            }
            getNotificationManager(context).createNotificationChannel(channel)
        }
    }

    fun createChatNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val allChannels = getNotificationManager(context).notificationChannels
            var chatChannel: NotificationChannel? = null
            for (element in allChannels) {
                if (element.id == CHAT_CHANNEL_ID) {
                    chatChannel = element
                    break
                }
            }
            if (chatChannel != null) return
            val uriSound =
                Uri.parse("android.resource://" + context.packageName + "/" + R.raw.pop_up_alert_notification)
            val channel = NotificationChannel(
                CHAT_CHANNEL_ID,
                CHAT_CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                this.description = CHAT_CHANNEL_DESCRIPTION
                setSound(uriSound, null)
            }
            getNotificationManager(context).createNotificationChannel(channel)
        }
    }

    fun createVoipNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val allChannels = getNotificationManager(context).notificationChannels
            var voipChannel: NotificationChannel? = null
            for (element in allChannels) {
                if (element.id == VOIP_CHANNEL_ID) {
                    voipChannel = element
                    break
                }
            }
            if (voipChannel != null) return
            val channel = NotificationChannel(
                VOIP_CHANNEL_ID,
                VOIP_CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                this.description = VOIP_CHANNEL_DESCRIPTION
                setSound(null, null)
            }
            getNotificationManager(context).createNotificationChannel(channel)
        }
    }

    fun createMediaNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val allChannels = getNotificationManager(context).notificationChannels
            var mediaChannel: NotificationChannel? = null
            for (element in allChannels) {
                if (element.id == MEDIA_CHANNEL_ID) {
                    mediaChannel = element
                    break
                }
            }
            if (mediaChannel != null) return
            val channel = NotificationChannel(
                MEDIA_CHANNEL_ID,
                MEDIA_CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                this.description = MEDIA_CHANNEL_DESCRIPTION
                setSound(null, null)
            }
            getNotificationManager(context).createNotificationChannel(channel)
        }
    }

    fun notificationBuilder(
        context: Context,
        channelId: String
    ): NotificationCompat.Builder {
        return NotificationCompat.Builder(context, channelId)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .setSmallIcon(R.drawable.il_logo_bankmas) // TODO(DEV): Change Small Icon
    }
}