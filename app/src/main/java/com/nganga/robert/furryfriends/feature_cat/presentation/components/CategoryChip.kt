package com.nganga.robert.furryfriends.feature_cat.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import java.util.Locale.Category

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryChip(
    name: String,
    icon: ImageVector? = null,
    image: Painter? = null,
    isSelected:Boolean,
    onSelected: ()->Unit,
    modifier: Modifier = Modifier
){
    Card(
        modifier = modifier
            .padding(5.dp),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) MaterialTheme.colorScheme.primary.copy(alpha = 0.5f) else Color(0xFFD9D9D9)
        ),
        onClick = onSelected
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if(icon!=null){
                Icon(
                    modifier = Modifier
                        .size(24.dp),
                    imageVector = icon,
                    contentDescription = null
                )
            }
            if(image!=null){
                Image(
                    modifier = Modifier
                        .size(24.dp),
                    painter = image,
                    contentDescription = null
                )
            }
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = name,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}