package com.fadlurahmanf.starterappmvvm.example.presentation.activity.biometric

import android.app.Activity
import android.content.Context
import com.fadlurahmanf.starterappmvvm.core.shared.layout.BaseViewModel
import com.fadlurahmanf.starterappmvvm.platform.data.repositories.PlatformRepository
import javax.inject.Inject

class BiometricViewModel @Inject constructor(
    private val platformRepository: PlatformRepository
) : BaseViewModel() {

    fun isSupportedBiometric(context: Context) =
        platformRepository.isDeviceSupportedBiometric(context)

    fun askBiometricPermission(
        activity: Activity,
        onShouldShowRequestPermissionRationale: () -> Unit,
        onCompleteCheckPermission: (Boolean, Int) -> Unit
    ) = platformRepository.askBiometricPermission(
        activity,
        onShouldShowRequestPermissionRationale,
        onCompleteCheckPermission
    )
}