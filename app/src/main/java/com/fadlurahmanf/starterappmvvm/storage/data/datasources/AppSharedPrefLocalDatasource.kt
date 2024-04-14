package com.fadlurahmanf.starterappmvvm.storage.data.datasources

interface AppSharedPrefLocalDatasource {
    fun saveString(key: String, value: String)
    fun getString(key: String, defaultValue: String?): String?

    fun saveStringEncrypted(key: String, value: String)
    fun getDecryptedString(key: String, defaultValue: String?): String?

    fun saveInt(key: String, value: Int)
    fun getInt(key: String, defaultValue: Int): Int
}