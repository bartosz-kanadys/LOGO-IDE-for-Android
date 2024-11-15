package com.example.logointerpreterbeta

import android.app.UiModeManager
import android.content.Context
import android.util.Log
import androidx.compose.ui.graphics.toArgb
import com.example.logointerpreterbeta.errors.StopException
import com.example.logointerpreterbeta.errors.SyntaxError
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

    private fun waitForDebugSignal(line: String) {
        Log.i("Czekam przed:",line)
        if (isDebugging) {
            debugSignal.await() // Wstrzymaj wykonanie do momentu otrzymania sygnału
            debugSignal = CountDownLatch(1) // Przygotuj do następnego kroku
        }
    }
    override fun visitRepeat_(ctx: logoParser.Repeat_Context?): Int {
        val repeatCount = ctx!!.number().text.toFloat().toInt()
        val commandsBlock = ctx.block().children
        try {
            for (i in 1..repeatCount) {
                for (command in commandsBlock) {
                    waitForDebugSignal(command.text) // Oczekiwanie na sygnał przed kolejnym krokiem
                    visit(command)
                    updateTurtleBitmap()

                }
            }
        } catch (e: StopException) {
            return 0
        }

        return 0
    }
    override fun visitProcedureInvocation(ctx: logoParser.ProcedureInvocationContext?): Int {
        val procedureName = ctx!!.name().text

        // Sprawdz czy procedura istnieje
        if (procedures.containsKey(procedureName)) {
            val procedureCtx = procedures[procedureName]

            // Obsluga parametrow procedury
            val parameterDeclarations = procedureCtx!!.parameterDeclarations()
            val arguments = ctx.expression()

            if (parameterDeclarations.size != arguments.size) {
                throw RuntimeException("Liczba argumentów nie pasuje do liczby parametrów.")
            }

            // Przypisz argumenty do zmiennych lokalnych
            val previousVariables = HashMap(variables) // Zapisz poprzednie zmienne
            for (i in parameterDeclarations.indices) {
                val paramName = parameterDeclarations[i].name().text
                val argumentValue = visit(arguments[i])
                variables[paramName] = argumentValue
            }

            try {
                // Wykonaj każdą linię ciała procedury
                for (line in procedureCtx.line()) {
                    waitForDebugSignal(line.text) // Oczekiwanie na sygnał przed kolejnym krokiem
                    visit(line)
                    updateTurtleBitmap()
                }
            } catch (e: StopException) {
                return 0
            } finally {
                variables = previousVariables //Przywróć poprzednie zmienne po zakończeniu procedury
            }
        } else {
            SyntaxError.addError("Nieznana procedura: $procedureName")
        }

        return 0
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
        updateTurtleBitmap()
        for (line in ctx!!.line()) {
            waitForDebugSignal(line.text) // Oczekiwanie na sygnał przed kolejnym krokiem
            visit(line)
            updateTurtleBitmap()
        }
        return 0
    }
}