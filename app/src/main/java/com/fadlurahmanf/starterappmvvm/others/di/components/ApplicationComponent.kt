package com.fadlurahmanf.starterappmvvm.others.di.components

import android.content.Context
import com.fadlurahmanf.starterappmvvm.example.others.di.ExampleModule
import com.fadlurahmanf.starterappmvvm.example.others.di.ExampleSubComponent
import com.fadlurahmanf.starterappmvvm.others.di.modules.NetworkModule
import com.fadlurahmanf.starterappmvvm.platform.data.others.di.PlatformModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        NetworkModule::class,
        PlatformModule::class,
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