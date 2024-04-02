package com.fadlurahmanf.starterappmvvm.core.notification.domain

import android.app.Activity
import android.app.PendingIntent
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.fadlurahmanf.starterappmvvm.core.notification.others.BaseNotificationService
import javax.inject.Inject

class NotificationServiceImpl @Inject constructor() : BaseNotificationService(), NotificationService {

    override fun areNotificationEnabledAndGranted(context: Context): Boolean {
        return when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> {
                ContextCompat.checkSelfPermission(
                    context,
                    android.Manifest.permission_group.NOTIFICATIONS
                ) == PackageManager.PERMISSION_GRANTED
            }

            else -> {
                NotificationManagerCompat.from(context).areNotificationsEnabled()
            }
        }
    }

    override fun askNotificationPermissionPermission(
        activity: Activity,
        onGranted: () -> Unit,
        onShouldShowRequestPermissionRationale: () -> Unit,
        onLaunchPermission: () -> Unit,
    ) {
        when {
            ContextCompat.checkSelfPermission(
                activity,
                android.Manifest.permission_group.NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED -> {
                onGranted()
            }

            ActivityCompat.shouldShowRequestPermissionRationale(
                activity, android.Manifest.permission_group.NOTIFICATIONS
            ) -> {
                onShouldShowRequestPermissionRationale()
            }

            else -> {
                onLaunchPermission()
            }
        }
    }

    override fun showNotification(
        context: Context,
        id: Int,
        title: String,
        message: String,
        pendingIntent: PendingIntent?
    ) {
        createGeneralNotificationChannel(context)
        val notification = notificationBuilder(context, GENERAL_CHANNEL_ID).apply {
            setContentTitle(title)
            setContentText(message)
            if (pendingIntent != null) {
                setContentIntent(pendingIntent)
            }
        }
        getNotificationManager(context).notify(id, notification.build())
    }

    override fun cancelNotification(context: Context, id: Int) {
        getNotificationManager(context).cancel(id)
    }
}