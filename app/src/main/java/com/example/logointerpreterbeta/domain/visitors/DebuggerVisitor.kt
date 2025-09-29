package com.example.logointerpreterbeta.domain.visitors

import com.example.logointerpreterbeta.domain.drawing.DrawingDelegate
import com.example.logointerpreterbeta.domain.interpreter.antlrFIles.logoParser
import com.example.logointerpreterbeta.domain.interpreter.errors.StopException
import com.example.logointerpreterbeta.domain.models.DebuggerState
import java.util.concurrent.CountDownLatch

interface DebugStateListener {
    fun onStateUpdate(newState: DebuggerState)
}

class DebuggerVisitor(
    drawingDelegate: DrawingDelegate,
) : MyLogoVisitor(drawingDelegate, null) {

    private val listeners = mutableListOf<DebugStateListener>()

    fun addListener(listener: DebugStateListener) {
        listeners.add(listener)
    }

    fun removeListener(listener: DebugStateListener) {
        listeners.remove(listener)
    }

    private fun updateStateAndNotify(update: (DebuggerState) -> DebuggerState) {
        state = update(state)
        listeners.forEach { it.onStateUpdate(state) }
    }

    enum class DebuggerMode {
        Idle,
        Running,
        StepByStep,
        Finished,
        StepIn,
        StepOut
    }

    var state: DebuggerState = DebuggerState()
        private set

    public override val errors = mutableListOf<String>()
    private var debuggerMode: DebuggerMode = DebuggerMode.Idle
    private var stepCount = 0
    private var debugSignal = CountDownLatch(1)

    private fun resetDebuggerState() {
//        state = state.copy(currentLine = -1)
        updateStateAndNotify({ it.copy(currentLine = -1) })
        stepCount = 0
        debuggerMode = DebuggerMode.Idle
        debugSignal = CountDownLatch(0) // Reset any waiting signals
    }

    // Add this method to properly handle session start/stop
    fun startNewDebugSession() {
        stopDebuggingSession() // Clean up any previous session
        debuggerMode = DebuggerMode.Running
//        state = state.copy(isDebugging = true)
        updateStateAndNotify({ it.copy(isDebugging = true) })
    }

    fun stopDebuggingSession() {
        debuggerMode = DebuggerMode.Idle
        if (debugSignal.count > 0) {
            debugSignal.countDown()
        }
        resetDebuggerState()
//        state = state.copy(isDebugging = false)
        updateStateAndNotify({ it.copy(isDebugging = false) })

    }

    private fun pauseAtLine(line: Int) {
//        state = state.copy(currentLine = line)
        updateStateAndNotify({ it.copy(currentLine = line) })
        handleDebugPause(line)
    }

    // Kontynuowanie wykonania
    fun continueExecution() {
        debuggerMode = DebuggerMode.Running
    }

    fun nextStep() {
        debugSignal.countDown()
    }

    fun toggleBreakpoint(lineNumber: Int) {
        val newBreakpoints = state.breakpoints.toMutableList()
        if (newBreakpoints.contains(lineNumber)) {
            newBreakpoints.remove(lineNumber)
        } else {
            newBreakpoints.add(lineNumber)
        }
//        state = state.copy(breakpoints = newBreakpoints)
        updateStateAndNotify({ it.copy(breakpoints = newBreakpoints) })
    }

    fun handleDebugPause(line: Int) {
        if (debuggerMode == DebuggerMode.Running && line in state.breakpoints) {
            debuggerMode = DebuggerMode.StepByStep
        }

        // Aktywuj tryb krokowy, jeśli wybraliśmy "step in" i weszliśmy do procedury
        if (debuggerMode == DebuggerMode.StepIn) {
            debuggerMode = DebuggerMode.StepByStep
        }

        if (debuggerMode == DebuggerMode.StepByStep) {
//            state = state.copy(currentLine = line)
            updateStateAndNotify({ it.copy(currentLine = line) })

            debugSignal = CountDownLatch(1)
            debugSignal.await() // Wstrzymaj wykonanie
        }

        // Sprawdź, czy debugowanie nie zostało przerwane
        if (debuggerMode == DebuggerMode.Idle) {
            throw StopException("Debugowanie przerwane")
        }
    }


    fun stepIn() {
        debuggerMode = DebuggerMode.StepIn
        nextStep()
    }

    fun stepOut() {
//        state = state.copy(showStepOutButton = false)
        updateStateAndNotify({ it.copy(showStepOutButton = false) })
        debuggerMode = DebuggerMode.StepOut
        nextStep()
    }

    fun updateCurrentLine(line: Int) {
        //state = state.copy(currentLine = line)
        updateStateAndNotify({ it.copy(currentLine = line) })
    }

    fun clearBreakpoints() {
//        state = state.copy(breakpoints = mutableListOf())
        updateStateAndNotify({ it.copy(breakpoints = mutableListOf()) })
    }

    override fun visitRepeat_(ctx: logoParser.Repeat_Context?): Int {
        val repeatCount = ctx!!.number().text.toFloat().toInt()
        val commandsBlock = ctx.block().children
        try {
            for (i in 1..repeatCount) {
                for (command in commandsBlock.filterIsInstance<logoParser.CmdContext>()) {
                    pauseAtLine(command.start.line)
                    visit(command)
                    drawingDelegate.updateTurtleBitmap(turtleState)
                }
            }
        } catch (_: StopException) {
            return 0
        }
        return 0
    }

    override fun visitProcedureInvocation(ctx: logoParser.ProcedureInvocationContext?): Int {
        val procedureName = ctx!!.name().text
        if (procedures.containsKey(procedureName)) {
            val procedureCtx = procedures[procedureName]
            val previousVariables = HashMap(variables)

            if (debuggerMode == DebuggerMode.StepIn) {
                stepCount++
                debuggerMode = DebuggerMode.StepByStep
            }

            try {
                // Przypisz argumenty
                for (i in procedureCtx!!.parameterDeclarations().indices) {
                    val paramName = procedureCtx.parameterDeclarations()[i].name().text
                    val argumentValue = visit(ctx.expression()[i])
                    variables[paramName] = argumentValue
                }

                // Wykonaj ciało procedury
                for (line in procedureCtx.line()) {
                    pauseAtLine(line.start.line)
                    visit(line)
                    drawingDelegate.updateTurtleBitmap(turtleState)
                }
            } catch (_: StopException) {
                return 0
            } finally {
                variables = previousVariables
                if (debuggerMode == DebuggerMode.StepOut && stepCount > 0) {
                    stepCount--
                    if (stepCount == 0) {
                        debuggerMode = DebuggerMode.StepByStep
                    }
                }
            }
        } else {
            errors.add("Nieznana procedura: $procedureName w linii ${ctx.start.line}")
        }
        return 0
    }

    override fun visitProg(ctx: logoParser.ProgContext?): Int {
        startNewDebugSession()

        resetTurtleState()
        drawingDelegate.clearScreen( null)
        drawingDelegate.updateTurtleBitmap(turtleState)

        try {
            for (line in ctx!!.line()) {
                //state = state.copy(currentLine = line.start.line)
                updateStateAndNotify({ it.copy(currentLine = line.start.line) })

                // Tutaj upewniamy się, że to jest moment do zatrzymania
                handleDebugPause(line.start.line)

                //state = state.copy(showStepInButton = procedures.containsKey(line.start.text))
                updateStateAndNotify({ it.copy(showStepInButton = procedures.containsKey(line.start.text)) })
                visit(line)
                drawingDelegate.updateTurtleBitmap(turtleState)
            }
        } catch (_: Exception) {
            // Obsługa błędów
        } finally {
//            programFinished = true
            debuggerMode = DebuggerMode.Finished
            stopDebuggingSession()
        }
        return 0
    }
}