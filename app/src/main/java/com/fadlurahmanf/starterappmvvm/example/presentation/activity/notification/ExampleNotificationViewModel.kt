package com.fadlurahmanf.starterappmvvm.example.presentation.activity.notification

import android.app.PendingIntent
import android.content.Context
import android.util.Log
import com.fadlurahmanf.starterappmvvm.call.domain.repository.CallNotificationRepository
import com.fadlurahmanf.starterappmvvm.core.shared.layout.BaseViewModel
import com.fadlurahmanf.starterappmvvm.example.data.repositories.ExampleNotificationRepository
import com.fadlurahmanf.starterappmvvm.example.domain.usecases.ExampleNotificationUseCase
import javax.inject.Inject

class ExampleNotificationViewModel @Inject constructor(
    private val callNotificationRepository: CallNotificationRepository,
    private val exampleNotificationRepository: ExampleNotificationRepository,
    private val exampleNotificationUseCase: ExampleNotificationUseCase,
) : BaseViewModel() {

    fun askNotificationPermission(context: Context) =
        exampleNotificationUseCase.askNotificationPermission(
            context,
            onCompleteCheckPermission = { isGranted ->
                Log.d(
                    ExampleNotificationViewModel::class.java.simpleName,
                    "IS NOTIFICATION PERMISSION GRANTED: $isGranted"
                )
            }
        )

    fun showSimpleNotification(
        context: Context
    ) = exampleNotificationUseCase.showNotification(
        context,
        1,
        title = "Simple Notification",
        message = "This is a simple notification",
        pendingIntent = null,
    )

    fun showImageNotification(
        context: Context,
    ) = exampleNotificationUseCase.showImageNotification(
        context,
        2,
        title = "Image Notification",
        message = "This is an image notification",
        networkImage = "https://raw.githubusercontent.com/TutorialsBuzz/cdn/main/android.jpg",
    )

    fun showCustomSoundNotification(
        context: Context,
    ) = exampleNotificationUseCase.showCustomSoundNotification(
        context,
        id = 3,
        title = "Custom Sound Notification",
        message = "This is a notification with custom sound",
        pendingIntent = null,
    )

    fun showGroupedNotification(
        context: Context,
        id: Int,
    ) = exampleNotificationRepository.showGroupedNotification(context, id = id)

    fun showIncomingCallNotification(
        context: Context,
        id: Int,
        callerName: String,
        phoneNumberCaller: String
    ) = callNotificationRepository.showIncomingCallNotification(
        context,
        id,
        callerName,
        phoneNumberCaller
    )

    fun cancelIncomingCallNotification(context: Context, id: Int) =
        callNotificationRepository.cancelIncomingCallNotification(context, id)
}