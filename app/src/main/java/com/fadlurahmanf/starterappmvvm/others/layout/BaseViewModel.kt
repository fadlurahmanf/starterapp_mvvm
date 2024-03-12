package com.fadlurahmanf.starterappmvvm.others.layout

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable

open class BaseViewModel() : ViewModel() {
    val baseDisposable: CompositeDisposable = CompositeDisposable()
    fun compositeDisposable() = CompositeDisposable()
    fun dispose() {
        baseDisposable.clear()
    }
}