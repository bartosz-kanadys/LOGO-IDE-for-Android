package com.example.logointerpreterbeta.ui.drawing

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.RectF
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.toArgb
import androidx.core.content.ContextCompat
import com.example.logointerpreterbeta.domain.drawing.DrawingDelegate
import com.example.logointerpreterbeta.domain.models.drawing.DrawingResult
import com.example.logointerpreterbeta.domain.models.drawing.PenState
import com.example.logointerpreterbeta.domain.models.drawing.TurtleState
import com.example.logointerpreterbeta.domain.repository.ConfigRepository
import com.example.logointerpreterbeta.ui.theme.surfaceDarkMediumContrast
import com.example.logointerpreterbeta.ui.theme.surfaceLightMediumContrast
import androidx.core.graphics.createBitmap
import com.example.logointerpreterbeta.R

class AndroidDrawingDelegate(
    private val bitmap: Bitmap,
    private val context: Context
): DrawingDelegate {
    private var turtleBitmap: Bitmap = createBitmap(bitmap.width, bitmap.height)
    private val canvas: Canvas = Canvas(bitmap)
    private val turtleCanvas: Canvas = Canvas(turtleBitmap)
    private val paint: Paint = Paint().apply {
        style = Paint.Style.STROKE
        isAntiAlias = true
    }

    override fun drawLine(
        startX: Float,
        startY: Float,
        endX: Float,
        endY: Float,
        pen: PenState
    ) {
        applyPenState(pen)
        canvas.drawLine(startX, startY, endX, endY, paint)
    }

    override fun drawArc(
        centerX: Double,
        centerY: Double,
        radius: Float,
        startAngle: Float,
        sweepAngle: Float,
        pen: PenState
    ) {
        applyPenState(pen)
        val rectF = RectF(
            (centerX - radius).toFloat(),
            (centerY - radius).toFloat(),
            (centerX + radius).toFloat(),
            (centerY + radius).toFloat()
        )
        // Set style to STROKE for open arc, FILL for pie slice
        paint.style = Paint.Style.STROKE
        canvas.drawArc(rectF, startAngle, sweepAngle, false, paint)
    }

    override fun drawText(
        text: String,
        x: Float,
        y: Float,
        pen: PenState
    ) {
        applyPenState(pen)
        paint.style = Paint.Style.FILL // Text is typically filled
        canvas.drawText(text, x, y, paint)
    }

    override fun clearScreen(isDarkMode: Boolean, color: Int?) {
        if (color == null) {
            if (isDarkMode) {
                canvas.drawColor(surfaceDarkMediumContrast.toArgb())
            } else {
                canvas.drawColor(surfaceLightMediumContrast.toArgb())
            }
        } else {
            canvas.drawColor(color)
        }
    }

    override fun getCanvasWidth(): Float {
        return canvas.width.toFloat()
    }

    override fun getCanvasHeight(): Float {
        return canvas.height.toFloat()
    }

    override fun getDrawing(): DrawingResult {
        val pixels = IntArray(bitmap.width * bitmap.height)
        bitmap.getPixels(pixels, 0, bitmap.width, 0, 0, bitmap.width, bitmap.height)
        return DrawingResult(bitmap.width, bitmap.height, pixels)
    }

    override fun getArrowDrawing(): DrawingResult {
        val pixels = IntArray(turtleBitmap.width * turtleBitmap.height)
        turtleBitmap.getPixels(pixels, 0, turtleBitmap.width, 0, 0, turtleBitmap.width, turtleBitmap.height)
        return DrawingResult(turtleBitmap.width, turtleBitmap.height, pixels)
    }

    override fun updateTurtleBitmap(turtleState: TurtleState) {
        this.turtleBitmap.eraseColor(Color.TRANSPARENT)
        if (turtleState.isVisible) {
            // Pobieranie bitmapy żółwia i rotacja
            val arrow = getBitmapFromImage(context, R.drawable.turtle_simple_green)


            val matrix = Matrix()
            matrix.postRotate(turtleState.direction, arrow.width / 2f, arrow.height / 2f)
            val turtleBitmap = Bitmap.createBitmap(
                arrow,
                0,
                0,
                arrow.width,
                arrow.height,
                matrix,
                true)
            turtleCanvas.drawBitmap(
                turtleBitmap,
                turtleState.x - turtleBitmap.width / 2,
                turtleState.y - turtleBitmap.height / 2,
                paint
            )

        }
    }

    private fun applyPenState(pen: PenState) {
        paint.color = pen.color
        paint.strokeWidth = pen.size
        paint.textSize = pen.textSize
    }

    private fun getBitmapFromImage(context: Context, drawable: Int): Bitmap {
        val drawable = ContextCompat.getDrawable(context, drawable)
        val bitmap = createBitmap(
            drawable!!.intrinsicWidth / 2, drawable.intrinsicHeight / 2, Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)

        return bitmap
    }

}

fun DrawingResult.toBitmap(): Bitmap {
    return Bitmap.createBitmap(pixels, width, height, Bitmap.Config.ARGB_8888)
}