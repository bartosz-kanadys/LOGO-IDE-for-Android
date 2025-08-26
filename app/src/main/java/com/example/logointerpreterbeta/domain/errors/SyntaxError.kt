package com.example.logointerpreterbeta.domain.errors

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SyntaxError(message: String) : RuntimeException(message) {
    companion object {
        private val _errors = MutableStateFlow<List<String>>(emptyList())
        val errors: StateFlow<List<String>> get() = _errors

        fun addError(error: String) {
            val currentErrors = _errors.value.toMutableList()
            currentErrors.add(error)
            _errors.value = currentErrors
        }

        fun clearErrors() {
            _errors.value = emptyList()
        }
    }
}
