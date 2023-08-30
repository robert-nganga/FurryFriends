package com.nganga.robert.furryfriends.feature_cat.presentation.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.nganga.robert.furryfriends.core.util.ResultState
import com.nganga.robert.furryfriends.feature_cat.domain.model.Cat
import com.nganga.robert.furryfriends.feature_cat.domain.model.Image
import com.nganga.robert.furryfriends.feature_cat.domain.use_cases.GetCatBreeds
import com.nganga.robert.furryfriends.feature_cat.domain.use_cases.GetCatById
import com.nganga.robert.furryfriends.feature_cat.domain.use_cases.GetCatImages
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatsViewModel@Inject constructor(
    getCatBreeds: GetCatBreeds,
    private val getCatById: GetCatById,
    private val getCatImages: GetCatImages
) : ViewModel() {

    val allBreeds = getCatBreeds()
        .cachedIn(viewModelScope)

    var cat by mutableStateOf(Cat())
        private set

    var catImages by mutableStateOf<List<Image>>(emptyList())
        private  set

    fun fetchCatById(id:String) = viewModelScope.launch(Dispatchers.IO){
        getCatById(id).collectLatest { results->
            if(results.isNotEmpty()){
                cat = results.first()
            }
        }
    }

    fun fetchCatImages(id:String) = viewModelScope.launch(Dispatchers.IO){
        val result = getCatImages(id)
        when(result.status){
            ResultState.Status.SUCCESS->{
                result.data?.let {
                    catImages = it
                }
            }
            ResultState.Status.LOADING->{}
            ResultState.Status.ERROR ->{}
        }
    }
}