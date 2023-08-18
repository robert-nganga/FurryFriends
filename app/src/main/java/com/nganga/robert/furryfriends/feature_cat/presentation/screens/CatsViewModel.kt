package com.nganga.robert.furryfriends.feature_cat.presentation.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.nganga.robert.furryfriends.feature_cat.domain.model.Cat
import com.nganga.robert.furryfriends.feature_cat.domain.use_cases.GetCatBreeds
import com.nganga.robert.furryfriends.feature_cat.domain.use_cases.GetCatById
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatsViewModel@Inject constructor(
    getCatBreeds: GetCatBreeds,
    private val getCatById: GetCatById
) : ViewModel() {

    val allBreeds = getCatBreeds()
        .cachedIn(viewModelScope)

    var cat by mutableStateOf(Cat())
        private set

    fun fetchCatById(id:String) = viewModelScope.launch(Dispatchers.IO){
        getCatById(id).collectLatest { results->
            if(results.isNotEmpty()){
                cat = results.first()
            }
        }
    }
}