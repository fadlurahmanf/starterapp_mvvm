package com.fadlurahmanf.starterappmvvm.example.data.model

data class FirstLaunchModel(
    val deviceId: String,
    val privateKey: String,
    val publicKey: String,
    val isFirstInstall: Boolean,
)
