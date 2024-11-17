package com.example.logointerpreterbeta.ui.components.codeEditor

import android.annotation.SuppressLint
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.logointerpreterbeta.functions.errors.prepareErrorList
import com.example.logointerpreterbeta.functions.project.writeFileContent
import com.example.logointerpreterbeta.ui.components.codeEditor.codeSuggestions.CodeSuggestionPopup
import com.example.logointerpreterbeta.ui.components.codeEditor.codeSuggestions.SuggestionList
import com.example.logointerpreterbeta.ui.components.codeEditor.textFunctions.NearestWordFinder
import com.example.logointerpreterbeta.ui.components.codeEditor.textFunctions.replaceAnnotatedSubstring
import com.example.logointerpreterbeta.ui.theme.AppTypography
import com.example.logointerpreterbeta.viewModels.InterpreterViewModel
import com.example.logointerpreterbeta.viewModels.ProjectViewModel

@Composable
fun CodeEditor(
    projectViewModel: ProjectViewModel? = null,
    interpreterViewModel: InterpreterViewModel? = null,
    codeState: TextFieldValue,
    errors: String = "",
    onCodeChange: (TextFieldValue) -> Unit = {},
    isSaveOnChange: Boolean = true,
    isEnabled: Boolean = true,
    isScrollable: Boolean = true,
    lines: Int = 10,
    fontSize: Int = 18,
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
                Text(
                    textAlign = TextAlign.Center,
                    text = i.toString(),
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = fontSize.sp,
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
            Box(modifier = modifier.fillMaxSize()) {
                BasicTextField(
                    enabled = isEnabled,
                    value = codeState,
                    onValueChange = { newValue ->
                        onCodeChange(newValue)
                        cursorPosition = newValue.selection.start
                        val wordToMatch = NearestWordFinder.find(newValue.text, cursorPosition)
                        Log.i("wordToMatch", wordToMatch)
                        filteredSuggestions = if (wordToMatch.isNotEmpty()) {
                            SuggestionList.suggestions.filter { it.startsWith(wordToMatch) && it != wordToMatch }
                        } else {
                            emptyList()
                        }
                        onCodeChange(newValue)
                        if (isSaveOnChange) {
                            writeFileContent(
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
                        fontFamily = AppTypography.bodySmall.fontFamily,
                    ),
                    onTextLayout = { textLayoutResult: TextLayoutResult ->
                        val actualCursorPosition = codeState.selection.start
                        if (actualCursorPosition >= 0) {
                            val cursorRect = textLayoutResult.getCursorRect(actualCursorPosition)
                            cursorOffset = Offset(cursorRect.left, cursorRect.bottom)
                        }
                    },
                    modifier = modifier
                        .fillMaxSize()
                        .then(if (isScrollable) Modifier.verticalScroll(scrollState) else Modifier)
                        .background(MaterialTheme.colorScheme.surfaceContainer)
                        .padding(top = 2.dp, start = 10.dp, end = 10.dp)

                )
            }
            if (filteredSuggestions.isNotEmpty()) {
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
