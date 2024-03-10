package com.fadlurahmanf.starterappmvvm.others.network.datasources

import com.fadlurahmanf.starterappmvvm.others.network.dto.response.guest_token.GenerateGuestTokenResponse
import io.reactivex.rxjava3.core.Observable

interface GuestIdentityDatasource {
    fun generateGuestToken(): Observable<GenerateGuestTokenResponse>
}