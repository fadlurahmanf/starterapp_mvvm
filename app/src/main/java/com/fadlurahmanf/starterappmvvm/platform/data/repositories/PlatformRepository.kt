package com.fadlurahmanf.starterappmvvm.platform.data.repositories

import android.content.Context

interface PlatformRepository {
    fun getDeviceId(context: Context): String
}