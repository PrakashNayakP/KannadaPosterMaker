package com.pnp.kannadapostermaker.presentation

import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.pnp.kannadapostermaker.presentation.components.EditorToolbar
import com.pnp.kannadapostermaker.presentation.components.TextLayerItem
import com.pnp.kannadapostermaker.presentation.viewmodel.EditorViewModel

@Composable
fun EditorScreen(
    imageUri: String,
    viewModel: EditorViewModel = viewModel()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    var isEditMode by remember {
        mutableStateOf(false)
    }

    var showDialog by remember {
        mutableStateOf(false)
    }

    var inputText by remember {
        mutableStateOf("")
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        AsyncImage(
            model = Uri.decode(imageUri),
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )

        uiState.textLayers.forEach { layer ->

            TextLayerItem(
                layer = layer,

                onSelected = {
                    viewModel.selectLayer(layer.id)
                },

                onPositionChanged = { x, y ->
                    viewModel.updatePosition(
                        layer.id,
                        x,
                        y
                    )
                }
            )
        }

        if (uiState.selectedLayerId != null) {

            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 90.dp)
            ) {

                EditorToolbar(

                    onEdit = {

                            isEditMode = true

                            inputText =
                                uiState.textLayers
                                    .firstOrNull {
                                        it.id == uiState.selectedLayerId
                                    }
                                    ?.text.orEmpty()

                            showDialog = true
                    },

                    onDelete = {
                        viewModel.deleteSelected()
                    },

                    onIncreaseFont = {
                        viewModel.increaseFont()
                    },

                    onDecreaseFont = {
                        viewModel.decreaseFont()
                    },

                    onRed = {
                        viewModel.changeColor(Color.Red)
                    },

                    onYellow = {
                        viewModel.changeColor(Color.Yellow)
                    },

                    onWhite = {
                        viewModel.changeColor(Color.White)
                    },

                    onBlack = {
                        viewModel.changeColor(Color.Black)
                    }
                )
            }
        }

        FloatingActionButton(
            onClick = {
                isEditMode = false
                inputText = ""
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
                    Text("Enter Text")
                },

                text = {

                    OutlinedTextField(
                        value = inputText,
                        onValueChange = {
                            inputText = it
                        }
                    )
                },

                confirmButton = {

                    TextButton(
                        onClick = {
                            if (isEditMode) {

                                viewModel.editSelectedText(
                                    inputText
                                )

                            } else {

                                viewModel.addText(
                                    inputText
                                )
                            }
                            inputText = ""
                            showDialog = false
                        }
                    ) {
                        Text("Save")
                    }
                }
            )
        }
    }
}