package com.pnp.kannadapostermaker.presentation.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ToolCategoryBar(
    selected: ToolCategory,
    onCategorySelected: (ToolCategory) -> Unit
) {

    Row(
        modifier = androidx.compose.ui.Modifier
            .horizontalScroll(
                rememberScrollState()
            )
    ) {

        ToolCategory.entries.forEach { category ->

            FilterChip(
                selected = category == selected,

                onClick = {
                    onCategorySelected(category)
                },

                label = {
                    Text(category.name)
                }
            )
        }
    }
}