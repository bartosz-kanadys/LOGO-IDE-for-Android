package com.example.logointerpreterbeta.ui.screens.interpreter

import com.example.logointerpreterbeta.domain.models.Project
import com.example.logointerpreterbeta.domain.models.ProjectFile

sealed class InterpreterEvent {
    // editor events
    data class OnCodeChange(val newText: String, val newCursorPosition: Int) : InterpreterEvent()

    // interpreter events
    data object InterpretCode : InterpreterEvent()
    data object ClearErrors : InterpreterEvent()

    // debugger events
    data object EnableDebugging : InterpreterEvent()
    data object DisableDebugging : InterpreterEvent()
    data object StepOverLoop : InterpreterEvent()
    data object NextStep : InterpreterEvent()
    data object StepIn : InterpreterEvent()
    data object StepOut : InterpreterEvent()
    data object ContinueExecution : InterpreterEvent()
    data class ToggleBreakpoint(val lineNumber: Int) : InterpreterEvent()
    data object ClearBreakpoints : InterpreterEvent()

    // ui events
    data object ToggleErrorListVisibility : InterpreterEvent()

    // file events
    data class LoadInitialCode(val project: Project?) : InterpreterEvent()
    data class SaveFile(val projectName: String) : InterpreterEvent()
    data class FileTapped(val file: ProjectFile, val project: Project?) : InterpreterEvent()
}