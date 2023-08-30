package com.nganga.robert.furryfriends.feature_cat.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TabView(
    modifier: Modifier = Modifier,
    categories: List<CategoryItem>,
    onTabSelected: (selectedIndex: Int) -> Unit,
    selectedTabIndex: Int
){
    Row(
        modifier = modifier
            .padding(horizontal = 5.dp)
    ) {
        categories.forEachIndexed { index, category ->
            CategoryChip(
                name = category.name,
                icon = category.icon,
                image = category.image,
                isSelected = selectedTabIndex == index,
                onSelected = { onTabSelected(index) }
            )
        }
    }
}