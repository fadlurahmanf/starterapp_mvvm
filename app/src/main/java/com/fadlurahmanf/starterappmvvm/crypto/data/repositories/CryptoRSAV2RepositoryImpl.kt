package com.fadlurahmanf.starterappmvvm.crypto.data.repositories

import android.util.Log
import com.fadlurahmanf.starterappmvvm.core.shared.constant.AppConstant
import com.fadlurahmanf.starterappmvvm.crypto.data.enums.RSAMethod
import com.fadlurahmanf.starterappmvvm.crypto.data.model.CryptoKey
import com.fadlurahmanf.starterappmvvm.crypto.others.BaseCryptoV2
import java.security.KeyFactory
import java.security.KeyPairGenerator
import java.security.Signature
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.X509EncodedKeySpec

class CryptoRSAV2RepositoryImpl : BaseCryptoV2(), CryptoRSAV2Repository {
    override fun generateKey(): CryptoKey {
        val keyGen = KeyPairGenerator.getInstance("RSA")
        keyGen.initialize(2048)
        val keyPair = keyGen.generateKeyPair()
        val publicKey = encode(keyPair.public.encoded)
        val privateKey = encode(keyPair.private.encoded)
        return CryptoKey(privateKey = privateKey, publicKey = publicKey)
    }

    override fun generateSignature(
        encodedPrivateKey: String,
        plainText: String,
        method: RSAMethod,
    ): String? {
        return try {
            val privateKeySpec = PKCS8EncodedKeySpec(decode(encodedPrivateKey))
            val privateKey = KeyFactory.getInstance("RSA").generatePrivate(privateKeySpec)
            val signer = Signature.getInstance(getRSASignatureAlgorithmBasedOnFlow(method))
            signer.initSign(privateKey)
            signer.update(plainText.toByteArray())
            encode(signer.sign())
        } catch (e: Throwable) {
            Log.e(AppConstant.LOGGER_TAG, "failed generateSignature: ${e.message}")
            null
        }
    }

    override fun verifySignature(
        encodedPublicKey: String,
        encodedSignature: String,
        plainText: String,
        method: RSAMethod,
    ): Boolean {
        return try {
            val publicKeySpec = X509EncodedKeySpec(decode(encodedPublicKey))
            val publicKey = KeyFactory.getInstance("RSA").generatePublic(publicKeySpec)
            val signer = Signature.getInstance(getRSASignatureAlgorithmBasedOnFlow(method))
            signer.initVerify(publicKey)
            signer.update(plainText.toByteArray())
            signer.verify(decode(encodedSignature))
            true
        } catch (e: Throwable) {
            Log.e(AppConstant.LOGGER_TAG, "failed verifySignature: ${e.message}")
            false
        }
    }
}