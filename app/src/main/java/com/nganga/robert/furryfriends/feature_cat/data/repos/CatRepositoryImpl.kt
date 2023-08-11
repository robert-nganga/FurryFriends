package com.nganga.robert.furryfriends.feature_cat.data.repos

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.nganga.robert.furryfriends.core.util.Constants.ITEMS_PER_PAGE
import com.nganga.robert.furryfriends.core.data.local.db.CatsDatabase
import com.nganga.robert.furryfriends.core.data.local.entities.CatEntity
import com.nganga.robert.furryfriends.feature_cat.data.remote.apis.BreedsApi
import com.nganga.robert.furryfriends.feature_cat.data.remote.remote_mediator.CatRemoteMediator
import com.nganga.robert.furryfriends.feature_cat.domain.repository.CatRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CatRepositoryImpl@Inject constructor(
    private val api: BreedsApi,
    private val database: CatsDatabase,
) : CatRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun getAllBreeds(): Flow<PagingData<CatEntity>> {
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            remoteMediator = CatRemoteMediator(
                api = api,
                database = database,
            ),
            pagingSourceFactory = { database.breedsDao.getCatBreeds() },
        ).flow
    }
}