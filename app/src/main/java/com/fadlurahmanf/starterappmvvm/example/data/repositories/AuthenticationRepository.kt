package com.fadlurahmanf.starterappmvvm.example.data.repositories

import io.reactivex.rxjava3.core.Observable

interface AuthenticationRepository {
    fun generateGuestToken(): Observable<Boolean>
}