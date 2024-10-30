package com.example.logointerpreterbeta.activities

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.logointerpreterbeta.LogoInterpreter
import com.example.logointerpreterbeta.MyImageHeight
import com.example.logointerpreterbeta.MyImageWidth
import com.example.logointerpreterbeta.Turtle
import com.example.logointerpreterbeta.components.CodeEditor.colorizeText
import com.example.logointerpreterbeta.errors.SyntaxError
import kotlinx.coroutines.launch

class InterpreterViewModel(context: Context) : ViewModel() {

    private val logo = LogoInterpreter(context)

    var codeState by mutableStateOf(TextFieldValue("\n\n\n\n\n\n\n\n\n\n\n"))

    var img by mutableStateOf(Bitmap.createBitmap(2000, 2000, Bitmap.Config.ARGB_8888))
        private set

    var errors by mutableStateOf(SyntaxError.errors.toString())
        private set

    var isErrorListVisable by mutableStateOf(false)
        private set

    var isErrorListExpanded by mutableStateOf(false)
        private set

    init {
        // Ustawienia początkowe pozycji i kierunku
        Turtle.setAcctualPosition(MyImageWidth.toFloat() / 2, MyImageHeight.toFloat() / 2)
        Turtle.direction = 0f
        logo.start("st")
        img = logo.bitmap
    }

    fun onCodeChange(newCode: TextFieldValue) {
        var wordColors = mapOf(
            "fd" to Color.Magenta,
            "rt" to Color.Blue,
            "ld" to Color.Green
        )
        codeState = newCode.copy(
            annotatedString = colorizeText(newCode.text, wordColors)
        )
    }

    fun toggleErrorListVisibility() {
        isErrorListExpanded = !isErrorListExpanded
    }

    fun interpretCode() {
        viewModelScope.launch {
            SyntaxError.errors.clear()
            Turtle.setAcctualPosition(MyImageWidth.toFloat() / 2, MyImageHeight.toFloat() / 2)
            Turtle.direction = 0f
            try {
                logo.start(codeState.text)
                img = logo.bitmap
            } catch (e: Exception) {
                Log.e("ERROR", "Błąd wykonywania interpretera")
            } finally {
                errors = SyntaxError.errors.toString()
                isErrorListVisable = errors != "[]"
            }
        }
    }
}
