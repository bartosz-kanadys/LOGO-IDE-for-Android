package com.example.logointerpreterbeta.ui.models

import com.example.logointerpreterbeta.domain.models.drawing.PenState

data class TurtleUI(
    val xPosition: Float,
    val yPosition: Float,
    val direction: Float,
    val isVisible: Boolean,
    val penState: PenState,
    val isPenDown: Boolean
)
