package com.nganga.robert.furryfriends.feature_cat.domain.use_cases

import androidx.paging.Pager
import com.nganga.robert.furryfriends.feature_cat.data.local.entities.CatEntity
import com.nganga.robert.furryfriends.feature_cat.domain.repository.CatRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllBreeds@Inject constructor(
    private val repository: CatRepository
) {
    operator fun invoke(page: Int, pageSize: Int): Flow<Pager<Int, CatEntity>> {
        return repository.getAllBreeds(page, pageSize)
    }
}