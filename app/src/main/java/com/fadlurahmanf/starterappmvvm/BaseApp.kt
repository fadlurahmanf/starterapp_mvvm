package com.fadlurahmanf.starterappmvvm

import android.app.Application
import com.fadlurahmanf.starterappmvvm.others.di.components.ApplicationComponent
import com.fadlurahmanf.starterappmvvm.others.di.components.DaggerApplicationComponent
import com.google.firebase.crashlytics.FirebaseCrashlytics

class BaseApp : Application() {
    lateinit var applicationComponent: ApplicationComponent
    lateinit var firebaseCrashlytics: FirebaseCrashlytics
    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.factory().create(applicationContext)
        firebaseCrashlytics = FirebaseCrashlytics.getInstance()
    }
}