package com.example.logointerpreterbeta.domain.interpreter

import com.example.logointerpreterbeta.domain.drawing.DrawingDelegate
import com.example.logointerpreterbeta.domain.interpreter.antlrFIles.logoLexer
import com.example.logointerpreterbeta.domain.interpreter.antlrFIles.logoParser
import com.example.logointerpreterbeta.domain.interpreter.errors.MyErrorListener
import com.example.logointerpreterbeta.domain.interpreter.errors.StopException
import com.example.logointerpreterbeta.domain.models.DebuggerState
import com.example.logointerpreterbeta.domain.models.InterpreterResult
import com.example.logointerpreterbeta.domain.visitors.DebugStateListener
import com.example.logointerpreterbeta.domain.visitors.DebuggerVisitor
import kotlinx.coroutines.flow.StateFlow
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream

class LogoDebugger(
    private val drawingDelegate: DrawingDelegate
) {
    private val debuggerVisitor = DebuggerVisitor(
        drawingDelegate = drawingDelegate
    )
    val debuggerState: StateFlow<DebuggerState> = debuggerVisitor.debuggerState

    fun debug(
        input: String
    ): InterpreterResult {
        val errorListener = MyErrorListener()
        val tree = parseInput(input + "\n", errorListener)

        return if (errorListener.errors.isEmpty()) {
            debuggerVisitor.resetTurtleState()
            try {
                debuggerVisitor.startNewDebugSession()
                for (line in tree.line()) {
                    debuggerVisitor.updateCurrentLine(line.start.line + 1)
                    debuggerVisitor.handleDebugPause(line.start.line) // <- tu czekasz
                    debuggerVisitor.visit(line)
                }
            } catch (e: StopException) {
            } finally {
                debuggerVisitor.stopDebuggingSession()
            }
            InterpreterResult(
                success = true,
                errors = emptyList(),
            )
        } else {
            InterpreterResult(
                success = false,
                errors = errorListener.errors,
            )
        }
    }

    private fun parseInput(input: String, errorListener: MyErrorListener): logoParser.ProgContext {
        // Tworzenie lexer'a
        val lexer = logoLexer(CharStreams.fromString(input))
        lexer.removeErrorListeners()
        lexer.addErrorListener(errorListener)

        // Tokenizacja
        val tokens = CommonTokenStream(lexer)

        // Parsowanie
        val parser = logoParser(tokens)
        parser.removeErrorListeners()
        parser.addErrorListener(errorListener)

        return parser.prog()
    }

    fun continueExecution() {
        debuggerVisitor.continueExecution()
        debuggerVisitor.nextStep()
    }

    fun clearBreakpoints() = debuggerVisitor.clearBreakpoints()
    fun toggleBreakpoint(lineNumber: Int) = debuggerVisitor.toggleBreakpoint(lineNumber)
    fun nextStep() = debuggerVisitor.nextStep()
    fun enableDebugging() = debuggerVisitor.startNewDebugSession()
    fun disableDebugging() = debuggerVisitor.stopDebuggingSession()
    fun stepIn() = debuggerVisitor.stepIn()
    fun stepOut() = debuggerVisitor.stepOut()
}