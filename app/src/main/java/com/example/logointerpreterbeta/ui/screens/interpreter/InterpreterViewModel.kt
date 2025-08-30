package com.example.logointerpreterbeta.ui.screens.interpreter

import android.graphics.Bitmap
import android.graphics.Bitmap.createBitmap
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.logointerpreterbeta.domain.interpreter.LogoInterpreter
import com.example.logointerpreterbeta.domain.interpreter.LogoTextColorizer
import com.example.logointerpreterbeta.ui.models.TurtleUI
import com.example.logointerpreterbeta.ui.MyImageHeight
import com.example.logointerpreterbeta.ui.MyImageWidth
import com.example.logointerpreterbeta.ui.drawing.toBitmap
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
    private val logo: LogoInterpreter
) : ViewModel() {

    private var codeState by mutableStateOf(TextFieldValue("\n\n\n\n\n\n\n\n\n\n\n"))
    private var cursorPosition by mutableIntStateOf(0)

    var isErrorListExpanded by mutableStateOf(false)
        private set

    private val _isDebugging = MutableStateFlow(false)
    var isDebugging = _isDebugging.asStateFlow()

    private var _img = MutableStateFlow(createBitmap(1000, 1000, Bitmap.Config.ARGB_8888))
    val img: StateFlow<Bitmap> = _img.asStateFlow()

    private var _arrowImg = MutableStateFlow(createBitmap(1000, 1000, Bitmap.Config.ALPHA_8))
    val arrowImg: StateFlow<Bitmap> = _arrowImg.asStateFlow()

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
            ) { text ->
                LogoTextColorizer.colorizeText(text)
            }
        )
    }

    fun toggleErrorListVisibility() {
        isErrorListExpanded = !isErrorListExpanded
    }

    fun colorCode() {
        codeState = codeState.copy(
            annotatedString = LogoTextColorizer.colorizeText(codeState.text)
        )
    }

    fun nextStep() {
        logo.nextStep()
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

    fun continueExecution() {
        logo.continueExecution()
    }

    fun stepIn() {
        logo.stepIn()
    }

    fun stepOut() {
        logo.stepOut()
    }

    fun clearErrors() {
        _errors.value = emptyList()
    }

    fun interpretCode(text: String = codeState.text) {
        viewModelScope.launch {
            clearErrors()
            TurtleUI.setAcctualPosition(MyImageWidth.toFloat() / 2, MyImageHeight.toFloat() / 2)
            TurtleUI.direction = 0f

            val result = logo.interpret(text)

            if (!result.success) {
                _errors.value = result.errors
            }
            if (result.image != null) {
                _img.update {
                  result.image.toBitmap()
                }
                _arrowImg.update {
                    result.arrowImage?.toBitmap() ?: createBitmap(1000, 1000, Bitmap.Config.ALPHA_8)
                }
            }
        }
    }

    private fun debugCode() {
        viewModelScope.launch(Dispatchers.Default) {
            Mutex().withLock {
                clearErrors()
                TurtleUI.setAcctualPosition(MyImageWidth.toFloat() / 2, MyImageHeight.toFloat() / 2)
                TurtleUI.direction = 0f

                val result = logo.debug(codeState.text) { imageResult, arrowResult ->
                    _img.value = imageResult.toBitmap()
                    _arrowImg.value = arrowResult.toBitmap()
                }

                withContext(Dispatchers.Main) {
                    if (!result.success) {
                        _errors.value = result.errors
                    }
                    if (result.image != null) {
                        _img.update {
                            result.image.toBitmap()
                        }
                        _arrowImg.update {
                            result.arrowImage?.toBitmap() ?: createBitmap(1000, 1000, Bitmap.Config.ALPHA_8)
                        }
                    }
                }
            }
        }
    }



//    fun getImage(): Bitmap {
//        logo.g
//    }
}