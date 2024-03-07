package com.fadlurahmanf.starterappmvvm.others.network.api

import retrofit2.http.GET
import retrofit2.http.POST

interface IdentityApi {
    @GET("tes/identity")
    fun generateGuestToken()
}