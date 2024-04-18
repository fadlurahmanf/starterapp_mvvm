package com.fadlurahmanf.starterappmvvm.example.presentation.activity.crypto

import com.fadlurahmanf.starterappmvvm.core.shared.layout.BaseViewModel
import com.github.fadlurahmanfdev.core_crypto.data.enums.AESMethod
import com.github.fadlurahmanfdev.core_crypto.data.enums.RSAMethod
import com.github.fadlurahmanfdev.core_crypto.data.enums.RSASignatureMethod
import com.github.fadlurahmanfdev.core_crypto.data.repositories.CryptoAESRepository
import com.github.fadlurahmanfdev.core_crypto.data.repositories.CryptoED25519Repository
import com.github.fadlurahmanfdev.core_crypto.data.repositories.CryptoRSARepository
import javax.inject.Inject

class CryptoViewModel @Inject constructor(
    private val cryptoAESRepository: CryptoAESRepository,
    private val cryptoED25519Repository: CryptoED25519Repository,
    private val cryptoRSARepository: CryptoRSARepository,
) : BaseViewModel() {

    fun generateAESKey() = cryptoAESRepository.generateKey()
    fun encryptAES(key: String, plainText: String, method: AESMethod) =
        cryptoAESRepository.encrypt(key, plainText, method)

    fun decryptAES(key: String, encryptedText: String, method: AESMethod) =
        cryptoAESRepository.decrypt(key, encryptedText, method)

    fun generateEd25119Key() = cryptoED25519Repository.generateKey()
    fun generateEd25119Signature(encodedPrivateKey: String, plainText: String) =
        cryptoED25519Repository.generateSignature(plainText, encodedPrivateKey)

    fun verifyEd25119Signature(encodedPublicKey: String, signature: String, plainText: String) =
        cryptoED25519Repository.verifySignature(plainText, signature, encodedPublicKey)

    fun generateRSAKey() = cryptoRSARepository.generateKey()
    fun generateRSASignature(
        encodedPrivateKey: String,
        plainText: String,
        method: RSASignatureMethod
    ) =
        cryptoRSARepository.generateSignature(encodedPrivateKey, plainText, method)

    fun verifyRSASignature(
        encodedPublicKey: String,
        signature: String,
        plainText: String,
        method: RSASignatureMethod
    ) =
        cryptoRSARepository.verifySignature(encodedPublicKey, signature, plainText, method)

    fun encryptRSA(publicKey: String, plainText: String, method: RSAMethod) =
        cryptoRSARepository.encrypt(
            encodedPublicKey = publicKey,
            plainText = plainText,
            method = method
        )

    fun decryptRSA(privateKey: String, encryptedText: String, method: RSAMethod) =
        cryptoRSARepository.decrypt(
            encodedPrivateKey = privateKey,
            encryptedText = encryptedText,
            method = method
        )
}