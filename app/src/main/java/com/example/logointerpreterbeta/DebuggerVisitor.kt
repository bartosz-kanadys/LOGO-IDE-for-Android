package com.example.logointerpreterbeta

import android.app.UiModeManager
import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.toArgb
import com.example.logointerpreterbeta.errors.StopException
import com.example.logointerpreterbeta.errors.SyntaxError
import com.example.logointerpreterbeta.interpreter.logoParser
import com.example.logointerpreterbeta.ui.theme.onSurfaceDarkMediumContrast
import com.example.logointerpreterbeta.ui.theme.onSurfaceLightMediumContrast
import com.example.logointerpreterbeta.ui.theme.surfaceDarkMediumContrast
import com.example.logointerpreterbeta.ui.theme.surfaceLightMediumContrast
import com.example.logointerpreterbeta.viewModels.InterpreterViewModel
import com.example.logointerpreterbeta.viewModels.InterpreterViewModel.Companion.errors
import com.example.logointerpreterbeta.viewModels.InterpreterViewModel.Companion.isErrorListVisable
import org.antlr.v4.runtime.tree.TerminalNodeImpl
import java.util.concurrent.CountDownLatch

class DebuggerVisitor(private val context: Context): MyLogoVisitor(context) {
    companion object{
        var currentLine by mutableStateOf(-1)
        var breakpoints = mutableStateListOf<Int>()
    }
    private var stepByStepMode = false
    private var isDebugging = false
    private var debugSignal = CountDownLatch(1)
    private var stepCount = 0
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
        stepByStepMode=false // Wysyła sygnał do wątku debugera
    }
    fun nextStep(){
        debugSignal.countDown()
    }
    private fun waitForDebugSignal() {
        stepCount++
        if(!isDebugging){
            currentLine=0
            stepByStepMode =false
        }
        if(currentLine in breakpoints && isDebugging) {
            stepByStepMode = true
        }
        if (stepByStepMode) {
            debugSignal.await() // Wstrzymaj wykonanie do momentu otrzymania sygnału
            debugSignal = CountDownLatch(1) // Przygotuj do następnego kroku
        }
    }
    override fun visitRepeat_(ctx: logoParser.Repeat_Context?): Int {
        val repeatCount = ctx!!.number().text.toFloat().toInt()
        val commandsBlock = ctx.block().children
        try {
            for (i in 1..repeatCount) {
                for (command in commandsBlock.filterIsInstance<logoParser.CmdContext>()) {
                    currentLine=command.start.line
                    Log.i("Czekam w petli:", command.text + " krok: $stepCount")
                    waitForDebugSignal() // Oczekiwanie na sygnał przed kolejnym krokiem
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
                        currentLine = line.start.line
                        Log.i("Czekam w procedurze:", line.text + " krok: $stepCount")
                        waitForDebugSignal() // Oczekiwanie na sygnał przed kolejnym krokiem
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
            InterpreterViewModel.errors.value=SyntaxError.errors.value;
            InterpreterViewModel.isErrorListVisable = !errors.value.isEmpty()
        }

        return 0
    }
    override fun visitProg(ctx: logoParser.ProgContext?): Int {
        stepCount=0;
        currentLine=0;
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
            currentLine=line.start.line
            Log.i("Czekam przed poleceniem:",line.text + " krok: $stepCount")
            waitForDebugSignal() // Oczekiwanie na sygnał przed kolejnym krokiem
            visit(line)
            updateTurtleBitmap()
        }
        currentLine=0
        stepByStepMode =false
        return 0
    }
}
