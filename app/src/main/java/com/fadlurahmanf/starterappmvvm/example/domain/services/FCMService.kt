package com.fadlurahmanf.starterappmvvm.example.domain.services

import android.util.Log
import com.fadlurahmanf.starterappmvvm.call.domain.CallNotificationService
import com.fadlurahmanf.starterappmvvm.call.domain.CallNotificationServiceImpl
import com.fadlurahmanf.starterappmvvm.core.constant.AppConstant
import com.fadlurahmanf.starterappmvvm.core.notification.domain.NotificationService
import com.fadlurahmanf.starterappmvvm.core.notification.domain.NotificationServiceImpl
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import kotlin.random.Random

class FCMService : FirebaseMessagingService() {
    private lateinit var notificationService: NotificationService
    private lateinit var callNotificationService: CallNotificationService
    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d(AppConstant.LOGGER_TAG, "NEW FIREBASE TOKEN: $token")
    }

    override fun onMessageReceived(message: RemoteMessage) {
        Log.d(AppConstant.LOGGER_TAG, "DATA: ${message.data}")
        Log.d(AppConstant.LOGGER_TAG, "NOTIFICATION: ${message.notification}")
        if (!this::notificationService.isInitialized) {
            notificationService = NotificationServiceImpl()
        }

        if (!this::callNotificationService.isInitialized) {
            callNotificationService = CallNotificationServiceImpl()
        }
        super.onMessageReceived(message)
        val type = message.data["type"]
        when (type) {
            "GENERAL" -> {
                val notificationId = Random.nextInt(99)
                val title = message.data["title"]
                val body = message.data["body"]
                if (title != null && body != null) {
                    notificationService.showNotification(
                        applicationContext,
                        notificationId,
                        title,
                        body,
                        null
                    )
                }
            }

            "VOIP_CALL" -> {
                val notificationId = 0
                val callerName = message.data["callerName"]
                val phoneCallerName = message.data["phoneCallerName"]
                callNotificationService.showIncomingCallNotification(
                    applicationContext,
                    notificationId,
                    callerName ?: "-",
                    phoneCallerName ?: "-",
                )
            }

            else -> {

            }
        }
    }
}