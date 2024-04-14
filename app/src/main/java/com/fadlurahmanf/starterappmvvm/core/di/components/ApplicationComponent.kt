package com.fadlurahmanf.starterappmvvm.core.di.components

import android.content.Context
import com.fadlurahmanf.starterappmvvm.call.di.CallModule
import com.fadlurahmanf.starterappmvvm.call.di.CallSubComponent
import com.fadlurahmanf.starterappmvvm.crypto.others.di.CryptoModule
import com.fadlurahmanf.starterappmvvm.example.others.di.ExampleModule
import com.fadlurahmanf.starterappmvvm.example.others.di.ExampleSubComponent
import com.fadlurahmanf.starterappmvvm.core.di.modules.NetworkModule
import com.fadlurahmanf.starterappmvvm.core.notification.others.NotificationModule
import com.fadlurahmanf.starterappmvvm.platform.di.PlatformModule
import com.fadlurahmanf.starterappmvvm.storage.others.di.StorageModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        CryptoModule::class,
        PlatformModule::class,
        NotificationModule::class,
        CallModule::class,
        StorageModule::class,
        NetworkModule::class,
        ExampleModule::class,
    ]
)
interface ApplicationComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): ApplicationComponent
    }

    fun exampleSubComponentFactory(): ExampleSubComponent.Factory
    fun callSubComponentFactory(): CallSubComponent.Factory
}