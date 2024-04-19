package com.fadlurahmanf.starterappmvvm.call.domain.repository

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import com.fadlurahmanf.starterappmvvm.call.domain.player.CallNotificationPlayerService
import com.fadlurahmanf.starterappmvvm.call.presentation.IncomingCallActivity
import com.github.fadlurahmanfdev.core_notification.others.BaseNotificationService
import javax.inject.Inject

class CallNotificationRepositoryImpl @Inject constructor() : BaseNotificationService(),
    CallNotificationRepository {
    override fun showIncomingCallNotification(
        context: Context,
        id: Int,
        callerName: String,
        phoneNumberCaller: String
    ) {
//        createVoipNotificationChannel(context)
//        val builder = NotificationCompat.Builder(context, VOIP_CHANNEL_ID)
//            .setSmallIcon(R.drawable.il_logo_bankmas)
//            .setPriority(NotificationCompat.PRIORITY_MAX)
//            .setCategory(NotificationCompat.CATEGORY_CALL)
//            .setAutoCancel(true)
//            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
//            .setOngoing(true)
//            .setWhen(0)
//            .setTimeoutAfter(60000L)
//            .setOnlyAlertOnce(true)
//            .setFullScreenIntent(getFullScreenIncomingCallActivityIntent(context, id), true)
////            .setDeleteIntent(getDeleteCallPendingIntent(notificationId))
//
//        val notificationView =
//            RemoteViews(context.packageName, R.layout.layout_incoming_call_notification)
//        val smallNotificationView =
//            RemoteViews(context.packageName, R.layout.layout_incoming_call_notification_small)
//
//        notificationView.setTextViewText(
//            R.id.tv_caller_name, callerName
//        )
//        notificationView.setTextViewText(
//            R.id.tv_phone_caller, phoneNumberCaller
//        )
//        notificationView.setOnClickPendingIntent(
//            R.id.iv_decline,
//            CallNotificationReceiver.declinedCallPendingIntent(context, id)
//        )
//        notificationView.setOnClickPendingIntent(
//            R.id.iv_accept,
//            CallNotificationReceiver.acceptCallPendingIntent(context, id)
//        )
//
//        smallNotificationView.setTextViewText(
//            R.id.tv_caller_name, callerName
//        )
//        smallNotificationView.setOnClickPendingIntent(
//            R.id.iv_decline,
//            CallNotificationReceiver.declinedCallPendingIntent(context, id)
//        )
//        smallNotificationView.setOnClickPendingIntent(
//            R.id.iv_accept,
//            CallNotificationReceiver.acceptCallPendingIntent(context, id)
//        )
//
//        builder.setCustomContentView(smallNotificationView)
//            .setCustomHeadsUpContentView(smallNotificationView)
//            .setCustomBigContentView(notificationView)
//
//        val notification = builder.build()
//        notification.flags = Notification.FLAG_ONGOING_EVENT
//
//        getNotificationManager(context).notify(id, notification)
//        CallNotificationPlayerService.startIncomingCallNotificationPlayer(context)
    }

    override fun cancelIncomingCallNotification(context: Context, id: Int) {
        getNotificationManager(context).cancel(id)
        CallNotificationPlayerService.stopIncomingCallNotificationPlayer(context)
    }

    private fun getFullScreenIncomingCallActivityIntent(context: Context, id: Int): PendingIntent {
        val intent = Intent(
            context,
            IncomingCallActivity::class.java
        )
        intent.apply {
            putExtra("NOTIFICATION_ID", id)
        }
        val flag = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        } else {
            PendingIntent.FLAG_UPDATE_CURRENT
        }
        return PendingIntent.getActivity(context, id, intent, flag)
    }
}