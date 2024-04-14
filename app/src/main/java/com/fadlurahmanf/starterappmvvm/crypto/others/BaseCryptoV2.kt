package com.fadlurahmanf.starterappmvvm.crypto.others

import android.util.Base64
import com.fadlurahmanf.starterappmvvm.crypto.data.enums.AESMethod
import com.fadlurahmanf.starterappmvvm.crypto.data.enums.RSAMethod
import com.fadlurahmanf.starterappmvvm.crypto.data.enums.RSAMethod.*

abstract class BaseCryptoV2 {

    open fun encode(byte: ByteArray): String {
        return Base64.encodeToString(byte, Base64.NO_WRAP)
    }

    open fun decode(text: String): ByteArray {
        return Base64.decode(text.toByteArray(), Base64.NO_WRAP)
    }

    open fun decode(byte: ByteArray): ByteArray {
        return Base64.decode(byte, Base64.NO_WRAP)
    }

    fun getAESTransformationBasedOnFlow(method: AESMethod): String {
        return when (method) {
            AESMethod.AES_CBC_ISO10126Padding -> "AES/CBC/ISO10126Padding"
            AESMethod.AES_GCM_NoPadding -> "AES/GCM/NoPadding"
        }
    }

    fun getRSASignatureAlgorithmBasedOnFlow(method: RSAMethod): String {
        return when (method) {
            MD5withRSA -> "MD5withRSA"
            SHA1withRSA -> "SHA1withRSA"
            SHA256withRSA -> "SHA256withRSA"
            SHA384withRSA -> "SHA384withRSA"
            SHA512withRSA -> "SHA512withRSA"
        }
    }
}