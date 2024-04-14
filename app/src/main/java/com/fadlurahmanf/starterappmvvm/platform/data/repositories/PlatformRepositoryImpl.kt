package com.fadlurahmanf.starterappmvvm.platform.data.repositories

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.hardware.biometrics.BiometricManager
import android.hardware.biometrics.BiometricManager.BIOMETRIC_SUCCESS
import android.hardware.fingerprint.FingerprintManager
import android.os.Build
import android.provider.Settings.Secure
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.hardware.fingerprint.FingerprintManagerCompat
import javax.inject.Inject

class PlatformRepositoryImpl @Inject constructor() : PlatformRepository {
    override fun getDeviceId(context: Context): String {
        return Secure.getString(context.contentResolver, Secure.ANDROID_ID)
    }

    override fun askBiometricPermission(
        activity: Activity,
        onShouldShowRequestPermissionRationale: () -> Unit,
        onCompleteCheckPermission: (isGranted: Boolean, result: Int) -> Unit
    ) {
        when {
            ActivityCompat.shouldShowRequestPermissionRationale(
                activity, android.Manifest.permission.USE_BIOMETRIC
            ) -> {
                onShouldShowRequestPermissionRationale()
            }

            else -> {
                val status = ContextCompat.checkSelfPermission(
                    activity,
                    android.Manifest.permission.USE_BIOMETRIC
                )
                onCompleteCheckPermission(status == PackageManager.PERMISSION_GRANTED, status)
            }
        }
    }

    override fun isDeviceSupportedBiometric(context: Context): Boolean {
        return when {
            Build.VERSION.SDK_INT > Build.VERSION_CODES.Q -> {
                val biometricManager =
                    context.getSystemService(Context.BIOMETRIC_SERVICE) as BiometricManager
                biometricManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_STRONG) == BIOMETRIC_SUCCESS
            }

            Build.VERSION.SDK_INT == Build.VERSION_CODES.Q -> {
                val biometricManager =
                    context.getSystemService(Context.BIOMETRIC_SERVICE) as BiometricManager
                biometricManager.canAuthenticate() == BIOMETRIC_SUCCESS
            }

            Build.VERSION.SDK_INT > Build.VERSION_CODES.M -> {
                val manager =
                    context.getSystemService(Context.FINGERPRINT_SERVICE) as FingerprintManager
                manager.hasEnrolledFingerprints()
            }

            Build.VERSION.SDK_INT <= Build.VERSION_CODES.M -> {
                val manager = FingerprintManagerCompat.from(context)
                manager.hasEnrolledFingerprints()
            }

            else -> false
        }
    }
}