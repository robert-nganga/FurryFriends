package com.nganga.robert.furryfriends.feature_cat.domain.use_cases

import androidx.paging.testing.asSnapshot
import com.google.common.truth.Truth.assertThat
import com.nganga.robert.furryfriends.feature_cat.data.repos.FakeCatRepository
import com.nganga.robert.furryfriends.feature_cat.domain.model.Cat
import com.nganga.robert.furryfriends.feature_cat.domain.repos.CatRepository
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetCatBreedsTest{

    private lateinit var repository: CatRepository


    @Before
    fun setup(){
        repository = FakeCatRepository()
    }

    @Test
    fun transformsCatEntitytoCat() = runTest{
        val getCatBreeds = GetCatBreeds(repository)
        val breeds = getCatBreeds().asSnapshot()
        val cat = Cat(
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
        )
        assertThat(breeds).contains(cat)
    }
}