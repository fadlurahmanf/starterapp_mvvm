package com.fadlurahmanf.starterappmvvm.others.state.general

import com.fadlurahmanf.starterappmvvm.others.exception.BaseException

sealed class AppState<out T : Any> {
    data object IDLE : AppState<Nothing>()
    data object LOADING : AppState<Nothing>()
    data class SUCCESS<out T : Any>(val data: T) : AppState<T>()
    data class FAILED(val exception: BaseException) : AppState<Nothing>()
}