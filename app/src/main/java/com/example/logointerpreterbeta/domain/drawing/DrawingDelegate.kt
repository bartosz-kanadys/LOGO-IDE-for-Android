package com.example.logointerpreterbeta.domain.drawing

import com.example.logointerpreterbeta.domain.models.drawing.DrawingResult
import com.example.logointerpreterbeta.domain.models.drawing.PenState
import com.example.logointerpreterbeta.domain.models.drawing.TurtleState

interface DrawingDelegate {
    fun drawLine(startX: Float, startY: Float, endX: Float, endY: Float, pen: PenState)
    fun drawArc(centerX: Double, centerY: Double, radius: Float, startAngle: Float, sweepAngle: Float, pen: PenState)
    fun drawText(text: String, x: Float, y: Float, pen: PenState)
    fun clearScreen(isDarkMode: Boolean, color: Int?)
    fun getCanvasWidth(): Float
    fun getCanvasHeight(): Float
    fun updateTurtleBitmap(turtleState: TurtleState)
    fun getDrawing(): DrawingResult
    fun getArrowDrawing(): DrawingResult
}