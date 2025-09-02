package com.example.logointerpreterbeta.ui.drawing

import android.graphics.Bitmap
import com.example.logointerpreterbeta.domain.drawing.DrawingDelegate
import com.example.logointerpreterbeta.ui.models.TurtleUI
import kotlinx.coroutines.flow.StateFlow

interface UIDrawingDelegate : DrawingDelegate {
    val bitmapFlow: StateFlow<Bitmap>
    val turtleBitmapFlow: StateFlow<Bitmap>
    val turtleUi: StateFlow<TurtleUI>
    fun updateTheme(isDarkMode: Boolean)
} 