package com.fadlurahmanf.starterappmvvm.example.domain.services

import android.util.Log
import com.fadlurahmanf.starterappmvvm.call.domain.repository.CallNotificationRepository
import com.fadlurahmanf.starterappmvvm.call.domain.repository.CallNotificationRepositoryImpl
import com.fadlurahmanf.starterappmvvm.core.shared.constant.AppConstant
import com.fadlurahmanf.starterappmvvm.example.data.repositories.ExampleNotificationRepository
import com.fadlurahmanf.starterappmvvm.example.data.repositories.ExampleNotificationRepositoryImpl
import com.github.fadlurahmanfdev.core_notification.data.repositories.NotificationRepository
import com.github.fadlurahmanfdev.core_notification.data.repositories.NotificationRepositoryImpl
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import kotlin.random.Random

class FCMService : FirebaseMessagingService() {
    private lateinit var callNotificationRepository: CallNotificationRepository
    private lateinit var exampleNotificationRepository: ExampleNotificationRepository
    private lateinit var notificationRepository: NotificationRepository
    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d(AppConstant.LOGGER_TAG, "NEW FIREBASE TOKEN: $token")
    }

    override fun onMessageReceived(message: RemoteMessage) {
        Log.d(AppConstant.LOGGER_TAG, "DATA: ${message.data}")
        Log.d(AppConstant.LOGGER_TAG, "NOTIFICATION: ${message.notification}")
        if (!this::notificationRepository.isInitialized) {
            notificationRepository = NotificationRepositoryImpl()
        }
        if (!this::exampleNotificationRepository.isInitialized) {
            exampleNotificationRepository =
                ExampleNotificationRepositoryImpl(notificationRepository)
        }

        if (!this::callNotificationRepository.isInitialized) {
            callNotificationRepository = CallNotificationRepositoryImpl()
        }
        super.onMessageReceived(message)
        val type = message.data["type"]
        when (type) {
            "GENERAL" -> {
                val notificationId = Random.nextInt(99)
                val title = message.data["title"]
                val body = message.data["body"]
                if (title != null && body != null) {
                    exampleNotificationRepository.showNotification(
                        applicationContext,
                        notificationId,
                        title = title,
                        message = body,
                        pendingIntent = null,
                    )
                }
            }

            "VOIP_CALL" -> {
                val notificationId = 0
                val callerName = message.data["callerName"]
                val phoneCallerName = message.data["phoneCallerName"]
                callNotificationRepository.showIncomingCallNotification(
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