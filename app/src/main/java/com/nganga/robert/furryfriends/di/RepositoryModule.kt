package com.nganga.robert.furryfriends.di

import com.nganga.robert.furryfriends.feature_cat.data.repos.CatRepositoryImpl
import com.nganga.robert.furryfriends.feature_cat.domain.repository.CatRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun provideCatRepository(repository: CatRepositoryImpl): CatRepository
}