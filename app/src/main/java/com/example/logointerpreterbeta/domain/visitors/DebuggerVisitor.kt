package com.example.logointerpreterbeta.domain.visitors

import com.example.logointerpreterbeta.domain.drawing.DrawingDelegate
import com.example.logointerpreterbeta.domain.interpreter.antlrFIles.logoParser
import com.example.logointerpreterbeta.domain.interpreter.errors.StopException
import com.example.logointerpreterbeta.domain.models.DebuggerState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.concurrent.CountDownLatch

class DebuggerVisitor(
    drawingDelegate: DrawingDelegate,
) : MyLogoVisitor(drawingDelegate, null) {

    private val _debuggerState = MutableStateFlow(DebuggerState())
    val debuggerState: StateFlow<DebuggerState> = _debuggerState.asStateFlow()

    private fun updateState(update: (DebuggerState) -> DebuggerState) {
        _debuggerState.update(update)
    }

    enum class DebuggerMode {
        Idle,
        Running,
        StepByStep,
        Finished,
        StepIn,
        StepOut,
        StepOverLoop,
        StepOutIteration
    }

    public override val errors = mutableListOf<String>()
    private var debuggerMode: DebuggerMode = DebuggerMode.Idle

    private var procedureDepth = 0
    private var loopDepth = 0
    private var stepOutTargetProcedureDepth = -1
    private var stepOutTargetLoopDepth = -1
    private var stepOverTargetLoopDepth = -1

    private var debugSignal = CountDownLatch(1)

    /**
     * Resets the internal state of the debugger to its default values.
     * This is typically called at the beginning or end of a debugging session.
     * It clears the highlighted line, resets procedure and loop depth counters,
     * clears any step-over/step-out targets, sets the mode to Idle,
     * and releases any waiting threads by counting down the `debugSignal`.
     */
    private fun resetDebuggerState() {
        updateState { it.copy(currentLine = -1) }
        procedureDepth = 0
        loopDepth = 0
        stepOutTargetProcedureDepth = -1
        stepOutTargetLoopDepth = -1
        stepOverTargetLoopDepth = -1
        debuggerMode = DebuggerMode.Idle
        debugSignal = CountDownLatch(0)
    }

    /**
     * Initializes and starts a new debugging session.
     *
     * This function prepares the debugger for a new run by first stopping any existing session
     * to ensure a clean state. It then sets the debugger's mode to `Running` and updates the
     * public `debuggerState` to reflect that debugging is now active. This is typically called
     * at the very beginning of the program execution when in debug mode.
     */
    fun startNewDebugSession() {
        stopDebuggingSession()
        debuggerMode = DebuggerMode.Running
        updateState { it.copy(isDebugging = true) }
    }

    /**
     * Stops the current debugging session.
     *
     * This function sets the debugger mode to `Idle`, ensuring no further debugging actions are taken.
     * It releases any waiting debugger thread by counting down the `debugSignal` latch if it's active.
     * It then calls `resetDebuggerState` to clean up internal counters and states,
     * and finally updates the public `debuggerState` to reflect that debugging is no longer active
     * and to hide debugging control buttons (Step In, Step Out, Step Over Loop).
     */
    fun stopDebuggingSession() {
        debuggerMode = DebuggerMode.Idle
        if (debugSignal.count > 0) {
            debugSignal.countDown()
        }
        resetDebuggerState()
        updateState {
            it.copy(
                isDebugging = false,
                showStepInButton = false,
                showStepOutButton = false,
                showStepOverLoopButton = false
            )
        }
    }

    /**
     * Updates the debugger state to highlight the current line and then triggers the main
     * debug pause logic. This function is the primary entry point for causing the interpreter
     * to pause execution at a specific line.
     *
     * @param line The line number to pause at. This line will be highlighted in the UI.
     * @see handleDebugPause
     */
    private fun pauseAtLine(line: Int) {
        updateState { it.copy(currentLine = line) }
        handleDebugPause(line)
    }

    /**
     * Resumes the execution of the program in a continuous run mode.
     *
     * This function is called when the user wants to exit step-by-step debugging
     * and let the program run freely until the next breakpoint is hit or the
     * program finishes. It achieves this by setting the internal `debuggerMode`
     * to `Running`. The execution will only pause again if it encounters a line
     * with a breakpoint.
     */
    fun continueExecution() {
        debuggerMode = DebuggerMode.Running
    }

    /**
     * Signals the debugger to advance to the next execution step.
     *
     * When the debugger is paused (e.g., at a breakpoint or after a previous step), this function
     * releases the execution thread to proceed. It first resets the visibility of conditional
     * debugging buttons (like "Step In", "Step Out") in the UI, as their relevance will be
     * re-evaluated at the next pause point. It then signals the internal `CountDownLatch`
     * (`debugSignal`), allowing the paused interpreter thread to continue until it hits the
     * next breakpoint, the next line in step-by-step mode, or the program finishes.
     *
     * @see handleDebugPause
     */
    fun nextStep() {
        updateState {
            it.copy(
                showStepInButton = false,
                showStepOutButton = false,
                showStepOverLoopButton = false
            )
        }
        debugSignal.countDown()
    }

    /**
     * Toggles a breakpoint for a given line number.
     *
     * If a breakpoint already exists at the specified `lineNumber`, it will be removed.
     * If no breakpoint exists, a new one will be added. This function updates the
     * `debuggerState` with the new list of breakpoints, which will be observed by the UI
     * to reflect the change visually.
     *
     * @param lineNumber The line number (1-based) on which to add or remove a breakpoint.
     */
    fun toggleBreakpoint(lineNumber: Int) {
        val newBreakpoints = _debuggerState.value.breakpoints.toMutableList()
        if (newBreakpoints.contains(lineNumber)) {
            newBreakpoints.remove(lineNumber)
        } else {
            newBreakpoints.add(lineNumber)
        }
        updateState { it.copy(breakpoints = newBreakpoints) }
    }

    fun handleDebugPause(line: Int) {

        // --- StepOverLoop ---
        if (debuggerMode == DebuggerMode.StepOverLoop) {
            // Goal: loopDepth <= (start level - 1)
            if (loopDepth <= stepOverTargetLoopDepth) {
                // We've reached the goal (we are outside the loop). Go back to pause.
                debuggerMode = DebuggerMode.StepByStep
                stepOverTargetLoopDepth = -1
            } else if (line in _debuggerState.value.breakpoints) {
                // Cancel if we hit a breakpoint
                debuggerMode = DebuggerMode.StepByStep
                stepOverTargetLoopDepth = -1
            } else {
                // We are in a loop that we are skipping. Skip the pause.
                return
            }
        }

        // --- StepOutIteration ---
        if (debuggerMode == DebuggerMode.StepOutIteration) {
            // Goal: We were at level X, we want to skip pauses until
            // we exit (level < X) and don't re-enter.

            // stepOutTargetLoopDepth stores the depth where we *started*.
            if (loopDepth == stepOutTargetLoopDepth) {
                // We are still in the same iteration. Skip the pause.
                return
            } else {
                // We have exited the iteration (loopDepth < stepOutTargetLoopDepth).
                // The next pause will be either at the beginning of a new iteration (where loopDepth == stepOutTargetLoopDepth)
                // or after the loop (where loopDepth < stepOutTargetLoopDepth).
                // In both cases, we want to stop.
                debuggerMode = DebuggerMode.StepByStep
                stepOutTargetLoopDepth = -1
                // Continue to the StepByStep pause below
            }
        }

        // --- StepOut ---
        if (debuggerMode == DebuggerMode.StepOut) {
            // Goal: procedureDepth <= (start level - 1)
            if (procedureDepth <= stepOutTargetProcedureDepth) {
                debuggerMode = DebuggerMode.StepByStep
                stepOutTargetProcedureDepth = -1
            } else if (line in _debuggerState.value.breakpoints) {
                debuggerMode = DebuggerMode.StepByStep
                stepOutTargetProcedureDepth = -1
            } else {
                return // Skip pause
            }
        }

        // pause
        if (debuggerMode == DebuggerMode.Running && line in _debuggerState.value.breakpoints) {
            debuggerMode = DebuggerMode.StepByStep
        }

        if (debuggerMode == DebuggerMode.StepIn) {
            debuggerMode = DebuggerMode.StepByStep
        }

        // Pause StepByStep
        if (debuggerMode == DebuggerMode.StepByStep) {
            _debuggerState.update {
                it.copy(
                    currentLine = line,
                    // Show StepOut if in a loop OR a procedure
                    showStepOutButton = (procedureDepth > 0 || loopDepth > 0),
                    // Show StepOverLoop only if in a loop
                    showStepOverLoopButton = (loopDepth > 0)
                )
            }
            debugSignal = CountDownLatch(1)
            debugSignal.await() // Wait for the signal
        }

        if (debuggerMode == DebuggerMode.Idle) {
            throw StopException("Debugging stopped")
        }
    }

    fun stepIn() {
        debuggerMode = DebuggerMode.StepIn
        nextStep()
    }

    fun stepOut() {
        updateState { it.copy(showStepOutButton = false) }

        if (procedureDepth > 0) {
            // 1. Priority: Always step out of a procedure
            stepOutTargetProcedureDepth = procedureDepth - 1
            stepOutTargetLoopDepth = -1 // Ignore loops
            debuggerMode = DebuggerMode.StepOut
        } else if (loopDepth > 0) {
            // 2. If not in a procedure, but in a loop: step out of the CURRENT ITERATION
            // We save the CURRENT loop depth as the target
            stepOutTargetLoopDepth = loopDepth
            stepOutTargetProcedureDepth = -1
            debuggerMode = DebuggerMode.StepOutIteration
        } else {
            // 3. At the top level: act like "Continue"
            debuggerMode = DebuggerMode.Running
        }
        nextStep()
    }

    fun stepOverLoop() {
        if (debuggerMode != DebuggerMode.StepByStep) return
        if (loopDepth == 0) return // Not in loop

        debuggerMode = DebuggerMode.StepOverLoop
        // Set the target to the level *below* the current depth (exit the loop)
        stepOverTargetLoopDepth = loopDepth - 1
        nextStep()
    }

    fun clearBreakpoints() {
        updateState { it.copy(breakpoints = mutableListOf()) }
    }

    override fun visitRepeat_(ctx: logoParser.Repeat_Context?): Int {
        val repeatCount = ctx!!.number().text.toFloat().toInt()
        val commandsBlock = ctx.block().children

        loopDepth++
        try {
           repeat(repeatCount) {
                for (command in commandsBlock.filterIsInstance<logoParser.CmdContext>()) {
                    val isProcedureCall = command.procedureInvocation() != null
                    updateState { it.copy(showStepInButton = isProcedureCall) }

                    pauseAtLine(command.start.line)
                    visit(command)
                    drawingDelegate.updateTurtleBitmap(turtleState)
                }
            }
        } catch (_: StopException) {
            return 0
        } finally {
            loopDepth--
        }
        return 0
    }

    override fun visitProcedureInvocation(ctx: logoParser.ProcedureInvocationContext?): Int {
        val procedureName = ctx!!.name().text
        if (procedures.containsKey(procedureName)) {
            val procedureCtx = procedures[procedureName]
            val previousVariables = HashMap(variables)

            procedureDepth++

            if (debuggerMode == DebuggerMode.StepIn) {
                debuggerMode = DebuggerMode.StepByStep
            }

            try {
                for (i in procedureCtx!!.parameterDeclarations().indices) {
                    val paramName = procedureCtx.parameterDeclarations()[i].name().text
                    val argumentValue = visit(ctx.expression()[i])
                    variables[paramName] = argumentValue
                }

                for (line in procedureCtx.line()) {
                    val cmd = line.cmd().firstOrNull()
                    val isProcedureCall = cmd != null && cmd.procedureInvocation() != null
                    updateState { it.copy(showStepInButton = isProcedureCall) }

                    pauseAtLine(line.start.line)
                    visit(line)
                    drawingDelegate.updateTurtleBitmap(turtleState)
                }
            } catch (_: StopException) {
                return 0
            } finally {
                variables = previousVariables
                procedureDepth--
            }
        } else {
            errors.add("Unknown procedure: $procedureName at line ${ctx.start.line}")
        }
        return 0
    }

    override fun visitProg(ctx: logoParser.ProgContext?): Int {
        startNewDebugSession()

        resetTurtleState()
        drawingDelegate.clearScreen(null)
        drawingDelegate.updateTurtleBitmap(turtleState)

        try {
            for (line in ctx!!.line()) {
                updateState { it.copy(currentLine = line.start.line) }

                val cmd = line.cmd().firstOrNull()
                val isProcedureCall = cmd != null && cmd.procedureInvocation() != null
                updateState { it.copy(showStepInButton = isProcedureCall) }

                handleDebugPause(line.start.line)

                visit(line)
                drawingDelegate.updateTurtleBitmap(turtleState)
            }
        } catch (e: Exception) {

        } finally {
            debuggerMode = DebuggerMode.Finished
            stopDebuggingSession()
        }
        return 0
    }
}