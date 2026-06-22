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
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import com.pnp.kannadapostermaker.presentation.model.CanvasTransformState

@Composable
fun EditorContent(
    imageUri: String,
    textLayers: List<TextLayer>,
    onLayerSelected: (String) -> Unit,
    onLayerMoved: (String, Float, Float) -> Unit,
    onImageBoundsChanged: (width: Int, height: Int) -> Unit,
    modifier: Modifier = Modifier,
    canvasTransform: CanvasTransformState,
    onCanvasTransformChanged: (
        scale: Float,
        offsetX: Float,
        offsetY: Float
    ) -> Unit,
) {

    var scale by remember {
        mutableFloatStateOf(
            canvasTransform.scale
        )
    }

    var offsetX by remember {
        mutableFloatStateOf(
            canvasTransform.offsetX
        )
    }

    var offsetY by remember {
        mutableFloatStateOf(
            canvasTransform.offsetY
        )
    }

    Box(
        modifier = modifier
            .pointerInput(Unit) {

                detectTransformGestures {

                        _,
                        pan,
                        zoom,
                        _ ->

                    scale =
                        (scale * zoom)
                            .coerceIn(
                                1f,
                                5f
                            )

                    offsetX += pan.x
                    offsetY += pan.y

                    onCanvasTransformChanged(
                        scale,
                        offsetX,
                        offsetY
                    )
                }
            }
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()

                .graphicsLayer {

                    scaleX = scale
                    scaleY = scale

                    translationX = offsetX
                    translationY = offsetY
                }
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
}