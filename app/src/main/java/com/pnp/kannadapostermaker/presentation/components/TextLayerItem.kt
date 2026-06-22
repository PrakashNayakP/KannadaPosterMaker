package com.pnp.kannadapostermaker.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pnp.kannadapostermaker.presentation.TextLayer
import com.pnp.kannadapostermaker.presentation.model.PosterFont
import com.pnp.kannadapostermaker.presentation.utils.FontUtils
import kotlin.math.roundToInt

@Composable
fun BoxScope.TextLayerItem(
    layer: TextLayer,
    onSelected: () -> Unit,
    onPositionChanged: (Float, Float) -> Unit
) {

    var offsetX by remember(layer.id) {
        mutableFloatStateOf(layer.offsetX)
    }

    var offsetY by remember(layer.id) {
        mutableFloatStateOf(layer.offsetY)
    }

    val fontFamily = when(layer.font) {

        PosterFont.NOTO ->
            FontUtils.notoKannada

        PosterFont.NOTO_SERIF ->
            FontUtils.notoSerifKannada

        PosterFont.BALOO ->
            FontUtils.balooTamma
    }

    Text(
        text = layer.text,
        color = layer.color,
        fontSize = layer.fontSize.sp,
        fontFamily = fontFamily,
        fontWeight = FontWeight.Bold,

        modifier = Modifier
            .align(Alignment.Center)

            .offset {
                IntOffset(
                    offsetX.roundToInt(),
                    offsetY.roundToInt()
                )
            }

            .border(
                width = if (layer.isSelected) 2.dp else 0.dp,
                color = Color.Blue,
                shape = RoundedCornerShape(4.dp)
            )

            .clickable {
                onSelected()
            }

            .pointerInput(layer.id) {

                detectDragGestures(

                    onDragStart = {
                        onSelected()
                    }

                ) { _, dragAmount ->

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