package com.example.logointerpreterbeta.domain.models.drawing

data class TurtleState(
    var xPosition: Float,
    var yPosition: Float,
    var direction: Float = 0f, // 0 is up, 90 is right, etc.
    var isPenDown: Boolean = true,
    var isVisible: Boolean = true,
    var penState: PenState = PenState()
)