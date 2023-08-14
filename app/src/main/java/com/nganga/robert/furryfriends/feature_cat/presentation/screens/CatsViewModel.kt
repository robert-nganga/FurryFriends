package com.nganga.robert.furryfriends.feature_cat.presentation.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.nganga.robert.furryfriends.feature_cat.domain.use_cases.GetCatBreeds
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CatsViewModel@Inject constructor(
    private val getCatBreeds: GetCatBreeds,
) : ViewModel() {

    val allBreeds = getCatBreeds()
        .cachedIn(viewModelScope)
}