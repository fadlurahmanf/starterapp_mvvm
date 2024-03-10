package com.fadlurahmanf.starterappmvvm.example.presentation

import android.os.Bundle
import androidx.viewbinding.ViewBinding
import com.fadlurahmanf.starterappmvvm.BaseApp
import com.fadlurahmanf.starterappmvvm.example.others.di.ExampleSubComponent
import com.fadlurahmanf.starterappmvvm.others.layout.BaseActivity
import com.fadlurahmanf.starterappmvvm.others.layout.InflateActivity

abstract class BaseExampleActivity<VB : ViewBinding>(
    inflater: InflateActivity<VB>
) : BaseActivity<VB>(inflater) {

    lateinit var component: ExampleSubComponent

    override fun onBaseCreateSubComponent() {
        component =
            (applicationContext as BaseApp).applicationComponent.exampleSubComponentFactory()
                .create()
        onBaseExampleInjectActivity()
    }

    abstract fun onBaseExampleInjectActivity()

    override fun onBaseCreate(savedInstanceState: Bundle?) {
        onBaseExampleCreate(savedInstanceState)
    }

    abstract fun onBaseExampleCreate(savedInstanceState: Bundle?)
}