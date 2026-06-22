package com.pnp.kannadapostermaker.presentation.viewmodel

import com.pnp.kannadapostermaker.presentation.TextLayer
import com.pnp.kannadapostermaker.presentation.model.ImageBounds

data class EditorUiState(
    val textLayers: List<TextLayer> = emptyList(),
    val selectedLayerId: String? = null,
    val imageBounds: ImageBounds = ImageBounds()
)