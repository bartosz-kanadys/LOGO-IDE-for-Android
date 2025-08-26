package com.example.logointerpreterbeta.domain.interpreter

import android.content.Context
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import com.example.logointerpreterbeta.domain.interpreter.antlrFIles.logoLexer
import com.example.logointerpreterbeta.domain.interpreter.antlrFIles.logoParser
import com.example.logointerpreterbeta.domain.visitors.DebuggerVisitor
import com.example.logointerpreterbeta.domain.visitors.MyLogoLibraryVisitor
import com.example.logointerpreterbeta.domain.visitors.MyLogoVisitor
import com.example.logointerpreterbeta.domain.errors.MyErrorListener
import com.example.logointerpreterbeta.ui.theme.cmdColorDark
import com.example.logointerpreterbeta.ui.theme.functionColorDark
import com.example.logointerpreterbeta.ui.theme.numberColorDark
import com.example.logointerpreterbeta.ui.theme.onSurfaceDarkMediumContrast
import com.example.logointerpreterbeta.ui.theme.onSurfaceLightMediumContrast
import com.example.logointerpreterbeta.ui.theme.stringColorDark
import com.example.logointerpreterbeta.ui.viewModels.SettingsViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LogoInterpreter @Inject constructor(
    @ApplicationContext private val context: Context,
) {
    var bitmap = MyLogoVisitor.Companion.image

    private val myVisitor = MyLogoVisitor(context = context)
    private val myLogoLibraryVisitor = MyLogoLibraryVisitor()
    private val debuggerVisitor = DebuggerVisitor(context = context)

    fun getPoceduresFromLibrary(): MutableMap<String, logoParser.ProcedureDeclarationContext> {
        return myLogoLibraryVisitor.getProcedures()
    }

    fun start(input: String) {
        // Tworzenie lexer'a
        val lexer = logoLexer(
            CharStreams.fromString(input)
        )
        lexer.removeErrorListeners()
        lexer.addErrorListener(MyErrorListener())

        // Tokenizacja
        val tokens = CommonTokenStream(lexer)

        // Parsowanie
        val parser = logoParser(tokens)
        parser.removeErrorListeners()
        parser.addErrorListener(MyErrorListener())

        // Startujemy od reguły głównej (prog)
        val tree = parser.prog()

        myVisitor.visit(tree)
        bitmap = MyLogoVisitor.Companion.image
    }

    fun debug(input: String) {
        // Tworzenie lexer'a
        val lexer = logoLexer(
            CharStreams.fromString(input)
        )
        lexer.removeErrorListeners()
        lexer.addErrorListener(MyErrorListener())

        // Tokenizacja
        val tokens = CommonTokenStream(lexer)

        // Parsowanie
        val parser = logoParser(tokens)
        parser.removeErrorListeners()
        parser.addErrorListener(MyErrorListener())

        // Startujemy od reguły głównej (prog)
        val tree = parser.prog()
        debuggerVisitor.visit(tree)
        bitmap = MyLogoVisitor.Companion.image
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

    fun colorizeText(text: String): AnnotatedString {
        val lexer = logoLexer(CharStreams.fromString(text))
        val tokens = CommonTokenStream(lexer)
        tokens.fill()

        val textColor = if (SettingsViewModel.Companion.darkMode) {
            onSurfaceDarkMediumContrast
        } else {
            onSurfaceLightMediumContrast
        }

        val styledText = buildAnnotatedString {
            tokens.tokens.forEach { token ->
                if (token.type == logoLexer.EOF) return@forEach

                val color = when (token.type) {
                    logoLexer.STRINGLITERAL -> stringColorDark   // stringi np "Test
                    1, 2, 3, 4 -> functionColorDark          //deklaracja procedury
                    in 7..77, 80, 81, 82 -> cmdColorDark        //cmd fd, rt ...
                    in 83..96 -> functionColorDark        //funkcje repeat if random ...
                    logoLexer.NUMBER, 105, 106 -> numberColorDark             //cyfry
                    else -> textColor   //reszta
                }
                val tokenText = token.text
                pushStyle(SpanStyle(color = color))
                append(tokenText)
                pop()
            }
        }
        return styledText
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