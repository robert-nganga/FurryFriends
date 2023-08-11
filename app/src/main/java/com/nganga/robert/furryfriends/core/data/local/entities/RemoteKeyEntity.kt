package com.nganga.robert.furryfriends.core.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys")
data class RemoteKeyEntity(
    @PrimaryKey
    val id: String,
    val nextKey: Int?,
    val prevKey: Int?,
)