package com.example.logointerpreterbeta.domain.interpreter

import com.example.logointerpreterbeta.domain.drawing.DrawingDelegate
import com.example.logointerpreterbeta.domain.interpreter.antlrFIles.logoLexer
import com.example.logointerpreterbeta.domain.interpreter.antlrFIles.logoParser
import com.example.logointerpreterbeta.domain.interpreter.errors.MyErrorListener
import com.example.logointerpreterbeta.domain.interpreter.errors.StopException
import com.example.logointerpreterbeta.domain.models.InterpreterResult
import com.example.logointerpreterbeta.domain.models.drawing.DrawingResult
import com.example.logointerpreterbeta.domain.repository.LibraryRepository
import com.example.logointerpreterbeta.domain.visitors.DebuggerVisitor
import com.example.logointerpreterbeta.domain.visitors.MyLogoLibraryVisitor
import com.example.logointerpreterbeta.domain.visitors.MyLogoVisitor
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LogoInterpreter @Inject constructor(
    private val drawingDelegate: DrawingDelegate,
    private val libraryRepository: LibraryRepository,
    private val isDarkMode: Boolean
) {
    private val myVisitor = MyLogoVisitor(
        drawingDelegate = drawingDelegate,
        libraryRepository = libraryRepository,
        isDarkMode = isDarkMode
    )
    private val myLogoLibraryVisitor = MyLogoLibraryVisitor()
    private val debuggerVisitor = DebuggerVisitor(
        drawingDelegate = drawingDelegate,
    )

    fun getProceduresFromLibrary(): MutableMap<String, logoParser.ProcedureDeclarationContext> {
        return myLogoLibraryVisitor.getProcedures()
    }

    fun interpret(code: String): InterpreterResult {
        val errorListener = MyErrorListener()
        val tree = parseInput(code + "\n", errorListener)

        return if (errorListener.errors.isEmpty()) {
            myVisitor.visit(tree)
            val imageResult = myVisitor.getImage()
            val arrowImage = myVisitor.getArrowImage()
            InterpreterResult(
                success = true,
                errors = emptyList(),
                image = imageResult,
                arrowImage = arrowImage
            )
        } else {
            InterpreterResult(
                success = false,
                errors = errorListener.errors,
                image = null,
                arrowImage = null
            )
        }
    }

    fun debug(input: String, onStep: (DrawingResult, DrawingResult) -> Unit): InterpreterResult {
        val errorListener = MyErrorListener()
        val tree = parseInput(input + "\n", errorListener)

        return if (errorListener.errors.isEmpty()) {
            debuggerVisitor.enableDebugging()

            debuggerVisitor.resetTurtleState()
            try {
                debuggerVisitor.startNewDebugSession()
                for (line in tree.line()) {
                    DebuggerVisitor.currentLine = line.start.line
                    debuggerVisitor.waitForDebugSignal()
                    debuggerVisitor.visit(line)

                    val imageResult = debuggerVisitor.getImage()
                    val arrowImage = debuggerVisitor.getArrowImage()
                    onStep(imageResult, arrowImage)
                }
                debuggerVisitor.stopDebuggingSession()
            } catch (e: StopException) {

            }

            debuggerVisitor.disableDebugging()
            val finalImage = debuggerVisitor.getImage()
            val finalArrow = debuggerVisitor.getArrowImage()
            InterpreterResult(
                success = true,
                errors = emptyList(),
                image = finalImage,
                arrowImage = finalArrow
            )
        } else {
            InterpreterResult(
                success = false,
                errors = errorListener.errors,
                image = null,
                arrowImage = null
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

    fun processProcedure(input: String) {
        val lexer = logoLexer(
            CharStreams.fromString(input)
        )
        lexer.removeErrorListeners()
        lexer.addErrorListener(MyErrorListener())
        val tokens = CommonTokenStream(lexer)
        val parser = logoParser(tokens)
        parser.removeErrorListeners()
        parser.addErrorListener(MyErrorListener())
        val tree = parser.prog()

        myLogoLibraryVisitor.visit(tree)
    }

    fun nextStep() {
        debuggerVisitor.nextStep()
    }

    fun enableDebugging() {
        debuggerVisitor.enableDebugging()
    }

    fun disableDebugging() {
        debuggerVisitor.disableDebugging()
    }

    fun continueExecution() {
        debuggerVisitor.continueExecution()
        debuggerVisitor.nextStep()
    }

    fun stepIn() {
        debuggerVisitor.stepIn()
    }

    fun stepOut() {
        debuggerVisitor.stepOut()
    }
}