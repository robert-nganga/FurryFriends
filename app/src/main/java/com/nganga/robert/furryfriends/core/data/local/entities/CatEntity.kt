package com.nganga.robert.furryfriends.core.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nganga.robert.furryfriends.feature_cat.data.remote.dtos.WeightDto

@Entity(tableName = "cat_breeds")
data class CatEntity(
    val adaptability: Int,
    val affection_level: Int,
    val child_friendly: Int,
    val country_code: String,
    val country_codes: String,
    val description: String,
    val dog_friendly: Int,
    val energy_level: Int,
    @PrimaryKey val id: String,
    val indoor: Int,
    val intelligence: Int,
    val life_span: String,
    val name: String,
    val natural: Int,
    val origin: String,
    val rare: Int,
    val reference_image_id: String?
)