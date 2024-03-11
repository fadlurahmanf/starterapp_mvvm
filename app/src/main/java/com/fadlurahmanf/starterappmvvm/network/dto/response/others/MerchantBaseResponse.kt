package com.fadlurahmanf.starterappmvvm.network.dto.response.others

data class MerchantBaseResponse<T>(
    val status: Boolean?,
    val code: String?,
    val message: String?,
    val data: T,
)