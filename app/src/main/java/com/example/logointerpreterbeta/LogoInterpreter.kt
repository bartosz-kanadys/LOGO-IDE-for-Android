package com.example.logointerpreterbeta

import android.graphics.Bitmap
import com.example.logointerpreterbeta.interpreter.logoLexer
import com.example.logointerpreterbeta.interpreter.logoParser
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTreeWalker

class LogoInterpreter {

    var bitmap:Bitmap? = null

    //"fd 100 rt 90 penup fd 100 rt 90 pendown fd 100 rt 90 penup fd 100";


    var walker =  ParseTreeWalker()
    var listener = ForwardMethodListener()

    fun start(text: String) {
        var context: String = text
        var lexer = logoLexer(CharStreams.fromString(context))

        // create a buffer of tokens pulled from the lexer
        var tokens = CommonTokenStream(lexer)
        // create a parser that feeds off the tokens buffer
        var parser = logoParser(tokens)

        var tree = parser.prog() // begin parsing at init rule


        walker.walk(listener, tree)
        bitmap = listener.image
    }

}