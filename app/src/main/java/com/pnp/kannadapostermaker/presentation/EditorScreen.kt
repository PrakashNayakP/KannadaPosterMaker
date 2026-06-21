package com.pnp.kannadapostermaker.presentation

import android.net.Uri
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil.compose.AsyncImage

@Composable
fun EditorScreen(
    imageUri: String
) {

    AsyncImage(
        model = Uri.decode(imageUri),
        contentDescription = null,
        modifier = Modifier.fillMaxSize()
    )
}