package com.pnp.kannadapostermaker.presentation

import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
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
import com.pnp.kannadapostermaker.presentation.components.BannerPlaceholder
import com.pnp.kannadapostermaker.presentation.components.EditorToolbar
import com.pnp.kannadapostermaker.presentation.components.EditorTopBar
import com.pnp.kannadapostermaker.presentation.components.TextLayerItem
import com.pnp.kannadapostermaker.presentation.components.ToolCategory
import com.pnp.kannadapostermaker.presentation.components.ToolCategoryBar
import com.pnp.kannadapostermaker.presentation.viewmodel.EditorViewModel
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import com.pnp.kannadapostermaker.presentation.components.EditorContent
import com.pnp.kannadapostermaker.presentation.components.ToolContentPanel

@Composable
fun EditorScreen(
    imageUri: String,
    viewModel: EditorViewModel = viewModel()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    var selectedCategory by remember {
        mutableStateOf(
            ToolCategory.TEXT
        )
    }

    var isEditMode by remember {
        mutableStateOf(false)
    }

    var showDialog by remember {
        mutableStateOf(false)
    }

    var inputText by remember {
        mutableStateOf("")
    }

    Scaffold(
        topBar = {
            EditorTopBar(
                onExportClick = {
                    // next milestone
                }
            )
        }
    ) { paddingValues ->
        Column(modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()) {

            EditorContent(
                imageUri = imageUri,
                textLayers = uiState.textLayers,

                onLayerSelected = {
                    viewModel.selectLayer(it)
                },

                onLayerMoved = { id, x, y ->
                    viewModel.updatePosition(
                        id,
                        x,
                        y
                    )
                },
                onImageBoundsChanged = { width, height ->

                    viewModel.updateImageBounds(
                        width,
                        height
                    )
                },
                modifier = Modifier.weight(1f)
            )

            ToolCategoryBar(
                selected = selectedCategory,
                onCategorySelected = {
                    selectedCategory = it
                }
            )
            ToolContentPanel(

                selectedCategory = selectedCategory,

                onAddText = {

                    isEditMode = false
                    inputText = ""
                    showDialog = true
                },

                onEditText = {

                    if (uiState.selectedLayerId != null) {

                        isEditMode = true

                        inputText =
                            uiState.textLayers
                                .firstOrNull {
                                    it.id == uiState.selectedLayerId
                                }
                                ?.text.orEmpty()

                        showDialog = true
                    }
                },

                onDeleteText = {
                    viewModel.deleteSelected()
                },

                onIncreaseFont = {
                    viewModel.increaseFont()
                },

                onDecreaseFont = {
                    viewModel.decreaseFont()
                },

                onColorSelected = {
                    viewModel.changeColor(it)
                },

                onDeleteLayer = {
                    viewModel.deleteSelected()
                },
                onFontSelected = {
                    viewModel.changeFont(it)
                },
            )
            BannerPlaceholder()
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