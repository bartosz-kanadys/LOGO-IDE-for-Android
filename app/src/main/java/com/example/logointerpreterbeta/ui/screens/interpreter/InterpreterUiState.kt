package com.example.logointerpreterbeta.ui.screens.interpreter

import android.graphics.Bitmap
import com.example.logointerpreterbeta.domain.models.Config
import com.example.logointerpreterbeta.domain.models.DebuggerState
import com.example.logointerpreterbeta.ui.models.TurtleUI

data class InterpreterUiState(
    val codeEditorState: CodeEditorState = CodeEditorState(),
    val currentFileName: String? = null,
    val errors: List<String> = emptyList(),
    val isErrorListExpanded: Boolean = false,
    val debuggerState: DebuggerState = DebuggerState(),
    val turtleState: TurtleUI = TurtleUI(),
    val canvasBitmap: Bitmap,
    val turtleBitmap: Bitmap,
    val isDarkMode: Boolean = false,
    val configSettings: Config = Config()
)

data class CodeEditorState(
    val text: String = "\n\n\n\n\n\n\n\n\n\n\n",
    val cursorPosition: Int = 0
)