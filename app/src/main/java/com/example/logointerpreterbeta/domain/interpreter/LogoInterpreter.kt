package com.example.logointerpreterbeta.domain.interpreter

import com.example.logointerpreterbeta.domain.drawing.DrawingDelegate
import com.example.logointerpreterbeta.domain.interpreter.antlrFIles.logoLexer
import com.example.logointerpreterbeta.domain.interpreter.antlrFIles.logoParser
import com.example.logointerpreterbeta.domain.interpreter.errors.MyErrorListener
import com.example.logointerpreterbeta.domain.models.InterpreterResult
import com.example.logointerpreterbeta.domain.repository.LibraryRepository
import com.example.logointerpreterbeta.domain.visitors.MyLogoLibraryVisitor
import com.example.logointerpreterbeta.domain.visitors.MyLogoVisitor
import jakarta.inject.Inject
import jakarta.inject.Singleton
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream

@Singleton
class LogoInterpreter @Inject constructor(
    private val drawingDelegate: DrawingDelegate,
    private val libraryRepository: LibraryRepository,
    private val isDarkMode: Boolean
) {
    private val myVisitor = MyLogoVisitor(
        drawingDelegate = drawingDelegate,
        libraryRepository = libraryRepository,
        isDarkMode = isDarkMode,
    )
    private val myLogoLibraryVisitor = MyLogoLibraryVisitor()

    fun getProceduresFromLibrary(): MutableMap<String, logoParser.ProcedureDeclarationContext> {
        return myLogoLibraryVisitor.getProcedures()
    }

    fun interpret(code: String): InterpreterResult {
        val errorListener = MyErrorListener()
        val tree = parseInput(code + "\n", errorListener)

        return if (errorListener.errors.isEmpty()) {
            myVisitor.visit(tree)
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
}