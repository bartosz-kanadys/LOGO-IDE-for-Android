package com.example.logointerpreterbeta

import android.graphics.Bitmap
import com.example.logointerpreterbeta.interpreter.logoLexer
import com.example.logointerpreterbeta.interpreter.logoParser
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream

class LogoInterpreter {

    var bitmap:Bitmap? = null

    //"fd 100 rt 90 penup fd 100 rt 90 pendown fd 100 rt 90 penup fd 100";

//    private var walker =  ParseTreeWalker()
//    private var listener = MyLogoListener()

    fun start(input: String) {
        // Tworzenie lexer'a
        val lexer = logoLexer(
            CharStreams.fromString(input)
        )

        // Tokenizacja
        val tokens = CommonTokenStream(lexer)

        // Parsowanie
        val parser = logoParser(tokens)

        // Startujemy od reguły głównej (prog)
        val tree = parser.prog()

        val myVisitor = MyLogoVisitor()
        myVisitor.visit(tree)
        bitmap = myVisitor.image

//        walker.walk(listener, tree)
//        bitmap = listener.image
    }

}