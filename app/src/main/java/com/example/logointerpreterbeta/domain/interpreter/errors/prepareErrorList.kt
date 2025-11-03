package com.example.logointerpreterbeta.domain.interpreter.errors

fun prepareErrorList(errorList: MutableList<String>): MutableMap<Int, String> {
    val errorMap = mutableMapOf<Int, String>()
    if (errorList.isNotEmpty()) {
        for (error in errorList) {
            val lineRegex = """line (\d+):""".toRegex()
            val matchResult = lineRegex.find(error)
            if (matchResult != null) {
                val lineNumber = matchResult.groupValues[1].toInt()
                errorMap[lineNumber] = error
            } else {
                errorMap[-1] = "Invalid format in error message: $error"
            }
        }
    } else {
        errorMap[-1] = "Succes"
    }
    return errorMap
}