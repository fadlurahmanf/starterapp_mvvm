package com.fadlurahmanf.starterappmvvm.platform.data.repositories

import android.content.Context
import android.provider.Settings.Secure

class PlatformRepositoryImpl : PlatformRepository {
    override fun getDeviceId(context: Context): String {
        return Secure.getString(context.contentResolver, Secure.ANDROID_ID)
    }
}