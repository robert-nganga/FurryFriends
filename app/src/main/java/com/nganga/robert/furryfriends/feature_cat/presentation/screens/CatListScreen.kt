package com.nganga.robert.furryfriends.feature_cat.presentation.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Tune
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.nganga.robert.furryfriends.R
import com.nganga.robert.furryfriends.feature_cat.domain.model.Cat
import com.nganga.robert.furryfriends.feature_cat.presentation.components.CatItem
import com.nganga.robert.furryfriends.feature_cat.presentation.components.CategoryItem
import com.nganga.robert.furryfriends.feature_cat.presentation.components.TabView

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
                "Could not load more cats",
                Toast.LENGTH_LONG,
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

    var selectedIndex by remember {
        mutableStateOf(0)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.26f),
            horizontalAlignment = Alignment.Start
        ) {
            Box(modifier = Modifier.weight(1f))

            TabView(
                categories = listOf(
                    CategoryItem(
                        name = "Filter",
                        icon = Icons.Outlined.Tune
                    ),
                    CategoryItem(
                        name = "Cats",
                        image = painterResource(id = R.drawable.cat)
                    ),
                    CategoryItem(
                        name = "Dogs",
                        image = painterResource(id = R.drawable.dog)
                    )
                ),
                onTabSelected = {
                    selectedIndex = it
                },
                selectedTabIndex = selectedIndex
            )
        }
        Spacer(modifier = Modifier.height(10.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(MaterialTheme.colorScheme.surface),
            contentAlignment = Alignment.Center,
        ) {
            if (catBreeds.loadState.refresh is LoadState.Loading) {
                CircularProgressIndicator()
            } else {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.fillMaxHeight(),
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
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 12.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator()
                            }
                        }
                    }
                    item {
                        if(catBreeds.loadState.append is LoadState.Error){

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 12.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Text(text = "Error loading items")
                                    Spacer(modifier = Modifier.height(10.dp))
                                    Button(
                                        onClick = {
                                            catBreeds.retry()
                                        }
                                    ) {
                                        Text(text = "Retry")
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}