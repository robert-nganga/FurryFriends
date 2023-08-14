package com.nganga.robert.furryfriends.core.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.nganga.robert.furryfriends.core.presentation.ui.theme.FurryFriendsTheme
import com.nganga.robert.furryfriends.feature_cat.presentation.screens.CatListScreen
import com.nganga.robert.furryfriends.feature_cat.presentation.screens.CatsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FurryFriendsTheme {
                val catsViewModel: CatsViewModel = hiltViewModel()

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    CatListScreen(catBreeds = catsViewModel.allBreeds.collectAsLazyPagingItems())
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FurryFriendsTheme {
    }
}