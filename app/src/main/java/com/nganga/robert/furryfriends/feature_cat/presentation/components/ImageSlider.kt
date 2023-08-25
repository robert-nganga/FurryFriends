package com.nganga.robert.furryfriends.feature_cat.presentation.components


import com.nganga.robert.furryfriends.feature_cat.domain.model.Image
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImageSlider(
    modifier: Modifier,
    images: List<Image>,
    pagerState: PagerState
){
    HorizontalPager(
        pageCount = images.size,
        modifier = modifier,
        state = pagerState,
        userScrollEnabled = true
    ) { index->
        AsyncImage(
            model = images[index].url,
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
    }
}