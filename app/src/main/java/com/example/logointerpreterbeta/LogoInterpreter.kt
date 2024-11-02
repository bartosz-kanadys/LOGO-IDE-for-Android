package com.example.logointerpreterbeta

import android.content.Context
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.toArgb
import com.example.logointerpreterbeta.errors.MyErrorListener
import com.example.logointerpreterbeta.interpreter.logoLexer
import com.example.logointerpreterbeta.interpreter.logoParser
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream


class LogoInterpreter(context: Context, bgcolor: Int) {
    var bitmap = MyImage

    private val myVisitor = MyLogoVisitor(context = context, bgcolor)

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
}