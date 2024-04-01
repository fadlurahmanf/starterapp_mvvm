package com.fadlurahmanf.starterappmvvm.core.state.general

import com.fadlurahmanf.starterappmvvm.core.exception.BaseException

sealed class AppState<out T : Any> {
    data object IDLE : AppState<Nothing>()
    data object LOADING : AppState<Nothing>()
    data class SUCCESS<out T : Any>(val data: T) : AppState<T>()
    data class FAILED(val exception: BaseException) : AppState<Nothing>()
}