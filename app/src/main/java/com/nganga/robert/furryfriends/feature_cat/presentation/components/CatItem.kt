package com.nganga.robert.furryfriends.feature_cat.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.nganga.robert.furryfriends.core.util.Extensions.getImageUrl
import com.nganga.robert.furryfriends.feature_cat.domain.model.Cat

@Composable
fun CatItem(
    modifier: Modifier = Modifier,
    cat: Cat,
    onClick: ()->Unit
) {
    Card(
        modifier = modifier.padding(5.dp),
        elevation = CardDefaults.cardElevation(6.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onPrimary
        )

    ) {
        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ){
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .clickable {
                        onClick.invoke()
                    },
                model = cat.reference_image_id?.getImageUrl(),
                contentDescription = "cat image",
                contentScale = ContentScale.Crop,
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 5.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = cat.name,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2,
                    fontStyle = FontStyle.Italic
                )
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    tint = MaterialTheme.colorScheme.primary,
                    contentDescription = null
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}