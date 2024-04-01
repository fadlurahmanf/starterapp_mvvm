package com.fadlurahmanf.starterappmvvm.core.exception

class BaseApiException(
    val code: Int? = null,
    override val disabledBackButton: Boolean = false,
    override val enumMessage: String? = null,
) : BaseException() {}