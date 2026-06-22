package com.pnp.kannadapostermaker.presentation.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FontPanel() {

    val fonts = listOf(
        "Noto Kannada",
        "Tunga",
        "Baloo",
        "Mallige",
        "Kedage"
    )

    Row(
        modifier = Modifier
            .horizontalScroll(
                rememberScrollState()
            )
            .padding(8.dp)
    ) {

        fonts.forEach {

            FilterChip(
                selected = false,
                onClick = {},
                label = {
                    Text(it)
                }
            )
        }
    }
}