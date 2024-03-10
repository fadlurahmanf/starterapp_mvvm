package com.fadlurahmanf.starterappmvvm.others.exception

open class BaseException(
    open val enumMessage: String? = null,
    open val disabledBackButton: Boolean = false,
    override var message: String? = null,
) : Throwable(message = message) {

    companion object {
        const val PREFIX_GENERAL_EXCEPTION = "BASE_EXCEPTION"
    }

    fun toProperMessage(): String? {
        when (enumMessage) {
            "${PREFIX_GENERAL_EXCEPTION}_DATA_MISSING" -> {
                message = ""
                return message
            }
        }

        return ""
    }
}