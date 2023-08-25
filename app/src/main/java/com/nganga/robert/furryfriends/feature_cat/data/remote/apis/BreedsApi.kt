package com.nganga.robert.furryfriends.feature_cat.data.remote.apis

import com.nganga.robert.furryfriends.core.util.ResultState
import com.nganga.robert.furryfriends.feature_cat.data.remote.dtos.CatDto
import com.nganga.robert.furryfriends.feature_cat.data.remote.dtos.ImageDto

interface BreedsApi {

    suspend fun getBreeds(page: Int, pageSize: Int): List<CatDto>

    suspend fun getImages(id: String): List<ImageDto>
}