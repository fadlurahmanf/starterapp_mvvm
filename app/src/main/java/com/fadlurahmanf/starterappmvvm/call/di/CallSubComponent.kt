package com.fadlurahmanf.starterappmvvm.call.di

import com.fadlurahmanf.starterappmvvm.call.presentation.CallActivity
import com.fadlurahmanf.starterappmvvm.call.presentation.IncomingCallActivity
import dagger.Subcomponent

@Subcomponent
interface CallSubComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): CallSubComponent
    }

    fun inject(activity: CallActivity)
    fun inject(activity: IncomingCallActivity)
}