package com.nganga.robert.furryfriends.feature_cat.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.nganga.robert.furryfriends.core.util.Extensions.getImageUrl

@Composable
fun CatScreen(
    catId: String?
){

    val catsViewModel: CatsViewModel = hiltViewModel()

    val cat = catsViewModel.cat

    LaunchedEffect(key1 = true){
        catId?.let {
            catsViewModel.fetchCatById(it)
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            model = cat.reference_image_id?.getImageUrl(),
            contentDescription = "cat image",
            contentScale = ContentScale.Crop,
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = cat.description
        )
    }

}