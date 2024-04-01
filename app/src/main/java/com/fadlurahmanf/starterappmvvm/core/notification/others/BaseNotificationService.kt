package com.fadlurahmanf.starterappmvvm.core.notification.others

import android.content.Context
import androidx.core.app.NotificationCompat
import com.fadlurahmanf.starterappmvvm.R

abstract class BaseNotificationService {
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