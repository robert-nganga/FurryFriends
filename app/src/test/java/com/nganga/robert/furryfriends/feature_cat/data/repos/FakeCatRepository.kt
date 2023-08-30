package com.nganga.robert.furryfriends.feature_cat.data.repos

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.testing.asPagingSourceFactory
import com.nganga.robert.furryfriends.core.data.local.entities.CatEntity
import com.nganga.robert.furryfriends.core.util.Constants
import com.nganga.robert.furryfriends.feature_cat.domain.repos.CatRepository
import kotlinx.coroutines.flow.Flow

class FakeCatRepository: CatRepository {

    private val cats = listOf(
        CatEntity(
            adaptability = 4,
            affection_level = 5,
            child_friendly = 4,
            country_code = "US",
            country_codes = "US",
            description = "Friendly and playful breed...",
            dog_friendly = 3,
            energy_level = 4,
            id = "1",
            indoor = 3,
            intelligence = 4,
            life_span = "12 - 15 years",
            name = "Siamese",
            natural = 1,
            origin = "Thailand",
            rare = 2,
            reference_image_id = "siamese_image_id"
        ),
        CatEntity(
            adaptability = 3,
            affection_level = 4,
            child_friendly = 3,
            country_code = "GB",
            country_codes = "GB",
            description = "Elegant and intelligent breed...",
            dog_friendly = 2,
            energy_level = 3,
            id = "2",
            indoor = 4,
            intelligence = 5,
            life_span = "10 - 15 years",
            name = "British Shorthair",
            natural = 1,
            origin = "United Kingdom",
            rare = 3,
            reference_image_id = "british_shorthair_image_id"
        )
    )

    override fun getAllBreeds(): Flow<PagingData<CatEntity>> {
        return Pager(
            config = PagingConfig(pageSize = Constants.ITEMS_PER_PAGE),
            pagingSourceFactory = cats.asPagingSourceFactory(),
        ).flow
    }
}