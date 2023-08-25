package com.nganga.robert.furryfriends.feature_cat.presentation.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.nganga.robert.furryfriends.core.util.Extensions.getImageUrl
import com.nganga.robert.furryfriends.feature_cat.presentation.components.Attribute

@OptIn(ExperimentalFoundationApi::class)
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

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface),
        verticalArrangement = Arrangement.Top
    ){
        stickyHeader {
            CatScreenTopAppBar(
                onBackClick = {  },
                onFavoriteToggle = {}
            )
        }
        item { Spacer(modifier = Modifier.height(10.dp)) }
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(200.dp)
                        .clip(RoundedCornerShape(12.dp)),
                    model = cat.reference_image_id?.getImageUrl(),
                    contentDescription = "cat image",
                    contentScale = ContentScale.Crop,
                )
            }
        }
        item { Spacer(modifier = Modifier.height(10.dp)) }
        item {
            DescriptionSection(
                name = cat.name,
                origin = cat.origin,
                lifeSpan = cat.life_span,
                description = cat.description
            )
        }
        item { Spacer(modifier = Modifier.height(10.dp)) }
        item {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Attribute(
                    modifier = Modifier.weight(1f),
                    name = "Dog Friendly",
                    value = cat.dog_friendly / 5.0f
                )
                Attribute(
                    modifier = Modifier.weight(1f),
                    name = "Energy Level",
                    value = cat.energy_level / 5.0f
                )
            }
        }
        item {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Attribute(
                    modifier = Modifier.weight(1f),
                    name = "Intelligence",
                    value = cat.intelligence / 5.0f
                )
                Attribute(
                    modifier = Modifier.weight(1f),
                    name = "Child Friendly",
                    value = cat.child_friendly / 5.0f
                )
            }
        }
        item {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Attribute(
                    modifier = Modifier.weight(1f),
                    name = "Affection Level",
                    value = cat.affection_level / 5.0f
                )
                Box(modifier = Modifier.weight(1f))
            }
        }

        item { Spacer(modifier = Modifier.height(20.dp)) }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatScreenTopAppBar(
    modifier: Modifier = Modifier,
    onBackClick: ()->Unit,
    onFavoriteToggle: ()->Unit
){
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
       Card(
           shape = RoundedCornerShape(5.dp),
           onClick = onBackClick,
           elevation = CardDefaults.cardElevation(8.dp),
           colors = CardDefaults.cardColors(
               containerColor = MaterialTheme.colorScheme.onPrimary
           )
       ) {
           Icon(
               modifier = Modifier.padding(12.dp),
               imageVector = Icons.Default.ArrowBack,
               contentDescription = null
           )
       }

        Card(
            shape = RoundedCornerShape(5.dp),
            onClick = onFavoriteToggle,
            elevation = CardDefaults.cardElevation(8.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.onPrimary
            )
        ) {
            Icon(
                modifier = Modifier.padding(12.dp),
                imageVector = Icons.Filled.Favorite,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
fun DescriptionSection(
    name: String,
    origin: String,
    lifeSpan: String,
    description: String,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 10.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.titleLarge.copy(
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                letterSpacing = 0.4.sp
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    )
                ){
                    append("Origin : ")
                }
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Normal,
                        fontSize = 13.sp
                    )
                ){
                    append(origin)
                }
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    )
                ){
                    append("Life Span : ")
                }
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Normal,
                        fontSize = 13.sp
                    )
                ){
                    append(lifeSpan)
                }
            }
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = description,
            style = MaterialTheme.typography.bodySmall
        )
    }
}

