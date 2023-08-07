package com.nganga.robert.furryfriends.feature_cat.data.remote.dtos

import kotlinx.serialization.Serializable


//@Serializable
data class WeightDto(
    val imperial: String,
    val metric: String,
)