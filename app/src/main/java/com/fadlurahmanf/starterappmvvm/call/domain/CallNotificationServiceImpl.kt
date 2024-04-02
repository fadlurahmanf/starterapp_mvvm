package com.fadlurahmanf.starterappmvvm.call.domain

import android.app.Notification
import android.content.Context
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.fadlurahmanf.starterappmvvm.R
import com.fadlurahmanf.starterappmvvm.call.domain.receiver.CallNotificationReceiver
import com.fadlurahmanf.starterappmvvm.core.notification.domain.NotificationService
import com.fadlurahmanf.starterappmvvm.call.domain.player.CallNotificationPlayerService
import com.fadlurahmanf.starterappmvvm.core.notification.others.BaseNotificationService
import javax.inject.Inject

class CallNotificationServiceImpl @Inject constructor() : BaseNotificationService(),
    CallNotificationService {
    override fun showIncomingCallNotification(
        context: Context,
        id: Int,
        callerName: String,
        phoneNumberCaller: String
    ) {
        createVoipNotificationChannel(context)
        val builder = NotificationCompat.Builder(context, VOIP_CHANNEL_ID)
            .setSmallIcon(R.drawable.il_logo_bankmas)
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .setCategory(NotificationCompat.CATEGORY_CALL)
            .setAutoCancel(true)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .setOngoing(true)
            .setWhen(0)
            .setTimeoutAfter(60000L)
            .setOnlyAlertOnce(true)
//            .setFullScreenIntent(getFullScreenIntent(notificationId), true)
//            .setDeleteIntent(getDeleteCallPendingIntent(notificationId))

        val notificationView =
            RemoteViews(context.packageName, R.layout.layout_incoming_call_notification)
        val smallNotificationView =
            RemoteViews(context.packageName, R.layout.layout_incoming_call_notification_small)

        notificationView.setTextViewText(
            R.id.tv_caller_name, callerName
        )
        notificationView.setTextViewText(
            R.id.tv_phone_caller, phoneNumberCaller
        )
        notificationView.setOnClickPendingIntent(
            R.id.iv_decline,
            CallNotificationReceiver.declinedCallPendingIntent(context, id)
        )
        notificationView.setOnClickPendingIntent(
            R.id.iv_accept,
            CallNotificationReceiver.acceptCallPendingIntent(context, id)
        )

        smallNotificationView.setTextViewText(
            R.id.tv_caller_name, callerName
        )
        smallNotificationView.setOnClickPendingIntent(
            R.id.iv_decline,
            CallNotificationReceiver.declinedCallPendingIntent(context, id)
        )
        smallNotificationView.setOnClickPendingIntent(
            R.id.iv_accept,
            CallNotificationReceiver.acceptCallPendingIntent(context, id)
        )

        builder.setCustomContentView(smallNotificationView)
            .setCustomHeadsUpContentView(smallNotificationView)
            .setCustomBigContentView(notificationView)

        val notification = builder.build()
        notification.flags = Notification.FLAG_ONGOING_EVENT

        getNotificationManager(context).notify(id, notification)
        CallNotificationPlayerService.startIncomingCallNotificationPlayer(context)
    }

    override fun cancelIncomingCallNotification(context: Context, id: Int) {
        getNotificationManager(context).cancel(id)
        CallNotificationPlayerService.stopIncomingCallNotificationPlayer(context)
    }
}