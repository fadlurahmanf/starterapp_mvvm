package com.fadlurahmanf.starterappmvvm.example.data.repositories

import com.fadlurahmanf.starterappmvvm.core.network.datasources.GuestIdentityDatasourceImpl
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