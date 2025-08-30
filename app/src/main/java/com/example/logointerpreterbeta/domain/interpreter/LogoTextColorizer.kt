package com.example.logointerpreterbeta.domain.interpreter

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import com.example.logointerpreterbeta.domain.interpreter.antlrFIles.logoLexer
import com.example.logointerpreterbeta.ui.screens.settings.SettingsViewModel
import com.example.logointerpreterbeta.ui.theme.cmdColorDark
import com.example.logointerpreterbeta.ui.theme.functionColorDark
import com.example.logointerpreterbeta.ui.theme.numberColorDark
import com.example.logointerpreterbeta.ui.theme.onSurfaceDarkMediumContrast
import com.example.logointerpreterbeta.ui.theme.onSurfaceLightMediumContrast
import com.example.logointerpreterbeta.ui.theme.stringColorDark
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream

object LogoTextColorizer {
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
}