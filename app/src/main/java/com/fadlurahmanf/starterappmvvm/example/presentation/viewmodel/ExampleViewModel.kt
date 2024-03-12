package com.fadlurahmanf.starterappmvvm.example.presentation.viewmodel

import android.content.Context
import com.fadlurahmanf.starterappmvvm.crypto.data.repositories.CryptoAESRepository
import com.fadlurahmanf.starterappmvvm.others.layout.BaseViewModel
import com.fadlurahmanf.starterappmvvm.platform.data.repositories.PlatformRepository
import javax.inject.Inject

class ExampleViewModel @Inject constructor(
    private val platformRepository: PlatformRepository,
    private val aesCryptoRepository: CryptoAESRepository,
) : BaseViewModel() {
    fun getUserId(context: Context): String = platformRepository.getDeviceId(context)

    fun generateKey(): String = aesCryptoRepository.generateKey()

    fun encryptAESECB(text: String, key: String): String? {
        return aesCryptoRepository.encryptECB(
            plainText = text,
            secretKey = key
        )
    }

    fun encryptAESCCB(text: String, key: String): String? {
        return aesCryptoRepository.encryptCBC(
            plainText = text,
            secretKey = key
        )
    }

    fun decryptAESECB(text: String, key: String): String? {
        return aesCryptoRepository.decryptECB(
            encryptedText = text,
            secretKey = key
        )
    }

    fun decryptAESCCB(text: String, key: String): String? {
        return aesCryptoRepository.decryptCBC(
            encryptedText = text,
            secretKey = key
        )
    }
}