package com.fadlurahmanf.starterappmvvm.others.di.components

import android.content.Context
import com.fadlurahmanf.starterappmvvm.feature_api_call.others.di.FeatureApiCallModule
import com.fadlurahmanf.starterappmvvm.feature_api_call.others.di.FeatureApiCallSubComponent
import com.fadlurahmanf.starterappmvvm.others.di.modules.NetworkModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        NetworkModule::class,
        FeatureApiCallModule::class,
    ]
)
interface ApplicationComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): ApplicationComponent
    }

    fun featureApiCallComponent(): FeatureApiCallSubComponent.Factory
}