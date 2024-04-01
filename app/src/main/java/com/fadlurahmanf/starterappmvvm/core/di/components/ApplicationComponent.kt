package com.fadlurahmanf.starterappmvvm.core.di.components

import android.content.Context
import com.fadlurahmanf.starterappmvvm.crypto.helper.di.CryptoModule
import com.fadlurahmanf.starterappmvvm.example.others.di.ExampleModule
import com.fadlurahmanf.starterappmvvm.example.others.di.ExampleSubComponent
import com.fadlurahmanf.starterappmvvm.core.di.modules.NetworkModule
import com.fadlurahmanf.starterappmvvm.platform.data.others.di.PlatformModule
import com.fadlurahmanf.starterappmvvm.storage.others.di.StorageModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        CryptoModule::class,
        PlatformModule::class,
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
}