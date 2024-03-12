package com.fadlurahmanf.starterappmvvm.storage.data.datasources

import android.content.Context
import com.fadlurahmanf.starterappmvvm.storage.data.entities.AppExampleEntity
import com.fadlurahmanf.starterappmvvm.storage.domain.AppExampleDatabase
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class AppExampleLocalDatasourceImpl @Inject constructor(
    context: Context,
) : AppExampleLocalDatasource {
    private val dao = AppExampleDatabase.getDatabase(context).appExampleDao()
    override fun getAll(): Single<List<AppExampleEntity>> = dao.getAll()
    override fun insert(value: AppExampleEntity) = dao.insert(value)
    override fun update(value: AppExampleEntity) = dao.update(value)
    override fun getPrivateKey(): Single<String> = dao.getPrivateKey().map {
        it.first()
    }

    override fun getPublicKey(): Single<String> = dao.getPublicKey().map {
        it.first()
    }

    override fun delete() = dao.delete()
}