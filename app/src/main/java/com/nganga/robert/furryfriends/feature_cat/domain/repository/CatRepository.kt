package com.nganga.robert.furryfriends.feature_cat.domain.repository

import androidx.paging.PagingData
import com.nganga.robert.furryfriends.feature_cat.data.local.entities.CatEntity
import kotlinx.coroutines.flow.Flow

interface CatRepository {

    fun getAllBreeds(): Flow<PagingData<CatEntity>>
}