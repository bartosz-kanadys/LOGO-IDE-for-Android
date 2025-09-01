package com.example.logointerpreterbeta.domain.models

data class DebuggerState(
    val currentLine: Int = -1,
    val breakpoints: MutableList<Int> = mutableListOf(),
    val showStepInButton: Boolean = false,
    val showStepOutButton: Boolean = false
)
