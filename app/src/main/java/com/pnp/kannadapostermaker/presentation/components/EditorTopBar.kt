package com.pnp.kannadapostermaker.presentation.components

import androidx.compose.material3.*
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditorTopBar(
    onExportClick: () -> Unit
) {

    TopAppBar(
        title = {
            Text("Poster Editor")
        },

        actions = {

            TextButton(
                onClick = onExportClick
            ) {
                Text("Export")
            }
        }
    )
}