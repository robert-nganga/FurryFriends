package com.nganga.robert.furryfriends.feature_cat.data.local.entities

import androidx.room.Entity


@Entity(tableName = "remote_keys")
data class RemoteKey(
    val id: String,
    val nextKey: Int?,
    val prevKey: Int?
)
