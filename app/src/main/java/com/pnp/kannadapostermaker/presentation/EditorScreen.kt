package com.pnp.kannadapostermaker.presentation

import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.remember
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset
import kotlin.math.roundToInt


@Composable
fun EditorScreen(
    imageUri: String
) {

    var showDialog by remember {
        mutableStateOf(false)
    }

    var inputText by remember {
        mutableStateOf("")
    }

    var textLayers by remember {
        mutableStateOf(
            listOf(
                TextLayer(
                    id = "1",
                    text = "ನಿಮ್ಮ ಪಠ್ಯ"
                )
            )
        )
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        AsyncImage(
            model = Uri.decode(imageUri),
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )

        textLayers.forEach { layer ->

            var offsetX by remember(layer.id) {
                mutableStateOf(layer.offsetX)
            }

            var offsetY by remember(layer.id) {
                mutableStateOf(layer.offsetY)
            }

            Text(
                text = layer.text,

                modifier = Modifier
                    .align(Alignment.Center)
                    .offset {
                        IntOffset(
                            offsetX.roundToInt(),
                            offsetY.roundToInt()
                        )
                    }
                    .pointerInput(layer.id) {

                        detectDragGestures { _, dragAmount ->

                            offsetX += dragAmount.x
                            offsetY += dragAmount.y

                            textLayers =
                                textLayers.map {

                                    if (it.id == layer.id) {
                                        it.copy(
                                            offsetX = offsetX,
                                            offsetY = offsetY
                                        )
                                    } else {
                                        it
                                    }
                                }
                        }
                    }
            )
        }

        FloatingActionButton(
            onClick = {
                showDialog = true
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Text("+")
        }

        if (showDialog) {

            AlertDialog(
                onDismissRequest = {
                    showDialog = false
                },

                title = {
                    Text("Add Text")
                },

                text = {

                    OutlinedTextField(
                        value = inputText,
                        onValueChange = {
                            inputText = it
                        },
                        label = {
                            Text("Enter Text")
                        }
                    )
                },

                confirmButton = {

                    TextButton(
                        onClick = {

                            if (inputText.isNotBlank()) {

                                textLayers =
                                    textLayers + TextLayer(
                                        id = System.currentTimeMillis().toString(),
                                        text = inputText,
                                        offsetX = 0f,
                                        offsetY = 0f
                                    )

                                inputText = ""
                            }

                            showDialog = false
                        }
                    ) {
                        Text("Add")
                    }
                },

                dismissButton = {

                    TextButton(
                        onClick = {
                            showDialog = false
                        }
                    ) {
                        Text("Cancel")
                    }
                }
            )
        }
    }
}


@Composable
fun DraggableText(
    layer: TextLayer,
    onPositionChanged: (Float, Float) -> Unit
) {

    var offsetX by remember(layer.id) {
        mutableStateOf(layer.offsetX)
    }

    var offsetY by remember(layer.id) {
        mutableStateOf(layer.offsetY)
    }

    BasicText(
        text = layer.text,

        modifier = Modifier
            .offset {
                IntOffset(
                    offsetX.roundToInt(),
                    offsetY.roundToInt()
                )
            }
            .pointerInput(Unit) {

                detectDragGestures { _, dragAmount ->

                    offsetX += dragAmount.x
                    offsetY += dragAmount.y

                    onPositionChanged(
                        offsetX,
                        offsetY
                    )
                }
            }
    )
}