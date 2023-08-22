package com.nganga.robert.furryfriends.feature_cat.presentation.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.nganga.robert.furryfriends.feature_cat.domain.model.Cat
import com.nganga.robert.furryfriends.feature_cat.presentation.components.CatItem

@Composable
fun CatListScreen(
    catBreeds: LazyPagingItems<Cat>,
    onCatClick: (String)->Unit
) {
    val context = LocalContext.current

    LaunchedEffect(key1 = catBreeds.loadState) {
        if (catBreeds.loadState.refresh is LoadState.Error) {
            Toast.makeText(
                context,
                "Error" + (catBreeds.loadState.refresh as LoadState.Error).error.message,
                Toast.LENGTH_SHORT,
            ).show()
        }

        if (catBreeds.loadState.append is LoadState.Error) {
            Toast.makeText(
                context,
                "Error" + (catBreeds.loadState.append as LoadState.Error).error.message,
                Toast.LENGTH_LONG,
            ).show()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface),
        contentAlignment = Alignment.Center,
    ) {
        if (catBreeds.loadState.refresh is LoadState.Loading) {
            CircularProgressIndicator()
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(horizontal = 5.dp)

            ) {
                items(count = catBreeds.itemCount) { index ->
                    catBreeds[index]?.let {
                        CatItem(
                            cat = it,
                            onClick = {
                                onCatClick.invoke(it.id)
                            }
                        )
                    }
                }
                item {
                    if (catBreeds.loadState.append is LoadState.Loading) {
                        Box(contentAlignment = Alignment.Center) {
                            CircularProgressIndicator()
                        }
                    }
                }
            }
        }
    }
}