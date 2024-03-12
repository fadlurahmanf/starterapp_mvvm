package com.fadlurahmanf.starterappmvvm.storage.data.datasources

import com.fadlurahmanf.starterappmvvm.storage.data.entities.AppExampleEntity
import io.reactivex.rxjava3.core.Single

interface AppExampleLocalDatasource {
    fun getAll(): Single<List<AppExampleEntity>>
    fun insert(value: AppExampleEntity)
    fun update(value: AppExampleEntity)
    fun getPrivateKey(): Single<String>

    fun getPublicKey(): Single<String>

    fun delete()
}