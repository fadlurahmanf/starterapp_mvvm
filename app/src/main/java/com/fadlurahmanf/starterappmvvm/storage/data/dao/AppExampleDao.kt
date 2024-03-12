package com.fadlurahmanf.starterappmvvm.storage.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.fadlurahmanf.starterappmvvm.storage.data.entities.AppExampleEntity
import com.fadlurahmanf.starterappmvvm.storage.others.AppDbConstant
import io.reactivex.rxjava3.core.Single

@Dao
interface AppExampleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(value: AppExampleEntity)

    @Update
    fun update(value: AppExampleEntity)

    @Query("SELECT * FROM ${AppDbConstant.APP_EXAMPLE_TABLE_NAME}")
    fun getAll(): Single<List<AppExampleEntity>>

    @Query("SELECT encodedPublicKey FROM ${AppDbConstant.APP_EXAMPLE_TABLE_NAME}")
    fun getPublicKey(): Single<List<String>>

    @Query("SELECT encodedPrivateKey FROM ${AppDbConstant.APP_EXAMPLE_TABLE_NAME}")
    fun getPrivateKey(): Single<List<String>>

    @Query("DELETE FROM ${AppDbConstant.APP_EXAMPLE_TABLE_NAME}")
    fun delete()
}