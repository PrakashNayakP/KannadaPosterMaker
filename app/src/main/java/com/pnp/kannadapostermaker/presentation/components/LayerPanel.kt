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
fun LayerPanel(
    onDeleteLayer: () -> Unit
) {

    Row(
        modifier = Modifier
            .horizontalScroll(
                rememberScrollState()
            )
            .padding(8.dp)
    ) {

        Button(
            onClick = onDeleteLayer
        ) {
            Text("Delete Layer")
        }

        Button(
            modifier = Modifier.padding(start = 8.dp),
            onClick = {}
        ) {
            Text("Bring Front")
        }

        Button(
            modifier = Modifier.padding(start = 8.dp),
            onClick = {}
        ) {
            Text("Send Back")
        }
    }
}