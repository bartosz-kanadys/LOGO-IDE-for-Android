package com.example.logointerpreterbeta.domain.visitors

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.logointerpreterbeta.domain.drawing.DrawingDelegate
import com.example.logointerpreterbeta.domain.interpreter.errors.StopException
import com.example.logointerpreterbeta.domain.interpreter.antlrFIles.logoParser
import java.util.concurrent.CountDownLatch


class DebuggerVisitor(
    drawingDelegate: DrawingDelegate,
) : MyLogoVisitor(drawingDelegate,null) {
    companion object {
        var currentLine by mutableIntStateOf(-1)
        var breakpoints = mutableStateListOf<Int>()
        var showStepInButton by mutableStateOf(false)
        var showStepOutButton by mutableStateOf(false)
        fun toggleBreakpoint(lineNumber: Int) {
            Log.i("Breakpoint", "Toggled breakpoint at line $lineNumber")
            if (breakpoints.contains(lineNumber)) {
                breakpoints.remove(lineNumber)
            } else {
                breakpoints.add(lineNumber)
            }
        }
    }

    override val errors = mutableListOf<String>()
    private var stepByStepMode = false
    private var isDebugging = false
    private var stepCount = 0
    private var isSteppingIn = false
    private var isSteppingOut = false
    private var isResuming = false
    private var programFinished = false
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
        stepByStepMode = false // Wysyła sygnał do wątku debugera
    }

    fun nextStep() {
        debugSignal.countDown()
    }

    fun waitForDebugSignal() {
        stepCount++
        if (!isDebugging && !programFinished) {
            //currentLine = 0
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
                    drawingDelegate.updateTurtleBitmap(turtleState)
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
                    drawingDelegate.updateTurtleBitmap(turtleState)
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
        // Reset state for new debugging session
        resetDebuggerState()
        isDebugging = true
        programFinished = false

        resetTurtleState()
        drawingDelegate.clearScreen(isDarkMode, null)
        drawingDelegate.updateTurtleBitmap(turtleState)

        try {
            for (line in ctx!!.line()) {
                currentLine = line.start.line

                // Check if we should break at this line
                if (isDebugging && currentLine in breakpoints) {
                    stepByStepMode = true
                }

                showStepInButton = procedures.containsKey(line.start.text)

                // Wait for debug signal if in step mode
                if (isDebugging && stepByStepMode) {
                    debugSignal = CountDownLatch(1)
                    debugSignal.await() // Wait for signal

                    // Check if debugging was disabled while waiting
                    if (!isDebugging) return 0
                }

                visit(line)
                drawingDelegate.updateTurtleBitmap(turtleState)

                // Reset step mode after executing the line unless we're explicitly stepping
                if (!isSteppingIn && !isSteppingOut) {
                    stepByStepMode = false
                }
            }
        } catch (e: Exception) {
            Log.e("Debugger", "Error during debugging", e)
        } finally {
            programFinished = true
            // Don't automatically stop debugging here - let the UI control that
        }

        return 0
    }

    private fun resetDebuggerState() {
        currentLine = -1
        stepCount = 0
        isSteppingIn = false
        isSteppingOut = false
        isResuming = false
        stepByStepMode = false
        debugSignal = CountDownLatch(0) // Reset any waiting signals
    }

    // Add this method to properly handle session start/stop
    fun startNewDebugSession() {
        stopDebuggingSession() // Clean up any previous session
        resetDebuggerState()
        isDebugging = true
        programFinished = false
    }

    fun stopDebuggingSession() {
        isDebugging = false
        stepByStepMode = false
        // Count down any waiting latch to release threads
        if (debugSignal.count > 0) {
            debugSignal.countDown()
        }
    }

}
