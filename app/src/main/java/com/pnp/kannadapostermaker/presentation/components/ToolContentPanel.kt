package com.pnp.kannadapostermaker.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.pnp.kannadapostermaker.presentation.model.PosterFont

@Composable
fun ToolContentPanel(
    selectedCategory: ToolCategory,

    onAddText: () -> Unit,
    onEditText: () -> Unit,
    onDeleteText: () -> Unit,

    onIncreaseFont: () -> Unit,
    onDecreaseFont: () -> Unit,

    onColorSelected: (Color) -> Unit,

    onDeleteLayer: () -> Unit,
    onFontSelected: (PosterFont) -> Unit,
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

            FontPanel(
                onFontSelected = onFontSelected
            )
        }

        ToolCategory.LAYER -> {

            LayerPanel(
                onDeleteLayer = onDeleteLayer
            )
        }
    }
}