package com.example.logointerpreterbeta

import android.app.UiModeManager
import android.content.Context
import androidx.compose.ui.graphics.toArgb
import com.example.logointerpreterbeta.interpreter.logoParser
import com.example.logointerpreterbeta.ui.theme.onSurfaceDarkMediumContrast
import com.example.logointerpreterbeta.ui.theme.onSurfaceLightMediumContrast
import com.example.logointerpreterbeta.ui.theme.surfaceDarkMediumContrast
import com.example.logointerpreterbeta.ui.theme.surfaceLightMediumContrast
import java.util.concurrent.CountDownLatch

class DebuggerVisitor(private val context: Context): MyLogoVisitor(context) {
    private var isDebugging = false
    private var debugSignal = CountDownLatch(1)

    // Włączenie trybu debugowania
    fun enableDebugging() {
        isDebugging = true
    }

    // Wyłączenie trybu debugowania
    fun disableDebugging() {
        isDebugging = false
        debugSignal.countDown() // Upewnij się, że debuger nie czeka
    }

    // Kontynuowanie wykonania
    fun continueExecution() {
        debugSignal.countDown() // Wysyła sygnał do wątku debugera
    }

    private fun waitForDebugSignal() {
        if (isDebugging) {
            debugSignal.await() // Wstrzymaj wykonanie do momentu otrzymania sygnału
            debugSignal = CountDownLatch(1) // Przygotuj do następnego kroku
        }
    }
    override fun visitProg(ctx: logoParser.ProgContext?): Int {
        paint.setColor(Turtle.penColor)
        val isDarkTheme = uiModeManager.nightMode == UiModeManager.MODE_NIGHT_YES
        if (isDarkTheme) {
            Turtle.penColor = onSurfaceDarkMediumContrast.toArgb()
            canvas.drawColor(surfaceDarkMediumContrast.toArgb()) //czyszczenie obrazka przed startem programu
        } else {
            Turtle.penColor = onSurfaceLightMediumContrast.toArgb()
            canvas.drawColor(surfaceLightMediumContrast.toArgb())
        }
        for (line in ctx!!.line()) {
            waitForDebugSignal() // Oczekiwanie na sygnał przed kolejnym krokiem
            visit(line)
        }
        updateTurtleBitmap()
        return 0
    }
}