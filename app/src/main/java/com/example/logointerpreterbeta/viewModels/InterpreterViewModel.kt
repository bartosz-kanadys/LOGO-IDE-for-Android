package com.example.logointerpreterbeta.viewModels

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.logointerpreterbeta.LogoInterpreter
import com.example.logointerpreterbeta.MyImageHeight
import com.example.logointerpreterbeta.MyImageWidth
import com.example.logointerpreterbeta.functions.config.updateLastModifiedProjectJSON
import com.example.logointerpreterbeta.Turtle

import com.example.logointerpreterbeta.errors.SyntaxError
import com.example.logointerpreterbeta.ui.components.codeEditor.textFunctions.textDiffrence

import kotlinx.coroutines.launch

class InterpreterViewModel(context: Context) : ViewModel() {

    private val logo = LogoInterpreter(context)
    var codeState by mutableStateOf(TextFieldValue("\n\n\n\n\n\n\n\n\n\n\n"))
    var cursorPosition by mutableStateOf(0)

    var acctualProjectName by mutableStateOf("-")
    var acctualFileName by mutableStateOf<String?>(null)

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
    }

    fun upadteAcctualProject(context: Context,projectName: String) {
        acctualProjectName = projectName
        updateLastModifiedProjectJSON(context, acctualProjectName)
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

    fun interpretCode(text: String = codeState.text) {
        viewModelScope.launch {
            SyntaxError.errors.clear()
            Turtle.setAcctualPosition(MyImageWidth.toFloat() / 2, MyImageHeight.toFloat() / 2)
            Turtle.direction = 0f
            try {
                logo.start(text+"\n")
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
