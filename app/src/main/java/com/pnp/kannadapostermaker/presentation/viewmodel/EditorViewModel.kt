package com.pnp.kannadapostermaker.presentation.viewmodel

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import com.pnp.kannadapostermaker.presentation.TextLayer

class EditorViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(
        EditorUiState(
            textLayers = listOf(
                TextLayer(
                    id = "1",
                    text = "ನಿಮ್ಮ ಪಠ್ಯ"
                )
            )
        )
    )

    val uiState: StateFlow<EditorUiState> = _uiState

    fun addText(text: String) {

        val newId = System.currentTimeMillis().toString()

        _uiState.update {

            it.copy(

                selectedLayerId = null,

                textLayers = it.textLayers + TextLayer(
                    id = newId,
                    text = text,
                    offsetX = 0f,
                    offsetY = 0f,
                    isSelected = true
                )
            )
        }
    }

    fun selectLayer(layerId: String) {

        _uiState.update { state ->

            state.copy(
                selectedLayerId = layerId,
                textLayers = state.textLayers.map {

                    it.copy(
                        isSelected = it.id == layerId
                    )
                }
            )
        }
    }

    fun updatePosition(
        layerId: String,
        x: Float,
        y: Float
    ) {

        _uiState.update { state ->

            state.copy(
                textLayers = state.textLayers.map {

                    if (it.id == layerId) {
                        it.copy(
                            offsetX = x,
                            offsetY = y
                        )
                    } else {
                        it
                    }
                }
            )
        }
    }

    fun deleteSelected() {

        val selectedId =
            _uiState.value.selectedLayerId ?: return

        _uiState.update {

            it.copy(
                selectedLayerId = null,
                textLayers = it.textLayers.filterNot { layer ->
                    layer.id == selectedId
                }
            )
        }
    }

    fun increaseFont() {

        modifySelected {
            copy(fontSize = fontSize + 2)
        }
    }

    fun decreaseFont() {

        modifySelected {
            copy(fontSize = (fontSize - 2).coerceAtLeast(12f))
        }
    }

    fun changeColor(color: Color) {

        modifySelected {
            copy(color = color)
        }
    }

    fun editSelectedText(newText: String) {

        modifySelected {
            copy(text = newText)
        }
    }

    private fun modifySelected(
        block: TextLayer.() -> TextLayer
    ) {

        val selectedId =
            _uiState.value.selectedLayerId ?: return

        _uiState.update { state ->

            state.copy(
                textLayers = state.textLayers.map {

                    if (it.id == selectedId) {
                        it.block()
                    } else {
                        it
                    }
                }
            )
        }
    }
}