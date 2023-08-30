package com.nganga.robert.furryfriends.feature_cat.domain.repos

import androidx.paging.PagingData
import com.nganga.robert.furryfriends.core.data.local.entities.CatEntity
import com.nganga.robert.furryfriends.core.data.local.entities.ImageEntity
import com.nganga.robert.furryfriends.core.util.ResultState
import com.nganga.robert.furryfriends.feature_cat.domain.model.Image
import kotlinx.coroutines.flow.Flow

interface CatRepository {

    fun getAllBreeds(): Flow<PagingData<CatEntity>>

    fun getCatById(id: String): Flow<List<CatEntity>>

    suspend fun getCatImages(id: String): ResultState<List<Image>>
}