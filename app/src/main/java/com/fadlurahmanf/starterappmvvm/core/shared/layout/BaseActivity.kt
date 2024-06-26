package com.fadlurahmanf.starterappmvvm.core.shared.layout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewbinding.ViewBinding
import com.fadlurahmanf.starterappmvvm.BaseApp
import com.google.firebase.crashlytics.FirebaseCrashlytics

typealias InflateActivity<T> = (LayoutInflater) -> T

abstract class BaseActivity<VB : ViewBinding>(
    private val inflater: InflateActivity<VB>
) : AppCompatActivity() {

    private lateinit var _binding: VB
    val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        onBaseBindingActivity()
        onBaseCreateSubComponent()
        setupFirebaseCrashlytics()
        onBaseCreate(savedInstanceState)
    }

    abstract fun onBaseCreateSubComponent()

    lateinit var firebaseCrashlytics: FirebaseCrashlytics

    open fun setupFirebaseCrashlytics() {
        firebaseCrashlytics = (applicationContext as BaseApp).firebaseCrashlytics
    }

    open fun onBaseBindingActivity() {
        _binding = inflater.invoke(layoutInflater)
        setContentView(_binding.root)
    }

    abstract fun onBaseCreate(savedInstanceState: Bundle?)

    open fun setOnApplyWindowInsetsListener(view: View) {
        ViewCompat.setOnApplyWindowInsetsListener(view) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(view.paddingLeft, systemBars.top, view.paddingRight, systemBars.bottom)
            insets
        }
    }
}