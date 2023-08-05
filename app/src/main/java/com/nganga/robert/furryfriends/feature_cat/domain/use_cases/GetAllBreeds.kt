package com.nganga.robert.furryfriends.feature_cat.domain.use_cases

import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.map
import com.nganga.robert.furryfriends.feature_cat.data.local.entities.CatEntity
import com.nganga.robert.furryfriends.feature_cat.data.mappers.toCat
import com.nganga.robert.furryfriends.feature_cat.domain.model.Cat
import com.nganga.robert.furryfriends.feature_cat.domain.repository.CatRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetAllBreeds@Inject constructor(
    private val repository: CatRepository,
) {
    operator fun invoke(): Flow<PagingData<Cat>> {
        return repository.getAllBreeds().map { pagingData->
            pagingData.map { it.toCat() }
        }
    }
}