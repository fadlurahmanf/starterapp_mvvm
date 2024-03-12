package com.fadlurahmanf.starterappmvvm.example.others.di

import com.fadlurahmanf.starterappmvvm.example.presentation.activity.api_call.ApiCallActivity
import com.fadlurahmanf.starterappmvvm.example.presentation.activity.ListExampleActivity
import com.fadlurahmanf.starterappmvvm.example.presentation.activity.crashlytics.FirebaseCrashlyticsActivity
import com.fadlurahmanf.starterappmvvm.example.presentation.activity.crypto.AesCryptoActivity
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
    fun inject(activity: AesCryptoActivity)
}