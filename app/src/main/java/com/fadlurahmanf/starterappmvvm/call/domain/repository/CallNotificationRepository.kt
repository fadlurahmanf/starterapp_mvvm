package com.fadlurahmanf.starterappmvvm.call.domain.repository

import android.content.Context

interface CallNotificationRepository {
    fun showIncomingCallNotification(
        context: Context,
        id: Int,
        callerName: String,
        phoneNumberCaller: String
    )

    fun cancelIncomingCallNotification(context: Context, id: Int)
}