package com.example.logointerpreterbeta.ui.models

import com.example.logointerpreterbeta.domain.models.drawing.PenState

data class TurtleUI(
    val xPosition: Float = 0f,
    val yPosition: Float = 0f,
    val direction: Float = 90f,
    val isVisible: Boolean = true,
    val penState: PenState = PenState(),
    val isPenDown: Boolean = true
)
