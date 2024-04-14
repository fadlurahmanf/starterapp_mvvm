package com.fadlurahmanf.starterappmvvm.example.data.repositories

import com.fadlurahmanf.starterappmvvm.storage.data.entities.AppExampleEntity
import io.reactivex.rxjava3.core.Single

interface ExampleStorageRepository {
    fun saveAppExampleEntity(entity: AppExampleEntity)
    fun getExistingAppExampleEntity(): Single<List<AppExampleEntity>>
    fun saveEncryptedString(key: String, plainText: String)
    fun getString(key: String): String?
    fun getDecryptedString(key: String, defaultValue: String?): String?
}