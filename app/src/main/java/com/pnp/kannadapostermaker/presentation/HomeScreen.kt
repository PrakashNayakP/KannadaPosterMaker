package com.pnp.kannadapostermaker.presentation

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun HomeScreen(
    onNavigateToEditor: () -> Unit
) {
    Button(
        onClick = onNavigateToEditor
    ) {
        Text("Open Editor")
    }
}