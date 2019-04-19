package com.catchman.data.github.storage

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey





@Entity(tableName = "user")
data class StorageUser(
    @PrimaryKey val id: Long,
    val login: String,
    val avatar_url: String
)
