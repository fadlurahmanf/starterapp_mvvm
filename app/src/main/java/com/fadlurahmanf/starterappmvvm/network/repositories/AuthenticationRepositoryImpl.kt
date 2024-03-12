package com.fadlurahmanf.starterappmvvm.network.repositories

import com.fadlurahmanf.starterappmvvm.network.datasources.GuestIdentityDatasourceImpl
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(
    private val guestIdentityDatasourceImpl: GuestIdentityDatasourceImpl
) : AuthenticationRepository {
    override fun generateGuestToken(): Observable<Boolean> {
        return guestIdentityDatasourceImpl.generateGuestToken().map {
            true
        }
    }
}