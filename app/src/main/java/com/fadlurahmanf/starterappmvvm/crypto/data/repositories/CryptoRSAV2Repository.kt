package com.fadlurahmanf.starterappmvvm.crypto.data.repositories

import com.fadlurahmanf.starterappmvvm.crypto.data.enums.RSAMethod
import com.fadlurahmanf.starterappmvvm.crypto.data.enums.RSASignatureMethod
import com.fadlurahmanf.starterappmvvm.crypto.data.model.CryptoKey

interface CryptoRSAV2Repository {
    fun generateKey(): CryptoKey
    fun generateSignature(
        encodedPrivateKey: String,
        plainText: String,
        method: RSASignatureMethod
    ): String?

    fun verifySignature(
        encodedPublicKey: String,
        encodedSignature: String,
        plainText: String,
        method: RSASignatureMethod,
    ): Boolean

    fun encrypt(encodedPublicKey: String, plainText: String, method: RSAMethod): String?
    fun decrypt(encodedPrivateKey: String, encryptedText: String, method: RSAMethod): String?
}