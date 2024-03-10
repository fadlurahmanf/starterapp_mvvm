package com.fadlurahmanf.starterappmvvm.example.others.di

import com.fadlurahmanf.starterappmvvm.example.presentation.activity.ApiCallActivity
import dagger.Subcomponent

@Subcomponent
interface ExampleSubComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): ExampleSubComponent
    }

    fun inject(apiCallActivity: ApiCallActivity)
}