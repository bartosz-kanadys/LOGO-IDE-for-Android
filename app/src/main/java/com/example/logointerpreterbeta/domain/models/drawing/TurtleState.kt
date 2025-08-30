package com.example.logointerpreterbeta.domain.models.drawing

data class TurtleState(
    val x: Float,
    val y: Float,
    val direction: Float = 0f, // 0 is up, 90 is right, etc.
    val isPenDown: Boolean = true,
    val isVisible: Boolean = true,
    val penState: PenState = PenState()
)