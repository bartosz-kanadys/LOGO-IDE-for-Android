package com.example.logointerpreterbeta.errors

class SyntaxError(message: String) : RuntimeException(message) {
    companion object {
        var errors: MutableList<String> = emptyList<String>().toMutableList()
    }
}