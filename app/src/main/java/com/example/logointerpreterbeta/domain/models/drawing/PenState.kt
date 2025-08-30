package com.example.logointerpreterbeta.domain.models.drawing

const val DEFAULT_PEN_COLOR = 0xFF000000.toInt() // Black
const val DEFAULT_BACKGROUND_COLOR = 0xFFFFFF // White

data class PenState(
    val color: Int = DEFAULT_PEN_COLOR,
    val size: Float = 5f,
    val textSize: Float = 50f
)