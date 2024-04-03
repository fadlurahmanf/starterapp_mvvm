package com.fadlurahmanf.starterappmvvm.call.domain

import android.app.PendingIntent
import android.content.Context

interface CallNotificationService {
    fun showIncomingCallNotification(
        context: Context,
        id: Int,
        callerName: String,
        phoneNumberCaller: String
    )

    fun cancelIncomingCallNotification(context: Context, id: Int)
}