package com.example.logointerpreterbeta.viewModels

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.logointerpreterbeta.LogoInterpreter
import com.example.logointerpreterbeta.MyImageHeight
import com.example.logointerpreterbeta.MyImageWidth
import com.example.logointerpreterbeta.Turtle
import com.example.logointerpreterbeta.components.codeEditor.textFunctions.textDiffrence
import com.example.logointerpreterbeta.errors.SyntaxError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class InterpreterViewModel(context: Context) : ViewModel() {

    private val logo = LogoInterpreter(context, viewModelScope)
    var codeState by mutableStateOf(TextFieldValue("\n\n\n\n\n\n\n\n\n\n\n"))
    var cursorPosition by mutableStateOf(0)

    var img by mutableStateOf(Bitmap.createBitmap(2000, 2000, Bitmap.Config.ARGB_8888))
        private set

    var errors = MutableStateFlow(SyntaxError.errors.value)
        private set

    var isErrorListVisable by mutableStateOf(false)
        private set

    var isErrorListExpanded by mutableStateOf(false)
        private set
    var isDebugging by mutableStateOf(false)

    init {
        // Ustawienia początkowe pozycji i kierunku
        Turtle.setAcctualPosition(MyImageWidth.toFloat() / 2, MyImageHeight.toFloat() / 2)
        Turtle.direction = 0f
        logo.start("st")
    }

    fun onCodeChange(newCode: TextFieldValue) {
        cursorPosition = newCode.selection.start
        codeState = newCode.copy(
            annotatedString = textDiffrence(codeState.annotatedString, newCode.text,logo::colorizeText)
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
    fun nextStep(){
        logo.nextStep()
    }
    fun enableDebugging(){
        isDebugging=true
        logo.enableDebugging()
    }
    fun disableDebugging(){
        isDebugging=false
        logo.disableDebugging()
    }
    fun interpretCode() {
        viewModelScope.launch(Dispatchers.Default) {
            SyntaxError.clearErrors()
            Turtle.setAcctualPosition(MyImageWidth.toFloat() / 2, MyImageHeight.toFloat() / 2)
            Turtle.direction = 0f
            try {
                logo.start(codeState.text,isDebugging)
                img = logo.bitmap
            } catch (e: Exception) {
                Log.e("ERROR", "Błąd wykonywania interpretera")
            } finally {
                withContext(Dispatchers.Main) {
                    errors.value = SyntaxError.errors.value
                    isErrorListVisable = !errors.value.isEmpty()
                }
//
            }
        }
    }
}
