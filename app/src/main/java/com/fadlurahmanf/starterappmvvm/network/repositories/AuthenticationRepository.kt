package com.fadlurahmanf.starterappmvvm.network.repositories

import io.reactivex.rxjava3.core.Observable

interface AuthenticationRepository {
    fun generateGuestToken(): Observable<Boolean>
}