package com.pnp.kannadapostermaker.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun EditorToolbar(
    onIncreaseFont: () -> Unit,
    onDecreaseFont: () -> Unit,
    onDelete: () -> Unit,
    onEdit: () -> Unit,
    onWhite: () -> Unit,
    onBlack: () -> Unit,
    onRed: () -> Unit,
    onYellow: () -> Unit
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {

        Button(onClick = onEdit) {
            Text("Edit")
        }

        Button(onClick = onIncreaseFont) {
            Text("A+")
        }

        Button(onClick = onDecreaseFont) {
            Text("A-")
        }

        Button(onClick = onRed) {
            Text("R")
        }

        Button(onClick = onYellow) {
            Text("Y")
        }

        Button(onClick = onWhite) {
            Text("W")
        }

        Button(onClick = onBlack) {
            Text("B")
        }

        Button(onClick = onDelete) {
            Text("Del")
        }
    }
}