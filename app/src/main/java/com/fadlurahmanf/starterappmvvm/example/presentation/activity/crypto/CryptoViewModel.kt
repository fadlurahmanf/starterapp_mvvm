package com.fadlurahmanf.starterappmvvm.example.presentation.activity.crypto

import com.fadlurahmanf.starterappmvvm.core.shared.layout.BaseViewModel
import com.fadlurahmanf.starterappmvvm.crypto.data.enums.AESMethod
import com.fadlurahmanf.starterappmvvm.crypto.data.enums.RSAMethod
import com.fadlurahmanf.starterappmvvm.crypto.data.repositories.CryptoAESV2Repository
import com.fadlurahmanf.starterappmvvm.crypto.data.repositories.CryptoED25119Repository
import com.fadlurahmanf.starterappmvvm.crypto.data.repositories.CryptoRSAV2Repository
import javax.inject.Inject

class CryptoViewModel @Inject constructor(
    private val cryptoAESV2Repository: CryptoAESV2Repository,
    private val cryptoED25119Repository: CryptoED25119Repository,
    private val cryptoRSAV2Repository: CryptoRSAV2Repository,
) : BaseViewModel() {

    fun generateAESKey() = cryptoAESV2Repository.generateKey()
    fun encrypt(key: String, plainText: String, method: AESMethod) =
        cryptoAESV2Repository.encrypt(key, plainText, method)

    fun decrypt(key: String, encryptedText: String, method: AESMethod) =
        cryptoAESV2Repository.decrypt(key, encryptedText, method)

    fun generateEd25119Key() = cryptoED25119Repository.generateKey()
    fun generateEd25119Signature(encodedPrivateKey: String, plainText: String) =
        cryptoED25119Repository.generateSignature(plainText, encodedPrivateKey)

    fun verifyEd25119Signature(encodedPublicKey: String, signature: String, plainText: String) =
        cryptoED25119Repository.verifySignature(plainText, signature, encodedPublicKey)

    fun generateRSAKey() = cryptoRSAV2Repository.generateKey()
    fun generateRSASignature(encodedPrivateKey: String, plainText: String, method: RSAMethod) =
        cryptoRSAV2Repository.generateSignature(encodedPrivateKey, plainText, method)

    fun verifyRSASignature(
        encodedPublicKey: String,
        signature: String,
        plainText: String,
        method: RSAMethod
    ) =
        cryptoRSAV2Repository.verifySignature(encodedPublicKey, signature, plainText, method)
}