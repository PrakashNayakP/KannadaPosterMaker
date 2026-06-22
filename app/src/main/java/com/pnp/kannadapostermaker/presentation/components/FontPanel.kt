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
import com.pnp.kannadapostermaker.presentation.model.PosterFont

@Composable
fun FontPanel(
    onFontSelected: (PosterFont) -> Unit
) {

    Row(
        modifier = Modifier
            .horizontalScroll(
                rememberScrollState()
            )
            .padding(8.dp)
    ) {

        FilterChip(
            selected = false,
            onClick = {
                onFontSelected(
                    PosterFont.NOTO
                )
            },
            label = {
                Text("Noto")
            }
        )

        FilterChip(
            selected = false,
            onClick = {
                onFontSelected(
                    PosterFont.NOTO_SERIF
                )
            },
            label = {
                Text("Serif")
            }
        )

        FilterChip(
            selected = false,
            onClick = {
                onFontSelected(
                    PosterFont.BALOO
                )
            },
            label = {
                Text("Baloo")
            }
        )
    }
}