package com.fadlurahmanf.starterappmvvm.network.api

import com.fadlurahmanf.starterappmvvm.network.dto.request.guest_token.GenerateGuestTokenRequest
import com.fadlurahmanf.starterappmvvm.network.dto.response.guest_token.GenerateGuestTokenResponse
import com.fadlurahmanf.starterappmvvm.network.dto.response.others.MerchantBaseResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.Body
import retrofit2.http.POST

interface IdentityApi {
    @POST("guest/session/create")
    fun generateGuestToken(
        @Body generateGuestTokenRequest: GenerateGuestTokenRequest
    ): Observable<MerchantBaseResponse<GenerateGuestTokenResponse>>
}