package com.example.logointerpreterbeta.ui.drawing

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.RectF
import androidx.compose.ui.graphics.toArgb
import androidx.core.content.ContextCompat
import androidx.core.graphics.createBitmap
import com.example.logointerpreterbeta.R
import com.example.logointerpreterbeta.domain.models.drawing.PenState
import com.example.logointerpreterbeta.domain.models.drawing.TurtleState
import com.example.logointerpreterbeta.ui.models.TurtleUI
import com.example.logointerpreterbeta.ui.theme.surfaceDarkMediumContrast
import com.example.logointerpreterbeta.ui.theme.surfaceLightMediumContrast
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AndroidDrawingDelegate(
    width: Int,
    height: Int,
    private val context: Context,
): UIDrawingDelegate {
    override val turtleUi: MutableStateFlow<TurtleUI> = MutableStateFlow(TurtleUI(0f, 0f, 90f, true, PenState(), true))

    private val mainBitmap = createBitmap(width, height, Bitmap.Config.ARGB_8888)
    private val turtleBitmap = createBitmap(width, height, Bitmap.Config.ARGB_8888)

    private val _bitmapFlow = MutableStateFlow(mainBitmap)
    override val bitmapFlow: StateFlow<Bitmap> = _bitmapFlow.asStateFlow()

    private val _turtleBitmapFlow = MutableStateFlow(turtleBitmap)
    override val turtleBitmapFlow: StateFlow<Bitmap> = _turtleBitmapFlow.asStateFlow()

    private val canvas: Canvas = Canvas(mainBitmap)
    private val turtleCanvas: Canvas = Canvas(turtleBitmap)
    private val paint: Paint = Paint().apply {
        style = Paint.Style.STROKE
        isAntiAlias = true
    }

    private fun notifyBitmapChanged() {
        _bitmapFlow.value = mainBitmap.copy(mainBitmap.config!!, true)
        _turtleBitmapFlow.value = turtleBitmap.copy(turtleBitmap.config!!, true)
    }

    fun getRelativeXPosition(x: Float): Float {
        return x - (getCanvasWidth() / 2 )
    }

    fun getRelativeYPosition(y: Float): Float {
        return (y - (getCanvasHeight() / 2)) * -1
    }

    override fun setPenState(penState: PenState) {
        turtleUi.update {
            it.copy(penState = penState)
        }
    }

    override fun hideTurtle(isVisible: Boolean) {
        turtleUi.update { it.copy(isVisible = isVisible) }
    }

    override fun penDown(isPenDown: Boolean) {
        turtleUi.update {
            it.copy(
                isPenDown = isPenDown
            )
        }
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
        notifyBitmapChanged()
        turtleUi.update {
            it.copy(
                xPosition = getRelativeXPosition(endX),
                yPosition = getRelativeYPosition(endY),
                penState = pen
            )
        }
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
        notifyBitmapChanged()
        turtleUi.update {
            it.copy(
                xPosition = getRelativeXPosition(centerX.toFloat()),
                yPosition = getRelativeYPosition(centerY.toFloat()),
                penState = pen
            )
        }
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
        notifyBitmapChanged()
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
        notifyBitmapChanged()
        turtleUi.update {
            it.copy(
                xPosition = 0f,
                yPosition = 0f,
                direction = 90f,
                isVisible = true,
                penState = PenState(),
                isPenDown = true
            )
        }
    }

    override fun getCanvasWidth(): Float {
        return canvas.width.toFloat()
    }

    override fun getCanvasHeight(): Float {
        return canvas.height.toFloat()
    }

    override fun updateTurtleBitmap(turtleState: TurtleState) {
        turtleCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR)
        notifyBitmapChanged()
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
                turtleState.xPosition - turtleBitmap.width / 2,
                turtleState.yPosition - turtleBitmap.height / 2,
                paint
            )

        }
        turtleUi.update {
            it.copy(
                direction = turtleState.direction,
            )
        }
        notifyBitmapChanged()
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