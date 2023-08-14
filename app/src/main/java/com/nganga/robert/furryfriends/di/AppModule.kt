package com.nganga.robert.furryfriends.di

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.nganga.robert.furryfriends.core.data.local.db.CatsDatabase
import com.nganga.robert.furryfriends.core.util.Constants
import com.nganga.robert.furryfriends.core.util.Constants.CAT_DB
import com.nganga.robert.furryfriends.feature_cat.data.remote.apis.BreedsApi
import com.nganga.robert.furryfriends.feature_cat.data.remote.apis.BreedsApiImpl
import com.nganga.robert.furryfriends.feature_cat.data.remote.remote_mediator.CatRemoteMediator
import com.nganga.robert.furryfriends.feature_cat.data.util.HttpClientFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideHttpClient() = HttpClientFactory.create(Android.create())

    @Provides
    @Singleton
    fun providesCatDatabase(@ApplicationContext context: Context): CatsDatabase {
        return Room.databaseBuilder(
            context,
            CatsDatabase::class.java,
            CAT_DB,
        ).build()
    }

    @Provides
    @Singleton
    fun providesBreedsApi(client: HttpClient): BreedsApi {
        return BreedsApiImpl(client)
    }

    @OptIn(ExperimentalPagingApi::class)
    @Provides
    @Singleton
    fun providePager(api: BreedsApi, database: CatsDatabase) = Pager(
        config = PagingConfig(pageSize = Constants.ITEMS_PER_PAGE),
        remoteMediator = CatRemoteMediator(
            api = api,
            database = database,
        ),
        pagingSourceFactory = { database.breedsDao.getCatBreeds() },
    )
}