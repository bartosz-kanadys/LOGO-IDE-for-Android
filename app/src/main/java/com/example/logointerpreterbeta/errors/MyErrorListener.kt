package com.example.logointerpreterbeta.errors


import org.antlr.v4.runtime.BaseErrorListener
import org.antlr.v4.runtime.RecognitionException

class MyErrorListener : BaseErrorListener() {

    // Nadpisanie metody do obsługi błędów parsowania
    override fun syntaxError(
        recognizer: org.antlr.v4.runtime.Recognizer<*, *>?,
        offendingSymbol: Any?,
        line: Int,
        charPositionInLine: Int,
        msg: String?,
        e: RecognitionException?
    ) {
        var errorMessage = "Error at line $line:$charPositionInLine - $msg"
        val index = errorMessage.indexOf("expecting")
        if (index != -1){
            errorMessage = errorMessage.substring(0, index)
        }
        SyntaxError.errors.add(errorMessage)
        println(errorMessage)
    }
}