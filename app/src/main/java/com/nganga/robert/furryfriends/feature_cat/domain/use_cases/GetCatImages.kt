package com.nganga.robert.furryfriends.feature_cat.domain.use_cases

import com.nganga.robert.furryfriends.core.util.ResultState
import com.nganga.robert.furryfriends.feature_cat.domain.model.Image
import com.nganga.robert.furryfriends.feature_cat.domain.repos.CatRepository
import javax.inject.Inject

class GetCatImages @Inject constructor(
    private val catRepository: CatRepository

) {
    suspend operator fun invoke(id: String): ResultState<List<Image>>{
        return catRepository.getCatImages(id)
    }
}