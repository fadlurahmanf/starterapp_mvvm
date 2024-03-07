package com.fadlurahmanf.starterappmvvm.feature_api_call.others.di

import dagger.Subcomponent
@Subcomponent
interface FeatureApiCallSubComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): FeatureApiCallSubComponent
    }
}