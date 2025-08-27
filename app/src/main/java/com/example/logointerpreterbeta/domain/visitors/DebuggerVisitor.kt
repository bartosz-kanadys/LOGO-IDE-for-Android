package com.example.logointerpreterbeta.domain.visitors

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.toArgb
import com.example.logointerpreterbeta.ui.models.TurtleUI
import com.example.logointerpreterbeta.domain.errors.StopException
import com.example.logointerpreterbeta.domain.interpreter.antlrFIles.logoParser
import com.example.logointerpreterbeta.ui.theme.onSurfaceDarkMediumContrast
import com.example.logointerpreterbeta.ui.theme.onSurfaceLightMediumContrast
import com.example.logointerpreterbeta.ui.theme.surfaceDarkMediumContrast
import com.example.logointerpreterbeta.ui.theme.surfaceLightMediumContrast
import com.example.logointerpreterbeta.ui.screens.settings.SettingsViewModel
import java.util.concurrent.CountDownLatch

class DebuggerVisitor(context: Context) : MyLogoVisitor(context) {
    companion object {
        var currentLine by mutableIntStateOf(-1)
        var breakpoints = mutableStateListOf<Int>()
        var showStepInButton by mutableStateOf(false)
        var showStepOutButton by mutableStateOf(false)
        fun toggleBreakpoint(lineNumber: Int) {
            Log.i("Breakpoint", "Toggled breakpoint at line $lineNumber")
            if (DebuggerVisitor.breakpoints.contains(lineNumber)) {
                DebuggerVisitor.breakpoints.remove(lineNumber)
            } else {
                DebuggerVisitor.breakpoints.add(lineNumber)
            }
        }
    }
    override val errors = mutableListOf<String>()
    private var stepByStepMode = false
    private var isDebugging = false
    private var debugSignal = CountDownLatch(1)
    private var stepCount = 0
    private var isSteppingIn = false
    private var isSteppingOut = false

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
        stepByStepMode = false // Wysyła sygnał do wątku debugera
    }

    fun nextStep() {
        debugSignal.countDown()
    }

    private fun waitForDebugSignal() {
        stepCount++
        if (!isDebugging) {
            currentLine = 0
            stepByStepMode = false
        }
        if (currentLine in breakpoints && isDebugging) {
            stepByStepMode = true
        }
        if (stepByStepMode) {
            debugSignal = CountDownLatch(1)
            debugSignal.await() // Wstrzymaj wykonanie do momentu otrzymania sygnału
        }
    }

    fun stepIn() {
        isSteppingIn = true
        nextStep()
    }

    fun stepOut() {
        showStepOutButton = false
        isSteppingOut = true
        isSteppingIn = false
        stepByStepMode = false
        nextStep()
    }

    override fun visitRepeat_(ctx: logoParser.Repeat_Context?): Int {
        val repeatCount = ctx!!.number().text.toFloat().toInt()
        val commandsBlock = ctx.block().children
        try {
            for (i in 1..repeatCount) {
                for (command in commandsBlock.filterIsInstance<logoParser.CmdContext>()) {
                    currentLine = command.start.line
                    Log.i("Czekam w petli:", command.text + " krok: $stepCount")
                    waitForDebugSignal() // Oczekiwanie na sygnał przed kolejnym krokiem
                    if(!isDebugging) return 0
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
            var steppedIn = isSteppingIn
            val temp = stepByStepMode
            if (!steppedIn) {
                stepByStepMode = false
            }
            showStepOutButton = true
            showStepInButton = false
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
            if (breakpoints.any { it in procedureCtx.start.line..procedureCtx.stop.line } && isDebugging) steppedIn =
                true
            try {
                // Wykonaj każdą linię ciała procedury
                for (line in procedureCtx.line()) {
                    currentLine = line.start.line
                    if (steppedIn) {
                        Log.i("Czekam w procedurze:", line.text + " krok: $stepCount")
                        waitForDebugSignal() // Oczekiwanie na sygnał przed kolejnym krokiem
                        if(!isDebugging) return 0
                    }
                    visit(line)
                    updateTurtleBitmap()
                }
            } catch (e: StopException) {
                return 0
            } finally {
                variables = previousVariables //Przywróć poprzednie zmienne po zakończeniu procedury
            }
            showStepOutButton = false
            stepByStepMode = temp
            if (steppedIn && isSteppingOut) {
                stepByStepMode = true
            }
        } else {
            errors.add("Nieznana procedura: $procedureName w linii ${ctx.start.line}")
        }
        return 0
    }

    override fun visitProg(ctx: logoParser.ProgContext?): Int {
        stepCount = 0
        currentLine = 0
        paint.setColor(TurtleUI.penColor)
        if (SettingsViewModel.darkMode) {
            TurtleUI.penColor = onSurfaceDarkMediumContrast.toArgb()
            canvas.drawColor(surfaceDarkMediumContrast.toArgb()) //czyszczenie obrazka przed startem programu
        } else {
            TurtleUI.penColor = onSurfaceLightMediumContrast.toArgb()
            canvas.drawColor(surfaceLightMediumContrast.toArgb())
        }
        updateTurtleBitmap()
        for (line in ctx!!.line()) {
            currentLine = line.start.line
            //Log.i("Czekam przed poleceniem:", line.text + " krok: $stepCount")
            showStepInButton = procedures.containsKey(line.start.text)
            waitForDebugSignal() // Oczekiwanie na sygnał przed kolejnym krokiem
            if(!isDebugging) return 0
            visit(line)
            updateTurtleBitmap()
        }
        currentLine = 0
        stepByStepMode = false
        return 0
    }
}
