package com.nganga.robert.furryfriends.feature_cat.domain.use_cases

import com.nganga.robert.furryfriends.feature_cat.data.mappers.toCat
import com.nganga.robert.furryfriends.feature_cat.domain.model.Cat
import com.nganga.robert.furryfriends.feature_cat.domain.repos.CatRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject

class GetCatById @Inject constructor(
    private val catRepository: CatRepository
) {

    @OptIn(ExperimentalCoroutinesApi::class)
    operator fun invoke(id: String): Flow<List<Cat>>{
        return catRepository.getCatById(id)
            .mapLatest { list->
                list.map { it.toCat() }
            }
    }
}