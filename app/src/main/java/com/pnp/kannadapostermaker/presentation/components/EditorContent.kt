package com.pnp.kannadapostermaker.presentation.components

import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import coil.compose.AsyncImage
import com.pnp.kannadapostermaker.presentation.TextLayer

@Composable
fun EditorContent(
    imageUri: String,
    textLayers: List<TextLayer>,
    onLayerSelected: (String) -> Unit,
    onLayerMoved: (String, Float, Float) -> Unit,
    onImageBoundsChanged: (width: Int, height: Int) -> Unit,
    modifier: Modifier = Modifier
) {

    Box(
        modifier = modifier
    ) {

        AsyncImage(
            model = Uri.decode(imageUri),
            contentDescription = null,

            modifier = Modifier
                .fillMaxSize()
                .onGloballyPositioned {

                    onImageBoundsChanged(
                        it.size.width,
                        it.size.height
                    )
                }
        )

        textLayers.forEach { layer ->

            TextLayerItem(
                layer = layer,

                onSelected = {
                    onLayerSelected(layer.id)
                },

                onPositionChanged = { x, y ->
                    onLayerMoved(
                        layer.id,
                        x,
                        y
                    )
                }
            )
        }
    }
}