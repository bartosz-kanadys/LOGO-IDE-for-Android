package com.example.logointerpreterbeta

import android.app.UiModeManager
import android.content.Context
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import com.example.logointerpreterbeta.errors.MyErrorListener
import com.example.logointerpreterbeta.interpreter.logoLexer
import com.example.logointerpreterbeta.interpreter.logoParser
import com.example.logointerpreterbeta.ui.theme.cmdColorDark
import com.example.logointerpreterbeta.ui.theme.functionColorDark
import com.example.logointerpreterbeta.ui.theme.numberColorDark
import com.example.logointerpreterbeta.ui.theme.onSurfaceDarkMediumContrast
import com.example.logointerpreterbeta.ui.theme.onSurfaceLightMediumContrast
import com.example.logointerpreterbeta.ui.theme.stringColorDark
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream


class LogoInterpreter(context: Context) {
    var bitmap = MyLogoVisitor.image

    private val myVisitor = MyLogoVisitor(context = context)
    val uiModeManager = context.getSystemService(Context.UI_MODE_SERVICE) as UiModeManager


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

    fun colorizeText(text: String): AnnotatedString {
        val lexer = logoLexer(CharStreams.fromString(text))
        val tokens = CommonTokenStream(lexer)
        tokens.fill()

        val isDarkTheme = uiModeManager.nightMode == UiModeManager.MODE_NIGHT_YES
        val textColor = if(isDarkTheme) {
            onSurfaceDarkMediumContrast
        } else {
            onSurfaceLightMediumContrast
        }

        val styledText = buildAnnotatedString {
            tokens.tokens.forEach { token ->
                if (token.type == logoLexer.EOF) return@forEach

                val color = when (token.type) {
                    logoLexer.STRINGLITERAL         -> stringColorDark   // stringi np "Test
                    1, 2, 3, 4                      -> functionColorDark          //deklaracja procedury
                    in 7..77, 80, 81, 82      -> cmdColorDark        //cmd fd, rt ...
                    in 83..94                 -> functionColorDark        //funkcje repeat if random ...
                    logoLexer.NUMBER, 105, 106      -> numberColorDark             //cyfry
                    else                            -> textColor   //reszta
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