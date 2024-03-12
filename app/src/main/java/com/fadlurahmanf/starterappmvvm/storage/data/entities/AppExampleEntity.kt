package com.fadlurahmanf.starterappmvvm.storage.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "appExampleEntity")
data class AppExampleEntity(
    @PrimaryKey
    var deviceId: String,
    var encryptedGuestToken: String? = null,
)