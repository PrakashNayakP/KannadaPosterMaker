package com.pnp.kannadapostermaker.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun ToolContentPanel(
    selectedCategory: ToolCategory,

    onAddText: () -> Unit,
    onEditText: () -> Unit,
    onDeleteText: () -> Unit,

    onIncreaseFont: () -> Unit,
    onDecreaseFont: () -> Unit,

    onColorSelected: (Color) -> Unit,

    onDeleteLayer: () -> Unit
) {

    when (selectedCategory) {

        ToolCategory.TEXT -> {

            TextActionsPanel(
                onAddText = onAddText,
                onEditText = onEditText,
                onDeleteText = onDeleteText,
                onIncreaseFont = onIncreaseFont,
                onDecreaseFont = onDecreaseFont
            )
        }

        ToolCategory.COLOR -> {

            ColorPalettePanel(
                onColorSelected = onColorSelected
            )
        }

        ToolCategory.FONT -> {

            FontPanel()
        }

        ToolCategory.LAYER -> {

            LayerPanel(
                onDeleteLayer = onDeleteLayer
            )
        }
    }
}