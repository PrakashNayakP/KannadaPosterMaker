package com.pnp.kannadapostermaker.presentation.viewmodel

import com.pnp.kannadapostermaker.presentation.TextLayer

data class EditorUiState(
    val textLayers: List<TextLayer> = emptyList(),
    val selectedLayerId: String? = null
)