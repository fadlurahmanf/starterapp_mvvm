package com.fadlurahmanf.starterappmvvm.example.others.di

import com.fadlurahmanf.starterappmvvm.example.presentation.activity.biometric.BiometricActivity
import com.fadlurahmanf.starterappmvvm.example.presentation.activity.api_call.ApiCallActivity
import com.fadlurahmanf.starterappmvvm.example.presentation.activity.ListExampleActivity
import com.fadlurahmanf.starterappmvvm.example.presentation.activity.crashlytics.FirebaseCrashlyticsActivity
import com.fadlurahmanf.starterappmvvm.example.presentation.activity.crypto.ExampleCryptoActivity
import com.fadlurahmanf.starterappmvvm.example.presentation.activity.notification.ExampleNotificationActivity
import com.fadlurahmanf.starterappmvvm.example.presentation.activity.storage.StorageActivity
import dagger.Subcomponent

@Subcomponent
interface ExampleSubComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): ExampleSubComponent
    }

    fun inject(activity: ListExampleActivity)
    fun inject(activity: ApiCallActivity)
    fun inject(activity: FirebaseCrashlyticsActivity)
    fun inject(activity: ExampleCryptoActivity)
    fun inject(activity: StorageActivity)
    fun inject(activity: ExampleNotificationActivity)
    fun inject(activity: BiometricActivity)
}