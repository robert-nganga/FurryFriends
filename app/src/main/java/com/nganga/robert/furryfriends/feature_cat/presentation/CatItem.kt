package com.nganga.robert.furryfriends.feature_cat.presentation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.nganga.robert.furryfriends.core.util.Extensions.getImageUrl
import com.nganga.robert.furryfriends.feature_cat.domain.model.Cat

@Composable
fun CatItem(
    modifier: Modifier = Modifier,
    cat: Cat
){
    Card(
        modifier = modifier
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
            model = cat.reference_image_id.getImageUrl(),
            contentDescription = "cat image",
            contentScale = ContentScale.Crop
        )
    }
}