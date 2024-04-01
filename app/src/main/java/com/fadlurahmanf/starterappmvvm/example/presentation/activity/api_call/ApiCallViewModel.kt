package com.fadlurahmanf.starterappmvvm.example.presentation.activity.api_call

import com.fadlurahmanf.starterappmvvm.core.network.datasources.GuestIdentityDatasourceImpl
import javax.inject.Inject

class ApiCallViewModel @Inject constructor(
    private val guestIdentityDatasourceImpl: GuestIdentityDatasourceImpl
) {

}