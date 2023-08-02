package com.nganga.robert.furryfriends.feature_cat.data.remote.apis

import com.nganga.robert.furryfriends.feature_cat.data.remote.dtos.CatDto

interface BreedsApi {

    suspend fun getBreeds(page: Int, pageSize: Int): List<CatDto>
}