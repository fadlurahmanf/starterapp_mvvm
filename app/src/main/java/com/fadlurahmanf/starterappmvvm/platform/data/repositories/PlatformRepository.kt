package com.fadlurahmanf.starterappmvvm.platform.data.repositories

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager

interface PlatformRepository {
    fun getDeviceId(context: Context): String

    /**
     * Determine whether <em>you</em> have been granted a particular permission.
     * @return isResult: [PackageManager.PERMISSION_GRANTED] if you have the
     * permission, or [PackageManager.PERMISSION_DENIED] if not.
     */
    fun askBiometricPermission(
        activity: Activity,
        onShouldShowRequestPermissionRationale: () -> Unit,
        onCompleteCheckPermission: (isGranted: Boolean, result: Int) -> Unit
    )

    fun isDeviceSupportedBiometric(context: Context): Boolean
}