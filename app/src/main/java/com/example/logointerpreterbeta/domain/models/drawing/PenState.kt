package com.example.logointerpreterbeta.domain.models.drawing

const val DEFAULT_PEN_COLOR = 0xFF000000.toInt() // Black
const val DEFAULT_BACKGROUND_COLOR = 0xFFFFFF // White

data class PenState(
    var color: Int = DEFAULT_PEN_COLOR,
    var size: Float = 5f,
    var textSize: Float = 50f
)