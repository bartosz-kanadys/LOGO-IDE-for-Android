package com.example.logointerpreterbeta.domain.errors


import org.antlr.v4.runtime.BaseErrorListener
import org.antlr.v4.runtime.RecognitionException
import org.antlr.v4.runtime.Recognizer

class MyErrorListener : BaseErrorListener() {
    val errors = mutableListOf<String>()

    override fun syntaxError(
        recognizer: Recognizer<*, *>?,
        offendingSymbol: Any?,
        line: Int,
        charPositionInLine: Int,
        msg: String?,
        e: RecognitionException?
    ) {
        var errorMessage = "Error at line $line:$charPositionInLine - $msg"
        val index = errorMessage.indexOf("expecting")
        if (index != -1) {
            errorMessage = errorMessage.substring(0, index)
        }
        errors.add(errorMessage)
        println(errorMessage)
    }
}
