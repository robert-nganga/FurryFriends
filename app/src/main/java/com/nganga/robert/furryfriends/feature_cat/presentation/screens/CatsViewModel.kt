package com.nganga.robert.furryfriends.feature_cat.presentation.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.nganga.robert.furryfriends.feature_cat.domain.use_cases.GetAllBreeds
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CatsViewModel@Inject constructor(
    private val getAllBreeds: GetAllBreeds,
) : ViewModel() {

    val allBreeds = getAllBreeds()
        .cachedIn(viewModelScope)
}