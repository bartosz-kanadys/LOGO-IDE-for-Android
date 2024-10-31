package com.example.logointerpreterbeta

import android.content.Context
import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import com.example.logointerpreterbeta.errors.MyErrorListener
import com.example.logointerpreterbeta.interpreter.logoLexer
import com.example.logointerpreterbeta.interpreter.logoParser
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.Token

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
        val lexer = logoLexer(
            CharStreams.fromString(text)
        )
        lexer.removeErrorListeners()
        lexer.addErrorListener(MyErrorListener())
        // Tokenizacja
        val tokens = CommonTokenStream(lexer)
        // Parsowanie
        val parser = logoParser(tokens)
        parser.removeErrorListeners()
        parser.addErrorListener(MyErrorListener())
        val tokenList = mutableListOf<Token>()
        // Przykładowa mapa kolorów tokenów
        while (true) {
            val token = lexer.nextToken()
            if (token.type == Token.EOF) break
            Log.i("Token", token.type.toString()+" "+token.text)
            tokenList.add(token)
        }
        val highlightedCode = highlightCode(tokenList)
        return highlightedCode
    }

    fun highlightCode(tokenList: List<Token>): AnnotatedString {
        val tokenColors = mapOf(
            109 to Color.Magenta,
            55 to Color.Green
        )
        return buildAnnotatedString {
            tokenList.forEach { token ->
                val tokenType = token.type
                val tokenText = token.text
                val color = tokenColors[tokenType] ?: Color.Black
                withStyle(style = SpanStyle(color = color)) {
                    append(tokenText)
                }
            }
        }
    }

}