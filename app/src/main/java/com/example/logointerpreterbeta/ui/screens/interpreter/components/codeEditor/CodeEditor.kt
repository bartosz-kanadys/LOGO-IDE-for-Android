package com.example.logointerpreterbeta.ui.screens.interpreter.components.codeEditor

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.logointerpreterbeta.domain.errors.prepareErrorList
import com.example.logointerpreterbeta.data.repository.FileRepositoryImpl
import com.example.logointerpreterbeta.ui.screens.interpreter.components.codeEditor.codeSuggestions.CodeSuggestionPopup
import com.example.logointerpreterbeta.ui.screens.interpreter.components.codeEditor.codeSuggestions.SuggestionList
import com.example.logointerpreterbeta.ui.screens.interpreter.components.codeEditor.textFunctions.NearestWordFinder
import com.example.logointerpreterbeta.ui.screens.interpreter.components.codeEditor.textFunctions.replaceAnnotatedSubstring
import com.example.logointerpreterbeta.ui.screens.interpreter.InterpreterViewModel
import com.example.logointerpreterbeta.ui.screens.projects.ProjectViewModel
import com.example.logointerpreterbeta.ui.screens.settings.SettingsViewModel
import com.example.logointerpreterbeta.domain.visitors.DebuggerVisitor
import com.example.logointerpreterbeta.domain.visitors.DebuggerVisitor.Companion.toggleBreakpoint

@Composable
fun CodeEditor(
    projectViewModel: ProjectViewModel? = null,
    interpreterViewModel: InterpreterViewModel? = null,
    fileRepository: FileRepositoryImpl? = null,
    codeState: TextFieldValue,
    errors: String = "",
    onCodeChange: (TextFieldValue) -> Unit = {},
    isSaveOnChange: Boolean = true,
    isEnabled: Boolean = true,
    isScrollable: Boolean = true,
    lines: Int = 10,
    fontSize: Int = SettingsViewModel.currentFontSize,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier
) {
    val linesCount = codeState.text.lines().size
    val scrollState = rememberScrollState()
    val errorsList = if (errors.isNotEmpty()) {
        errors.removeSurrounding("[", "]")
            .split(",")
            .toMutableList()
    } else mutableListOf(":)")
    val errorMap = prepareErrorList(errorsList)
    var cursorOffset by remember { mutableStateOf(Offset.Zero) }
    var filteredSuggestions by remember { mutableStateOf(emptyList<String>()) }
    var cursorPosition by remember { mutableIntStateOf(0) }
    val context = LocalContext.current
    val interactionSource = remember { MutableInteractionSource() }
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
                .padding(top = 2.dp)

        ) {
            for (i in 1..linesCount) {
                if (i in DebuggerVisitor.breakpoints) {
                    // Jeśli numer linii jest w breakpointach, wyświetl czerwone koło
                    Box(
                        modifier = Modifier
                            .fillMaxSize() // Ustal wymiary tła
                            .background(
                                when (i) {
                                    in errorMap -> MaterialTheme.colorScheme.errorContainer
                                    DebuggerVisitor.currentLine -> MaterialTheme.colorScheme.secondary
                                    else -> MaterialTheme.colorScheme.inversePrimary
                                }
                            )
                    ) {
                        Box(
                            modifier = Modifier
                                .size((fontSize*1.4).dp) // Ustal rozmiar koła
                                .background(
                                    MaterialTheme.colorScheme.errorContainer,
                                    shape = CircleShape
                                ) // Czerwone koło
                                .align(Alignment.Center)
                                .clickable(
                                    interactionSource = interactionSource,
                                    indication = null
                                ) {
                                    toggleBreakpoint(i) // Przełącz breakpoint
                                    //DebuggerVisitor.breakpoints=i
                                },

                            )
                    }
                } else {
                    // W przeciwnym razie wyświetl numer linii
                    Text(
                        textAlign = TextAlign.Center,
                        text = i.toString(),
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = fontSize.sp,
                        style = TextStyle(
                            platformStyle = PlatformTextStyle(includeFontPadding = false),
                            fontFamily = SettingsViewModel.currentFont.bodySmall.fontFamily
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                when (i) {
                                    in errorMap -> MaterialTheme.colorScheme.errorContainer
                                    DebuggerVisitor.currentLine -> MaterialTheme.colorScheme.secondary
                                    else -> MaterialTheme.colorScheme.inversePrimary
                                }
                            )
                            .clickable(
                                interactionSource = interactionSource,
                                indication = null
                            ) {
                                toggleBreakpoint(i) // Przełącz breakpoint
                            }
                    )
                }
            }
        }
        Column {
            Box(modifier = modifier.fillMaxSize()) {
                BasicTextField(
                    enabled = isEnabled,
                    value = codeState,
                    onValueChange = { newValue ->
                        onCodeChange(newValue)
                        cursorPosition = newValue.selection.start
                        val wordToMatch = NearestWordFinder.find(newValue.text, cursorPosition)
                        filteredSuggestions = if (wordToMatch.isNotEmpty()) {
                            SuggestionList.suggestions.filter { it.startsWith(wordToMatch) && it != wordToMatch }
                        } else { emptyList() }
                        onCodeChange(newValue)
                        if (isSaveOnChange) {
                            fileRepository!!.writeFileContent(
                                context,
                                projectViewModel!!.actualFileName.value!!,
                                projectViewModel.actualProjectName.value,
                                interpreterViewModel!!.getCodeStateAsString()
                            )
                        }
                    },
                    minLines = lines,
                    textStyle = TextStyle(
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = fontSize.sp,
                        fontFamily = SettingsViewModel.currentFont.bodySmall.fontFamily
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
                        autoCorrectEnabled = SettingsViewModel.useAutocorrect,
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Default
                    ),
                    modifier = modifier
                        .fillMaxSize()
                        .then(if (isScrollable) Modifier.verticalScroll(scrollState) else Modifier)
                        .background(MaterialTheme.colorScheme.surfaceContainer)
                        .padding(top = 2.dp, start = 10.dp, end = 10.dp)
                )
            }
            if (filteredSuggestions.isNotEmpty()&&SettingsViewModel.showSuggestions) {
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

@Preview
@Composable
fun AA() {
    CodeEditor(
        projectViewModel = hiltViewModel(),
        interpreterViewModel = hiltViewModel(),
        codeState = TextFieldValue("t\n\n\n\n\n\n\n\n papap"),
        errors = "",
        onCodeChange = { },
        isSaveOnChange = true,
        modifier = Modifier
    )
}
