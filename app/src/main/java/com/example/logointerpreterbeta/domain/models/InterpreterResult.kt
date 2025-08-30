package com.example.logointerpreterbeta.domain.models

import com.example.logointerpreterbeta.domain.models.drawing.DrawingResult

class InterpreterResult(
    val success: Boolean,
    val errors: List<String>,
    val image: DrawingResult?,
    val arrowImage: DrawingResult?
)
