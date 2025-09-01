package com.example.logointerpreterbeta.ui.screens.interpreter

import android.graphics.Bitmap
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.logointerpreterbeta.domain.interpreter.LogoInterpreter
import com.example.logointerpreterbeta.domain.interpreter.LogoTextColorizer
import com.example.logointerpreterbeta.domain.models.DebuggerState
import com.example.logointerpreterbeta.ui.drawing.UIDrawingDelegate
import com.example.logointerpreterbeta.ui.screens.interpreter.components.codeEditor.textFunctions.textDiffrence
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class InterpreterViewModel @Inject constructor(
    private val logo: LogoInterpreter,
    drawingDelegate: UIDrawingDelegate
) : ViewModel() {
    private val debugMutex = Mutex()

    private var codeState by mutableStateOf(TextFieldValue("\n\n\n\n\n\n\n\n\n\n\n"))
    private var cursorPosition by mutableIntStateOf(0)

    private val _isErrorListExpanded = MutableStateFlow(false)
    var isErrorListExpanded = _isErrorListExpanded.asStateFlow()

    private val _debuggerState = MutableStateFlow(DebuggerState())
    val debuggerState: StateFlow<DebuggerState> = _debuggerState.asStateFlow()

    private val _isDebugging = MutableStateFlow(false)
    var isDebugging = _isDebugging.asStateFlow()

    private val _img = drawingDelegate.bitmapFlow
    val img: StateFlow<Bitmap> = _img

    private val _arrowImg = drawingDelegate.turtleBitmapFlow
    val arrowImg: StateFlow<Bitmap> = _arrowImg

    private val _errors = MutableStateFlow<List<String>>(emptyList())
    val errors: StateFlow<List<String>> = _errors.asStateFlow()

    init {
        logo.interpret("st")
    }

    fun updateCodeState(newCode: String) {
        codeState = TextFieldValue(newCode)
    }

    fun getCodeStateAsString(): String {
        return codeState.text
    }

    fun getCodeStateAsTextFieldValue(): TextFieldValue {
        return codeState
    }

    fun onCodeChange(newCode: TextFieldValue) {
        cursorPosition = newCode.selection.start
        codeState = newCode.copy(
            annotatedString = textDiffrence(
                codeState.annotatedString,
                newCode.text,
            ) { text ->
                LogoTextColorizer.colorizeText(text)
            }
        )
    }

    fun toggleErrorListVisibility() {
        _isErrorListExpanded.update { !_isErrorListExpanded.value  }
    }

    fun colorCode() {
        codeState = codeState.copy(
            annotatedString = LogoTextColorizer.colorizeText(codeState.text)
        )
    }

    fun enableDebugging() {
        _isDebugging.update { true }
        logo.enableDebugging()
        debugCode()
    }

    fun disableDebugging() {
        _isDebugging.update { false }
        logo.disableDebugging()
    }

    fun clearErrors() {
        _errors.value = emptyList()
    }

    fun nextStep() {
        logo.nextStep()
        _debuggerState.value = logo.getDebuggerState()
    }

    fun stepIn() {
        logo.stepIn()
        _debuggerState.value = logo.getDebuggerState()
    }

    fun stepOut() {
        logo.stepOut()
        _debuggerState.value = logo.getDebuggerState()
    }

    fun continueExecution() {
        logo.continueExecution()
        _debuggerState.value = logo.getDebuggerState()
    }

    fun toggleBreakpoint(lineNumber: Int) {
        logo.toggleBreakpoint(lineNumber)
        _debuggerState.value = logo.getDebuggerState()
    }

    fun clearBreakpoints() {
        logo.clearBreakpoints()
        _debuggerState.value = logo.getDebuggerState()
    }

    fun interpretCode(text: String = codeState.text) {
        viewModelScope.launch {
            clearErrors()

            val result = logo.interpret(text)

            if (!result.success) {
                _errors.value = result.errors
            }
        }
    }

    private fun debugCode() {
        viewModelScope.launch(Dispatchers.Default) {
            debugMutex.withLock {
                clearErrors()

                val result = logo.debug(codeState.text)

                viewModelScope.launch(Dispatchers.Main) {
                    _debuggerState.update { logo.getDebuggerState() }
                }
                withContext(Dispatchers.Main) {
                    if (!result.success) {
                        _errors.value = result.errors
                    }
                }
            }
        }
    }
}