package com.nganga.robert.furryfriends.feature_cat.presentation.components

import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector

data class CategoryItem(
    val name: String,
    val icon: ImageVector? = null,
    val image: Painter? = null
)
