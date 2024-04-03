package com.fadlurahmanf.starterappmvvm.call.presentation

import android.os.Bundle
import androidx.viewbinding.ViewBinding
import com.fadlurahmanf.starterappmvvm.BaseApp
import com.fadlurahmanf.starterappmvvm.call.di.CallSubComponent
import com.fadlurahmanf.starterappmvvm.example.others.di.ExampleSubComponent
import com.fadlurahmanf.starterappmvvm.core.layout.BaseActivity
import com.fadlurahmanf.starterappmvvm.core.layout.InflateActivity

abstract class BaseCallActivity<VB : ViewBinding>(
    inflater: InflateActivity<VB>
) : BaseActivity<VB>(inflater) {

    lateinit var component: CallSubComponent

    override fun onBaseCreateSubComponent() {
        component =
            (applicationContext as BaseApp).applicationComponent.callSubComponentFactory()
                .create()
        onBaseCallInjectActivity()
    }

    abstract fun onBaseCallInjectActivity()

    override fun onBaseCreate(savedInstanceState: Bundle?) {
        onBaseExampleCreate(savedInstanceState)
    }

    abstract fun onBaseExampleCreate(savedInstanceState: Bundle?)
}