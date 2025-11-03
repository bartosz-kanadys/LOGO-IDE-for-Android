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
import androidx.compose.runtime.LaunchedEffect
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
import com.example.logointerpreterbeta.ui.utils.LogoTextColorizer
import com.example.logointerpreterbeta.ui.screens.interpreter.components.codeEditor.codeSuggestions.CodeSuggestionPopup
import com.example.logointerpreterbeta.ui.screens.interpreter.components.codeEditor.codeSuggestions.SuggestionList
import com.example.logointerpreterbeta.ui.screens.interpreter.components.codeEditor.textFunctions.NearestWordFinder
import com.example.logointerpreterbeta.ui.screens.interpreter.components.codeEditor.textFunctions.replaceAnnotatedSubstring
import com.example.logointerpreterbeta.ui.screens.interpreter.components.codeEditor.textFunctions.textDiffrence
import com.example.logointerpreterbeta.ui.theme.ThemeUtils

@Composable
fun CodeEditor(
    code: String,
    isDarkMode: Boolean,
    currentLine: Int,
    modifier: Modifier = Modifier,
    errors: String = "",
    onCodeChange: (text: String, cursorPosition: Int) -> Unit = { _, _ -> },
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
    val linesCount = code.lines().size
    val scrollState = rememberScrollState()

    var cursorOffset by remember { mutableStateOf(Offset.Zero) }
    var filteredSuggestions by remember { mutableStateOf(emptyList<String>()) }

    var textFieldValue by remember {
        mutableStateOf(
            TextFieldValue(
                annotatedString = LogoTextColorizer.colorizeText(code, isDarkMode),
                selection = TextRange(0)
            )
        )
    }

    LaunchedEffect(code, isDarkMode) {
        if (textFieldValue.text != code) {
            textFieldValue = TextFieldValue(
                annotatedString = LogoTextColorizer.colorizeText(code, isDarkMode),
                // Spróbuj zachować pozycję kursora, jeśli to możliwe
                selection = try {
                    val newStart = textFieldValue.selection.start.coerceIn(0, code.length)
                    val newEnd = textFieldValue.selection.end.coerceIn(0, code.length)
                    TextRange(newStart, newEnd)
                } catch (e: Exception) {
                    TextRange(code.length)
                }
            )
        }
        // Jeśli zmienił się tylko tryb ciemny, przerysuj kolorowanie
        else if (textFieldValue.text == code) {
            textFieldValue = textFieldValue.copy(
                annotatedString = LogoTextColorizer.colorizeText(code, isDarkMode)
            )
        }
    }

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
                    value = textFieldValue,
                    onValueChange = { newValue ->

                        val oldText = textFieldValue.text
                        val newText = newValue.text

                        // Aktualizuj stan lokalny (z optymalizacją kolorowania)
                        textFieldValue = if (oldText != newText) {
                            // 1. Tekst się zmienił: skopiuj `newValue`, ale zastąp jego string naszym pokolorowanym
                            newValue.copy(
                                annotatedString = textDiffrence(
                                    textFieldValue.annotatedString, // Użyj STAREGO pokolorowanego stringu
                                    newText,
                                ) { text ->
                                    LogoTextColorizer.colorizeText(text, isDarkMode)
                                }
                            )
                        } else {
                            // Zachowaj stary, pokolorowany string (`textFieldValue.annotatedString`)
                            // i zaktualizuj tylko selekcję z `newValue`.
                            textFieldValue.copy(
                                selection = newValue.selection
                            )
                        }

                        // Logika sugestii
                        val cursorPosition = newValue.selection.start
                        val wordToMatch = NearestWordFinder.find(newText, cursorPosition)
                        filteredSuggestions = if (wordToMatch.isNotEmpty() && showSuggestions) {
                            SuggestionList.suggestions.filter { it.startsWith(wordToMatch) && it != wordToMatch }
                        } else {
                            emptyList()
                        }

                        // Emituj surowe dane do ViewModelu
                        onCodeChange(newText, cursorPosition)

                        if (isSaveOnChange) {
                            onSave(newText)
                        }
                    },
                    minLines = lines,
                    textStyle = TextStyle(
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = fontSize.sp,
                        fontFamily = ThemeUtils.fontFamilyFromString(fontFamily)
                    ),
                    onTextLayout = { textLayoutResult: TextLayoutResult ->
                        val actualCursorPosition = textFieldValue.selection.start
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
                    // Logika sugestii musi teraz także aktualizować stan lokalny i powiadamiać VM
                    val newAnnotatedString = replaceAnnotatedSubstring(
                        textFieldValue.annotatedString, // Użyj stanu lokalnego
                        NearestWordFinder.nearestSpacePositionToLeft,
                        NearestWordFinder.nearestSpacePositionToRight,
                        suggestion
                    )
                    val newText = newAnnotatedString.text
                    val actualCursorPosition =
                        NearestWordFinder.nearestSpacePositionToLeft + suggestion.length

                    //Zaktualizuj stan lokalny
                    textFieldValue = textFieldValue.copy(
                        annotatedString = newAnnotatedString,
                        selection = TextRange(actualCursorPosition)
                    )

                    onCodeChange(newText, actualCursorPosition)
                }
            }
        }
    }
}
