package com.nganga.robert.furryfriends.feature_cat.data.mappers

import androidx.room.PrimaryKey
import com.nganga.robert.furryfriends.feature_cat.data.local.entities.CatEntity
import com.nganga.robert.furryfriends.feature_cat.data.remote.dtos.CatDto
import com.nganga.robert.furryfriends.feature_cat.data.remote.dtos.WeightDto


fun CatDto.toCatEntity(): CatEntity{
    return CatEntity(
         adaptability = adaptability,
         affection_level = affection_level,
         child_friendly = child_friendly,
         country_code = country_code,
         country_codes = country_codes,
         description = description,
         dog_friendly = dog_friendly,
         energy_level = energy_level,
         id = id,
         indoor = indoor,
         intelligence = intelligence,
         life_span = life_span,
         name = name,
         natural = natural,
         origin = origin,
         rare = rare,
         reference_image_id = reference_image_id,
         weight = weight
    )
}