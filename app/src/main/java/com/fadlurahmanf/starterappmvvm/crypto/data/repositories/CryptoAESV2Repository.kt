package com.fadlurahmanf.starterappmvvm.crypto.data.repositories

import com.fadlurahmanf.starterappmvvm.crypto.data.enums.AESMethod

interface CryptoAESV2Repository {
    fun generateKey(): String
    fun encrypt(encodedKey: String, plainText: String, method: AESMethod): String?
    fun decrypt(encodedKey: String, encryptedText: String, method: AESMethod): String?
}