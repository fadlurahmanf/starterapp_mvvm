package com.fadlurahmanf.starterappmvvm.example.presentation.viewmodel

import android.content.Context
import com.fadlurahmanf.starterappmvvm.others.layout.BaseViewModel
import com.fadlurahmanf.starterappmvvm.platform.data.repositories.PlatformRepository
import javax.inject.Inject

class ExampleViewModel @Inject constructor(
    private val platformRepository: PlatformRepository
) : BaseViewModel() {
    fun getUserId(context: Context): String = platformRepository.getDeviceId(context)
}