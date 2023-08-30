package com.nganga.robert.furryfriends.feature_cat.data.mappers

import com.nganga.robert.furryfriends.core.data.local.entities.ImageEntity
import com.nganga.robert.furryfriends.feature_cat.data.remote.dtos.ImageDto
import com.nganga.robert.furryfriends.feature_cat.domain.model.Image

fun ImageEntity.toImage(): Image {
    return Image(
        id = id,
        url = url
    )
}

fun ImageDto.toImageEntity(): ImageEntity {
    return ImageEntity(
        id = id,
        url = url
    )
}