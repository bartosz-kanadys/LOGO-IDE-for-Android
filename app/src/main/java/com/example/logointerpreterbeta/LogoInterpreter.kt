package com.example.logointerpreterbeta

import android.content.Context
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import com.example.logointerpreterbeta.errors.MyErrorListener
import com.example.logointerpreterbeta.interpreter.logoLexer
import com.example.logointerpreterbeta.interpreter.logoParser
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream

class LogoInterpreter(context: Context) {
    var bitmap = MyImage

    private val myVisitor = MyLogoVisitor(context = context)

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
        bitmap = MyLogoVisitor.image
    }

    fun colorizeText1(text: String): AnnotatedString {
        val lexer = logoLexer(CharStreams.fromString(text))
        val tokens = CommonTokenStream(lexer)
        tokens.fill()

        val styledText = buildAnnotatedString {
            tokens.tokens.forEach { token ->
                if (token.type == logoLexer.EOF) return@forEach

                val color = when (token.type) {
                    logoLexer.STRINGLITERAL -> Color.Green
                    1, 2, 3, 4 -> Color.Magenta
                    in 7..77, 80, 81, 82 -> Color.Blue
                    in 83..94 -> Color.Magenta
                    logoLexer.NUMBER, 105, 106 -> Color.Red
                    else -> Color.Black
                }
                val tokenText = token.text
                pushStyle(SpanStyle(color = color))
                append(tokenText)
                pop()
            }
        }

        return styledText
    }
}