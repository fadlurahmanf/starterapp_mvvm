package com.fadlurahmanf.starterappmvvm.example.presentation.utilities.notification

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import com.fadlurahmanf.starterappmvvm.example.presentation.activity.notification.ExampleNotificationActivity

class ExampleNotificationUtility {
    companion object {
         fun getPendingIntentNotificationDetail(
            context: Context,
            notificationId: Int
        ): PendingIntent {
            val intent = Intent(context, ExampleNotificationActivity::class.java)
            intent.apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
                putExtra("IS_GO_TO_NOTIFICATION_DETAIL", true)
                putExtra("NOTIFICATION_ID", notificationId)
            }
            return PendingIntent.getActivity(
                context,
                notificationId,
                intent,
                getFlagPendingIntent()
            )
        }

        private fun getFlagPendingIntent(): Int {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            } else {
                PendingIntent.FLAG_UPDATE_CURRENT
            }
        }
    }
}