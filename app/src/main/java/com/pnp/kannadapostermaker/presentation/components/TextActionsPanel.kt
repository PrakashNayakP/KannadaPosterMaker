package com.pnp.kannadapostermaker.presentation.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TextActionsPanel(
    onAddText: () -> Unit,
    onEditText: () -> Unit,
    onDeleteText: () -> Unit,
    onIncreaseFont: () -> Unit,
    onDecreaseFont: () -> Unit
) {

    Row(
        modifier = Modifier
            .horizontalScroll(
                rememberScrollState()
            )
            .padding(8.dp)
    ) {

        Button(onClick = onAddText) {
            Text("Add")
        }

        Button(
            modifier = Modifier.padding(start = 8.dp),
            onClick = onEditText
        ) {
            Text("Edit")
        }

        Button(
            modifier = Modifier.padding(start = 8.dp),
            onClick = onDeleteText
        ) {
            Text("Delete")
        }

        Button(
            modifier = Modifier.padding(start = 8.dp),
            onClick = onIncreaseFont
        ) {
            Text("A+")
        }

        Button(
            modifier = Modifier.padding(start = 8.dp),
            onClick = onDecreaseFont
        ) {
            Text("A-")
        }
    }
}