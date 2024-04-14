package com.fadlurahmanf.starterappmvvm.example.data.repositories

import com.fadlurahmanf.starterappmvvm.storage.data.datasources.AppExampleLocalDatasource
import com.fadlurahmanf.starterappmvvm.storage.data.datasources.AppSharedPrefLocalDatasource
import com.fadlurahmanf.starterappmvvm.storage.data.entities.AppExampleEntity
import javax.inject.Inject

class ExampleStorageRepositoryImpl @Inject constructor(
    private val appExampleLocalDatasource: AppExampleLocalDatasource,
    private val appSharedPrefLocalDatasource: AppSharedPrefLocalDatasource
) : ExampleStorageRepository {
    override fun saveAppExampleEntity(entity: AppExampleEntity) =
        appExampleLocalDatasource.insert(entity)

    override fun getExistingAppExampleEntity() = appExampleLocalDatasource.getAll()

    override fun saveEncryptedString(key: String, plainText: String) =
        appSharedPrefLocalDatasource.saveStringEncrypted(key, plainText)

    override fun getString(key: String): String? =
        appSharedPrefLocalDatasource.getString(key, null)

    override fun getDecryptedString(key: String, defaultValue: String?): String? =
        appSharedPrefLocalDatasource.getDecryptedString(key, defaultValue)
}