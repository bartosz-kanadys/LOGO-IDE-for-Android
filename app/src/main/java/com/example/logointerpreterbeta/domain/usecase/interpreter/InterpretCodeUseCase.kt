package com.example.logointerpreterbeta.domain.usecase.interpreter

import com.example.logointerpreterbeta.domain.interpreter.LogoInterpreter
import com.example.logointerpreterbeta.domain.models.InterpreterResult
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InterpretCodeUseCase @Inject constructor(
    private val logoInterpreter: LogoInterpreter
) {
    operator fun invoke(code: String): InterpreterResult {
        return try {
            val result = logoInterpreter.interpret(code)
            InterpreterResult(success = result.success, errors = result.errors)
        } catch (e: Exception) {
            InterpreterResult(success = false, errors = listOf(e.message ?: "Interpreter error"))
        }
    }
}