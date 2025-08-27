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
import com.example.logointerpreterbeta.ui.models.TurtleUI
import com.example.logointerpreterbeta.ui.MyImageHeight
import com.example.logointerpreterbeta.ui.MyImageWidth
import com.example.logointerpreterbeta.ui.screens.interpreter.components.codeEditor.textFunctions.textDiffrence
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class InterpreterViewModel @Inject constructor(
    private val logo: LogoInterpreter
) : ViewModel() {
//    companion object {
//        var errors = MutableStateFlow(SyntaxError.Companion.errors.value)
//        var isErrorListVisable by mutableStateOf(false)
//    }
    private var isErrorListVisible by mutableStateOf(false)
    private var codeState by mutableStateOf(TextFieldValue("\n\n\n\n\n\n\n\n\n\n\n"))
    private var cursorPosition by mutableIntStateOf(0)
    private var img by mutableStateOf(Bitmap.createBitmap(2000, 2000, Bitmap.Config.ARGB_8888))
    var isErrorListExpanded by mutableStateOf(false)
        private set
    var isDebugging by mutableStateOf(false)
    private val mutex = Mutex()

    private val _errors = MutableStateFlow<List<String>>(emptyList())
    val errors: StateFlow<List<String>> = _errors.asStateFlow()

    init {
        // Ustawienia początkowe pozycji i kierunku
        TurtleUI.setAcctualPosition(MyImageWidth.toFloat() / 2, MyImageHeight.toFloat() / 2)
        TurtleUI.direction = 0f
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
                logo::colorizeText
            )
        )
    }

    fun toggleErrorListVisibility() {
        isErrorListExpanded = !isErrorListExpanded
    }

    fun colorCode() {
        codeState = codeState.copy(
            annotatedString = logo.colorizeText(codeState.text)
        )
    }

    fun nextStep() {
        logo.nextStep()
    }

    fun enableDebugging() {
        isDebugging = true
        logo.enableDebugging()
        debugCode()
    }

    fun disableDebugging() {
        isDebugging = false
        logo.disableDebugging()
    }

    fun continueExecution() {
        logo.continueExecution()
    }

    fun stepIn() {
        logo.stepIn()
    }

    fun stepOut() {
        logo.stepOut()
    }

    fun addError(error: String) {
        _errors.value = _errors.value + error
    }

    fun clearErrors() {
        _errors.value = emptyList()
    }

    fun interpretCode(text: String = codeState.text) {
//        viewModelScope.launch {
//            clearErrors()
//            Turtle.setAcctualPosition(MyImageWidth.toFloat() / 2, MyImageHeight.toFloat() / 2)
//            Turtle.direction = 0f
//            try {
//                logo.start(text + "\n")
//                img = logo.bitmap
//            } catch (e: Exception) {
//                Log.e("ERROR", "Błąd wykonywania interpretera")
//            } finally {
////                _errors.value = SyntaxError.Companion.errors.value
//                isErrorListVisible = errors.value.isNotEmpty()
//            }
//        }
        viewModelScope.launch {
            clearErrors()
            TurtleUI.setAcctualPosition(MyImageWidth.toFloat() / 2, MyImageHeight.toFloat() / 2)
            TurtleUI.direction = 0f

            val result = logo.interpret(text)

            if (!result.success) {
                _errors.value = result.errors
            }

            img = logo.bitmap
            isErrorListVisible = errors.value.isNotEmpty()
        }
    }

    private fun debugCode() {
//        viewModelScope.launch(Dispatchers.Default) {
//            mutex.withLock {
//                clearErrors()
//                TurtleUI.setAcctualPosition(MyImageWidth.toFloat() / 2, MyImageHeight.toFloat() / 2)
//                TurtleUI.direction = 0f
//                try {
//                    logo.debug(codeState.text)
//                    img = logo.bitmap
//                } catch (e: Exception) {
//                    Log.e("ERROR", "Błąd wykonywania interpretera")
//                } finally {
//                    withContext(Dispatchers.Main) {
////                        errors.value = SyntaxError.Companion.errors.value
//                        isErrorListVisible = errors.value.isNotEmpty()
//                    }
//                }
//            }
//        }
        viewModelScope.launch(Dispatchers.Default) {
            mutex.withLock {
                clearErrors()
                TurtleUI.setAcctualPosition(MyImageWidth.toFloat() / 2, MyImageHeight.toFloat() / 2)
                TurtleUI.direction = 0f

                val result = logo.debug(codeState.text)

                withContext(Dispatchers.Main) {
                    if (!result.success) {
                        _errors.value = result.errors
                    }
                    img = logo.bitmap
                    isErrorListVisible = errors.value.isNotEmpty()
                }
            }
        }
    }
}