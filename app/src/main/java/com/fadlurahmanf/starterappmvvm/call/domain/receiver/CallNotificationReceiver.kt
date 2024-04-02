package com.fadlurahmanf.starterappmvvm.call.domain.receiver

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import com.fadlurahmanf.starterappmvvm.call.domain.CallNotificationService
import com.fadlurahmanf.starterappmvvm.call.domain.CallNotificationServiceImpl
import com.fadlurahmanf.starterappmvvm.call.presentation.CallActivity
import com.fadlurahmanf.starterappmvvm.core.notification.domain.NotificationService
import com.fadlurahmanf.starterappmvvm.core.notification.domain.NotificationServiceImpl

class CallNotificationReceiver : BroadcastReceiver() {
    private lateinit var notificationService: NotificationService
    private lateinit var callNotificationService: CallNotificationService

    companion object {
        const val ACTION_NOTIFICATION_ACCEPT_INCOMING_CALL =
            "com.fadlurahmanf.starterappmvvm.ACTION_NOTIFICATION_ACCEPT_INCOMING_CALL"
        const val ACTION_NOTIFICATION_DECLINED_INCOMING_CALL =
            "com.fadlurahmanf.starterappmvvm.ACTION_NOTIFICATION_DECLINED_INCOMING_CALL"

        fun acceptCallPendingIntent(
            context: Context,
            notificationId: Int
        ): PendingIntent {
            val intent = Intent(context, CallNotificationReceiver::class.java)
            intent.apply {
                action = ACTION_NOTIFICATION_ACCEPT_INCOMING_CALL
                putExtra("NOTIFICATION_ID", notificationId)
            }
            return PendingIntent.getBroadcast(
                context,
                notificationId,
                intent,
                getFlagPendingIntent()
            )
        }

        fun declinedCallPendingIntent(
            context: Context,
            notificationId: Int
        ): PendingIntent {
            val intent = Intent(context, CallNotificationReceiver::class.java)
            intent.apply {
                action = ACTION_NOTIFICATION_DECLINED_INCOMING_CALL
                putExtra("NOTIFICATION_ID", notificationId)
            }
            return PendingIntent.getBroadcast(
                context,
                notificationId,
                intent,
                getFlagPendingIntent()
            )
        }

        private fun getFlagPendingIntent(): Int {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
            } else {
                PendingIntent.FLAG_UPDATE_CURRENT
            }
        }
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        if (!this::notificationService.isInitialized && !this::callNotificationService.isInitialized && context != null) {
            notificationService = NotificationServiceImpl()
            callNotificationService = CallNotificationServiceImpl(notificationService)
        }
        if (context == null) return
        when (intent?.action) {
            ACTION_NOTIFICATION_ACCEPT_INCOMING_CALL -> {
                val notificationId = intent.getIntExtra("NOTIFICATION_ID", -1)
                if (notificationId != -1) {
                    onAcceptIncomingCall(context, notificationId)
                }
            }

            ACTION_NOTIFICATION_DECLINED_INCOMING_CALL -> {
                val notificationId = intent.getIntExtra("NOTIFICATION_ID", -1)
                if (notificationId != -1) {
                    onDeclinedIncomingCall(context, notificationId)
                }
            }
        }
    }

    private fun onAcceptIncomingCall(context: Context, notificationId: Int) {
        callNotificationService.cancelIncomingCallNotification(context, notificationId)
        val intent = Intent(context, CallActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
    }

    private fun onDeclinedIncomingCall(context: Context, notificationId: Int) {
        callNotificationService.cancelIncomingCallNotification(context, notificationId)
    }
}