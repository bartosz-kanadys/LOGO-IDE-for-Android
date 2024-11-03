package com.example.logointerpreterbeta.components.codeEditor

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.logointerpreterbeta.components.codeEditor.codeSuggestions.CodeSuggestionPopup
import com.example.logointerpreterbeta.components.codeEditor.codeSuggestions.SuggestionList
import com.example.logointerpreterbeta.components.codeEditor.textFunctions.NearestWordFinder
import com.example.logointerpreterbeta.components.codeEditor.textFunctions.replaceAnnotatedSubstring
import com.example.logointerpreterbeta.functions.prepareErrorList
import com.example.logointerpreterbeta.ui.theme.AppTypography

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
    var cursorOffset by remember { mutableStateOf(Offset.Zero) }
    var suggestedInstruction by remember { mutableStateOf("") }
    var filteredSuggestions by remember { mutableStateOf(emptyList<String>()) }
    var cursorPosition by remember { mutableIntStateOf(0) }
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
                .background(MaterialTheme.colorScheme.inversePrimary)
                .padding(top = 15.dp)

        ) {
            for (i in 1..linesCount) {
                Text(
                    textAlign = TextAlign.Center,
                    text = i.toString(),
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 18.sp,
                    style = TextStyle(
                        platformStyle = PlatformTextStyle(includeFontPadding = false),
                        fontFamily = AppTypography.bodySmall.fontFamily
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(if (i in errorMap) MaterialTheme.colorScheme.errorContainer else MaterialTheme.colorScheme.inversePrimary)
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
                            filteredSuggestions = if(wordToMatch.isNotEmpty()){
                                SuggestionList.suggestions.filter {it.startsWith(wordToMatch) && it!=wordToMatch}
                            } else{
                                emptyList()
                            }
                                onCodeChange(newValue)
                                },
                    minLines = 10,
                    textStyle = TextStyle(
                        fontSize = 18.sp,
                        fontFamily = AppTypography.bodySmall.fontFamily,
                    ),
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState)
                        .background(MaterialTheme.colorScheme.surfaceContainer)
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
                    val newText = replaceAnnotatedSubstring(
                        codeState.annotatedString,
                        NearestWordFinder.nearestSpacePositionToLeft,
                        NearestWordFinder.nearestSpacePositionToRight,
                        suggestion
                    )
                    val cursorPosition = NearestWordFinder.nearestSpacePositionToLeft + suggestion.length

                    onCodeChange(
                        codeState.copy(
                            annotatedString = newText,
                            selection = TextRange(cursorPosition)
                        )
                    )
                }
            }
        }
    }
}
