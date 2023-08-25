package com.nganga.robert.furryfriends.feature_cat.data.repos

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.nganga.robert.furryfriends.core.data.local.db.CatsDatabase
import com.nganga.robert.furryfriends.core.data.local.entities.CatEntity
import com.nganga.robert.furryfriends.core.util.Constants.ITEMS_PER_PAGE
import com.nganga.robert.furryfriends.core.util.ResultState
import com.nganga.robert.furryfriends.feature_cat.data.mappers.toImage
import com.nganga.robert.furryfriends.feature_cat.data.mappers.toImageEntity
import com.nganga.robert.furryfriends.feature_cat.data.remote.apis.BreedsApi
import com.nganga.robert.furryfriends.feature_cat.data.remote.remote_mediator.CatRemoteMediator
import com.nganga.robert.furryfriends.feature_cat.domain.model.Image
import com.nganga.robert.furryfriends.feature_cat.domain.repos.CatRepository
import kotlinx.coroutines.flow.Flow
import timber.log.Timber
import javax.inject.Inject

class CatRepositoryImpl@Inject constructor(
    private val api: BreedsApi,
    private val database: CatsDatabase,
) : CatRepository {

    private val catBreedsDao = database.catBreedsDao
    private val imageDao = database.imageDao

    @OptIn(ExperimentalPagingApi::class)
    override fun getAllBreeds(): Flow<PagingData<CatEntity>> {
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            remoteMediator = CatRemoteMediator(
                api = api,
                database = database,
            ),
            pagingSourceFactory = { database.catBreedsDao.getCatBreeds() },
        ).flow
    }

    override fun getCatById(id: String): Flow<List<CatEntity>> {
        return catBreedsDao.getCatById(id)
    }

    override suspend fun getCatImages(id: String): ResultState<List<Image>> {
        val cachedImages = imageDao.getImages(id)
        val shouldFetch = cachedImages.isEmpty()
        return if (shouldFetch){
            try {
                val images = api.getImages(id)
                imageDao.insertImages(images.map { it.toImageEntity().copy(catId = id) })
                ResultState.success(imageDao.getImages(id).map { it.toImage() })
            } catch (e: Exception){
                Timber.e(e)
                ResultState.error(e.localizedMessage)
            }
        }else{
            ResultState.success(cachedImages.map { it.toImage() })
        }
    }

}