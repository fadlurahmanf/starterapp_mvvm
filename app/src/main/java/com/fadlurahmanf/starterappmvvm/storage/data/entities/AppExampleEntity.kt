package com.fadlurahmanf.starterappmvvm.storage.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fadlurahmanf.starterappmvvm.storage.others.AppDbConstant

@Entity(tableName = AppDbConstant.APP_EXAMPLE_TABLE_NAME)
data class AppExampleEntity(
    @PrimaryKey
    val deviceId: String,
    val encodedPublicKey: String,
    val encodedPrivateKey: String,
    var encryptedGuestToken: String? = null,
)