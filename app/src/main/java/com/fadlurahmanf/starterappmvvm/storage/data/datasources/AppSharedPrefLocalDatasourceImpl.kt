package com.fadlurahmanf.starterappmvvm.storage.data.datasources

import android.content.Context
import android.util.Log
import com.fadlurahmanf.starterappmvvm.BuildConfig
import com.fadlurahmanf.starterappmvvm.core.shared.constant.AppConstant
import com.github.fadlurahmanfdev.core_crypto.data.enums.AESMethod
import com.github.fadlurahmanfdev.core_crypto.data.repositories.CryptoAESRepository
import javax.inject.Inject

class AppSharedPrefLocalDatasourceImpl @Inject constructor(
    context: Context,
    private val cryptoAESRepository: CryptoAESRepository,
) : AppSharedPrefLocalDatasource {
    private val sharedPref by lazy {
        context.getSharedPreferences(
            "${BuildConfig.APPLICATION_ID}.shared-pref-key",
            Context.MODE_PRIVATE
        )
    }

    private val appSharedPrefKey by lazy {
        getSharedPrefKey()
    }

    override fun saveString(key: String, value: String) {
        return sharedPref.edit().putString(key, value).apply()
    }

    override fun getString(key: String, defaultValue: String?): String? {
        return sharedPref.getString(key, defaultValue)
    }

    override fun saveStringEncrypted(key: String, value: String) {
        try {
            val encryptedValue = cryptoAESRepository.encrypt(value, appSharedPrefKey, method = AESMethod.AES_GCM_NoPadding)
            if (encryptedValue != null) {
                saveString(key, encryptedValue)
                Log.d(AppConstant.LOGGER_TAG, "success saveStringEncrypted: $encryptedValue")
                return
            }
            Log.e(AppConstant.LOGGER_TAG, "failed saveStringEncrypted: encrypted value is null")
        } catch (e: Throwable) {
            Log.e(AppConstant.LOGGER_TAG, "failed saveStringEncrypted: ${e.message}")
        }
    }

    override fun getDecryptedString(key: String, defaultValue: String?): String? {
        try {
            val encryptedValue = getString(key, null)
            if (encryptedValue != null) {
                val decryptedValue = cryptoAESRepository.decrypt(encryptedValue, appSharedPrefKey, method = AESMethod.AES_GCM_NoPadding)
                if (decryptedValue != null) {
                    return decryptedValue
                } else {
                    Log.e(
                        AppConstant.LOGGER_TAG,
                        "failed getEncryptedString: decrypted value is null"
                    )
                    return defaultValue
                }
            } else {
                Log.e(AppConstant.LOGGER_TAG, "failed getEncryptedString: encrypted value is null")
                return defaultValue
            }
        } catch (e: Throwable) {
            Log.e(AppConstant.LOGGER_TAG, "failed getEncryptedString: ${e.message}")
            return defaultValue
        }
    }

    override fun saveInt(key: String, value: Int) {
        return sharedPref.edit().putInt(key, value).apply()
    }

    override fun getInt(key: String, defaultValue: Int): Int {
        return sharedPref.getInt(key, defaultValue)
    }

    private fun getSharedPrefKey(): String {
        var key: String? = sharedPref.getString("sharedPrefKey", null)
        if (key == null) {
            key = cryptoAESRepository.generateKey()
            saveString("sharedPrefKey", key)
        }
        return key
    }
}