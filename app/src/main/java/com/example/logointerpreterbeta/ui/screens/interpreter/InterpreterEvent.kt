package com.example.logointerpreterbeta.ui.screens.interpreter

import com.example.logointerpreterbeta.domain.models.Project
import com.example.logointerpreterbeta.domain.models.ProjectFile

sealed class InterpreterEvent {
    // Zdarzenia edytora
    data class OnCodeChange(val newText: String, val newCursorPosition: Int) : InterpreterEvent()

    // Zdarzenia interpretera
    data object InterpretCode : InterpreterEvent()
    data object ClearErrors : InterpreterEvent()

    // Zdarzenia debuggera
    data object EnableDebugging : InterpreterEvent()
    data object DisableDebugging : InterpreterEvent()
    data object NextStep : InterpreterEvent()
    data object StepIn : InterpreterEvent()
    data object StepOut : InterpreterEvent()
    data object ContinueExecution : InterpreterEvent()
    data class ToggleBreakpoint(val lineNumber: Int) : InterpreterEvent()
    data object ClearBreakpoints : InterpreterEvent()

    // Zdarzenia UI
    data object ToggleErrorListVisibility : InterpreterEvent()

    // Zdarzenia plików
    data class LoadInitialCode(val project: Project?) : InterpreterEvent()
    data class SaveFile(val projectName: String) : InterpreterEvent()
    data class FileTapped(val file: ProjectFile, val project: Project?) : InterpreterEvent()
}