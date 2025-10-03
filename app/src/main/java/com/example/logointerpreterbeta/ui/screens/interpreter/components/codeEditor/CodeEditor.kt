package com.example.logointerpreterbeta.ui.screens.interpreter.components.codeEditor

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.logointerpreterbeta.ui.screens.interpreter.components.codeEditor.codeSuggestions.CodeSuggestionPopup
import com.example.logointerpreterbeta.ui.screens.interpreter.components.codeEditor.codeSuggestions.SuggestionList
import com.example.logointerpreterbeta.ui.screens.interpreter.components.codeEditor.textFunctions.NearestWordFinder
import com.example.logointerpreterbeta.ui.screens.interpreter.components.codeEditor.textFunctions.replaceAnnotatedSubstring
import com.example.logointerpreterbeta.ui.theme.ThemeUtils

@Composable
fun CodeEditor(
    codeState: TextFieldValue,
    currentLine: Int,
    modifier: Modifier = Modifier,
    errors: String = "",
    onCodeChange: (TextFieldValue) -> Unit = {},
    isSaveOnChange: Boolean = true,
    lines: Int = 10,
    fontSize: Int = 18,
    fontFamily: String = "JetBrains Mono",
    breakpoints: List<Int>,
    useAutocorrect: Boolean = false,
    showSuggestions: Boolean = true,
    onSave: (String) -> Unit = {},
    onToggleBreakpoint: (Int) -> Unit,
) {
    val linesCount = codeState.text.lines().size
    val scrollState = rememberScrollState()

    var cursorOffset by remember { mutableStateOf(Offset.Zero) }
    var filteredSuggestions by remember { mutableStateOf(emptyList<String>()) }
    var cursorPosition by remember { mutableIntStateOf(0) }

    Row(
        modifier = modifier
            .height(350.dp)
            .fillMaxWidth()
            .shadow(18.dp)
    ) {
        EditorLines(
            scrollState = scrollState,
            linesCount = linesCount,
            breakpoints = breakpoints,
            currentLine = currentLine,
            fontSize = fontSize,
            fontFamily = fontFamily,
            errors = errors,
        ) {
            onToggleBreakpoint(it)
        }
        Column {
            Box(modifier = modifier.fillMaxSize()) {
                BasicTextField(
                    value = codeState,
                    onValueChange = { newValue ->
                        cursorPosition = newValue.selection.start
                        val wordToMatch = NearestWordFinder.find(newValue.text, cursorPosition)
                        filteredSuggestions = if (wordToMatch.isNotEmpty()) {
                            SuggestionList.suggestions.filter { it.startsWith(wordToMatch) && it != wordToMatch }
                        } else { emptyList() }
                        onCodeChange(newValue)
                        if (isSaveOnChange) {
                            onSave(newValue.text)
                        }
                    },
                    minLines = lines,
                    textStyle = TextStyle(
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = fontSize.sp,
                        fontFamily = ThemeUtils.fontFamilyFromString(fontFamily)
                    ),
                    onTextLayout = { textLayoutResult: TextLayoutResult ->
                        val actualCursorPosition = codeState.selection.start
                        if (actualCursorPosition >= 0) {
                            val cursorRect = textLayoutResult.getCursorRect(actualCursorPosition)
                            cursorOffset = Offset(cursorRect.left, cursorRect.bottom)
                        }
                    },
                    cursorBrush = SolidColor(MaterialTheme.colorScheme.onSurface),
                    keyboardOptions = KeyboardOptions(
                        autoCorrectEnabled = useAutocorrect,
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Default
                    ),
                    modifier = modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState)
                        .background(MaterialTheme.colorScheme.surfaceContainer)
                        .padding(top = 2.dp, start = 10.dp, end = 10.dp)
                )
            }
            if (filteredSuggestions.isNotEmpty() && showSuggestions) {
                CodeSuggestionPopup(filteredSuggestions, cursorOffset) { suggestion: String ->
                    val newText = replaceAnnotatedSubstring(
                        codeState.annotatedString,
                        NearestWordFinder.nearestSpacePositionToLeft,
                        NearestWordFinder.nearestSpacePositionToRight,
                        suggestion
                    )
                    val actualCursorPosition =
                        NearestWordFinder.nearestSpacePositionToLeft + suggestion.length

                    onCodeChange(
                        codeState.copy(
                            annotatedString = newText,
                            selection = TextRange(actualCursorPosition)
                        )
                    )
                }
            }
        }
    }
}
