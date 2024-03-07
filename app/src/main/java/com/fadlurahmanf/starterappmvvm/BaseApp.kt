package com.fadlurahmanf.starterappmvvm

import android.app.Application
import com.fadlurahmanf.starterappmvvm.others.di.components.ApplicationComponent
import com.fadlurahmanf.starterappmvvm.others.di.components.DaggerApplicationComponent

class BaseApp : Application() {
    lateinit var applicationComponent: ApplicationComponent
    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.factory().create(applicationContext)
    }
}