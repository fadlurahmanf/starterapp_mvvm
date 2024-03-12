package com.fadlurahmanf.starterappmvvm.example.data.repositories

import com.fadlurahmanf.starterappmvvm.storage.data.datasources.AppExampleLocalDatasource
import com.fadlurahmanf.starterappmvvm.storage.data.entities.AppExampleEntity
import javax.inject.Inject

class ExampleRepositoryImpl @Inject constructor(
    private val appExampleLocalDatasource: AppExampleLocalDatasource
) : ExampleRepository {
    override fun saveAppExampleEntity(entity: AppExampleEntity) =
        appExampleLocalDatasource.insert(entity)

    override fun getExistingAppExampleEntity() = appExampleLocalDatasource.getAll()
}