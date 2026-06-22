package com.pnp.kannadapostermaker.presentation

import androidx.compose.ui.graphics.Color
import com.pnp.kannadapostermaker.presentation.model.PosterFont

data class TextLayer(
    val id: String,
    val text: String,
    val offsetX: Float = 0f,
    val offsetY: Float = 0f,
    val fontSize: Float = 24f,
    val color: Color = Color.White,
    val isSelected: Boolean = false,
    val fontName: String = "NOTO",
    val font: PosterFont = PosterFont.NOTO
)