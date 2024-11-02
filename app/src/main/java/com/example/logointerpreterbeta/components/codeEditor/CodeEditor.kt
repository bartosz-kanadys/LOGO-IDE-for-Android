package com.example.logointerpreterbeta.components.codeEditor

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import com.example.logointerpreterbeta.activities.prepareErrorList
import com.example.logointerpreterbeta.components.codeEditor.codeSuggestions.CodeSuggestionPopup
import com.example.logointerpreterbeta.components.codeEditor.textFunctions.NearestWordFinder
import com.example.logointerpreterbeta.ui.theme.jetBrainsMono
import kotlin.math.roundToInt

@Composable
fun CodeEditor(codeState: TextFieldValue, errors: String, onCodeChange: (TextFieldValue) -> Unit, modifier: Modifier) {
    val linesCount = codeState.text.lines().size
    val scrollState = rememberScrollState()
    val errorsList = if (errors.isNotEmpty()) {
        errors.removeSurrounding("[", "]")
            .split(",")
            .toMutableList()
    } else mutableListOf(":)")
    val errorMap = prepareErrorList(errorsList)
    val suggestions = listOf("String", "struct", "stream", "strategy", "structure","amogus","aString")
    var cursorOffset by remember { mutableStateOf(Offset.Zero) }
    var suggestedInstruction by remember { mutableStateOf("") }
    var filteredSuggestions by remember { mutableStateOf(emptyList<String>()) }
    var cursorPosition by remember {mutableStateOf(0)}
    Row(
        modifier = modifier
            .height(300.dp)
            .fillMaxWidth()
            .shadow(18.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxHeight()
                .verticalScroll(scrollState)
                .width(33.dp)
                .background(Color(0xFF4CAF50))
                .padding(vertical = 10.dp)

        ) {
            for (i in 1..linesCount) {
                Text(
                    textAlign = TextAlign.Center,
                    text = i.toString(),
                    color = Color(0xFF212121),
                    fontSize = 18.sp,
                    style = TextStyle(
                        platformStyle = PlatformTextStyle(includeFontPadding = false),
                        fontFamily = jetBrainsMono
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(if (i in errorMap) Color(0xFFFF5252) else Color(0xFF4CAF50))
                )
            }
        }
        Column {
            // Pole tekstowe
            Box(modifier = Modifier.fillMaxSize()) {
                BasicTextField(
                    value = codeState,
                    onValueChange = { newValue -> onCodeChange(newValue)
                            cursorPosition = newValue.selection.start
                            val wordToMatch = NearestWordFinder.find(newValue.text, cursorPosition)
                            Log.i("wordToMatch",wordToMatch)
                            filteredSuggestions= suggestions.filter {it.startsWith(wordToMatch) && it!=wordToMatch}
                                    },
                    minLines = 10,
                    textStyle = TextStyle(
                        fontSize = 18.sp,
                        fontFamily = jetBrainsMono
                    ),
                    modifier = Modifier
                        .fillMaxSize()

                        .verticalScroll(scrollState)
                        .background(Color.White)
                        .padding(horizontal = 10.dp, vertical = 10.dp),
                    onTextLayout = { textLayoutResult: TextLayoutResult ->
                        val cursorPosition = codeState.selection.start
                        if (cursorPosition >= 0) {
                            val cursorRect = textLayoutResult.getCursorRect(cursorPosition)
                            cursorOffset = Offset(cursorRect.left, cursorRect.bottom)
                        }
                    }
                )
            }
            if(filteredSuggestions.isNotEmpty()) {
                CodeSuggestionPopup(filteredSuggestions, cursorOffset) { suggestion: String ->
                    suggestedInstruction = suggestion
                    // onCodeChange(TextFieldValue(suggestion))
                }
            }
        }
    }
}
