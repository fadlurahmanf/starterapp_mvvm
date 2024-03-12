package com.fadlurahmanf.starterappmvvm.crypto.helper.di

import dagger.Subcomponent

@Subcomponent(modules = [CryptoModule::class])
interface CryptoSubComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): CryptoSubComponent
    }
}