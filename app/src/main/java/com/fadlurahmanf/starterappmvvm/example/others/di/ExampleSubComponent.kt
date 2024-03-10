package com.fadlurahmanf.starterappmvvm.example.others.di

import dagger.Subcomponent
@Subcomponent
interface ExampleSubComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): ExampleSubComponent
    }
}