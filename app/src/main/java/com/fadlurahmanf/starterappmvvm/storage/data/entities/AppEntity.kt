package com.fadlurahmanf.starterappmvvm.storage.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "appEntity")
data class AppEntity(
    @PrimaryKey
    var deviceId: String,
    var encryptedGuestToken: String? = null,
)