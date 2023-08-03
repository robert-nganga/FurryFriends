package com.nganga.robert.furryfriends.feature_cat.data.remote.remote_mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.nganga.robert.furryfriends.feature_cat.data.local.dao.BreedsDao
import com.nganga.robert.furryfriends.feature_cat.data.local.dao.RemoteKeysDao
import com.nganga.robert.furryfriends.feature_cat.data.local.db.CatsDatabase
import com.nganga.robert.furryfriends.feature_cat.data.local.entities.CatEntity
import com.nganga.robert.furryfriends.feature_cat.data.local.entities.RemoteKey
import com.nganga.robert.furryfriends.feature_cat.data.mappers.toCatEntity
import com.nganga.robert.furryfriends.feature_cat.data.remote.apis.BreedsApi
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class CatRemoteMediator @Inject constructor(
    private val api: BreedsApi,
    private val database: CatsDatabase
): RemoteMediator<Int, CatEntity>() {

    private val remoteKeysDao: RemoteKeysDao = database.remoteKeysDao
    private val breedsDao: BreedsDao = database.breedsDao

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CatEntity>
    ): MediatorResult {

        return try {
            val currentPage = when(loadType){
                LoadType.REFRESH -> {
                    val remoteKey = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKey?.prevKey?: 1
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.nextKey
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    nextPage
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKeys?.prevKey
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    prevPage
                }
            }

            val response = api.getBreeds(currentPage, state.config.pageSize)
            val endOfPaginationReached = response.isEmpty()
            val prevPage = if (currentPage == 1) null else currentPage - 1
            val nextPage = if (endOfPaginationReached) null else currentPage + 1

            database.withTransaction {
                if (loadType == LoadType.REFRESH){
                    breedsDao.clearAllBreeds()
                    remoteKeysDao.clearAllKeys()
                }
                val remoteKeys = response.map { cat->
                    RemoteKey(
                        id = cat.id,
                        prevKey = prevPage,
                        nextKey = nextPage
                    )
                }
                breedsDao.insertCatBreeds(response.map { it.toCatEntity() })
                remoteKeysDao.addRemoteKeys(remoteKeys)
            }
            MediatorResult.Success(endOfPaginationReached)
        } catch (e: Exception){
            MediatorResult.Error(e)
        }

    }

    private fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, CatEntity>
    ): RemoteKey? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                remoteKeysDao.getRemoteKey(id = id)
            }
        }
    }

    private fun getRemoteKeyForFirstItem(
        state: PagingState<Int, CatEntity>
    ): RemoteKey? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { cat ->
                remoteKeysDao.getRemoteKey(cat.id)
            }
    }

    private fun getRemoteKeyForLastItem(
        state: PagingState<Int, CatEntity>
    ): RemoteKey? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { cat ->
                remoteKeysDao.getRemoteKey(cat.id)
            }
    }
}