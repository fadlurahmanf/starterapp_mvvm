package com.fadlurahmanf.starterappmvvm.example.data.repositories

import com.fadlurahmanf.starterappmvvm.storage.data.entities.AppExampleEntity
import io.reactivex.rxjava3.core.Single

interface ExampleRepository {
    fun saveAppExampleEntity(entity: AppExampleEntity)
    fun getExistingAppExampleEntity(): Single<List<AppExampleEntity>>
}