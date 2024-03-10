package com.fadlurahmanf.starterappmvvm.others.network.api

import com.fadlurahmanf.starterappmvvm.others.network.dto.request.guest_token.GenerateGuestTokenRequest
import com.fadlurahmanf.starterappmvvm.others.network.dto.response.guest_token.GenerateGuestTokenResponse
import com.fadlurahmanf.starterappmvvm.others.network.dto.response.others.MerchantBaseResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.Body
import retrofit2.http.POST

interface IdentityApi {
    @POST("auth/guest-token")
    fun generateGuestToken(
        @Body generateGuestTokenRequest: GenerateGuestTokenRequest
    ): Observable<MerchantBaseResponse<GenerateGuestTokenResponse>>
}