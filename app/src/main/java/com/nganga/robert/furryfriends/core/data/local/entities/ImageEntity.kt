package com.nganga.robert.furryfriends.core.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "images_table")
data class ImageEntity(
    @PrimaryKey
    val id: String,
    val url: String,
    val catId: String = ""
)
