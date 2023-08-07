package com.nganga.robert.furryfriends.di

import android.content.Context
import androidx.room.Room
import com.nganga.robert.furryfriends.core.util.Constants.CAT_DB
import com.nganga.robert.furryfriends.feature_cat.data.local.db.CatsDatabase
import com.nganga.robert.furryfriends.feature_cat.data.remote.apis.BreedsApi
import com.nganga.robert.furryfriends.feature_cat.data.remote.apis.BreedsApiImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.gson.gson
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideHttpClient() = HttpClient(Android) {
        install(Logging) {
            level = LogLevel.ALL
        }
        install(ContentNegotiation) {
            gson()
        }
    }

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
}