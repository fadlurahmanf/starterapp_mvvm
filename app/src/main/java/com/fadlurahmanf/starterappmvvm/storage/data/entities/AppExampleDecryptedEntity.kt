package com.fadlurahmanf.starterappmvvm.storage.data.entities

data class AppExampleDecryptedEntity(
    val deviceId: String,
    val encodedPublicKey: String,
    val encodedPrivateKey: String,
    var guestToken: String? = null,
)