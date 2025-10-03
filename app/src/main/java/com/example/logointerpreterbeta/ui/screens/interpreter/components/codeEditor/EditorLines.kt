package com.example.logointerpreterbeta.ui.screens.interpreter.components.codeEditor

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.logointerpreterbeta.domain.interpreter.errors.prepareErrorList
import com.example.logointerpreterbeta.ui.theme.ThemeUtils

@Composable
fun EditorLines(
    scrollState: ScrollState? = null,
    linesCount: Int,
    breakpoints: List<Int>,
    currentLine: Int,
    fontSize: Int,
    fontFamily: String,
    errors: String,
    onToggleBreakpoint: (Int) -> Unit,
) {
    val errorsList = if (errors.isNotEmpty()) {
        errors.removeSurrounding("[", "]")
            .split(",")
            .toMutableList()
    } else mutableListOf(":)")
    val errorMap = prepareErrorList(errorsList)

    val interactionSource = remember { MutableInteractionSource() }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxHeight()
            .width(33.dp)
            .background(MaterialTheme.colorScheme.inversePrimary)
            .padding(top = 2.dp)
            .then(if (scrollState != null) Modifier.verticalScroll(scrollState) else Modifier)
    ) {
        for (lineNumber in 1..linesCount) {
            if (lineNumber in breakpoints) {
                // Jeśli numer linii jest w breakpointach, wyświetl czerwone koło
                Box(
                    modifier = Modifier
                        .fillMaxSize() // Ustal wymiary tła
                        .background(
                            when (lineNumber) {
                                in errorMap -> MaterialTheme.colorScheme.errorContainer
                                currentLine -> MaterialTheme.colorScheme.secondary
                                else -> MaterialTheme.colorScheme.inversePrimary
                            }
                        )
                ) {
                    Box(
                        modifier = Modifier
                            .size((fontSize * 1.4).dp) // Ustal rozmiar koła
                            .background(
                                MaterialTheme.colorScheme.errorContainer,
                                shape = CircleShape
                            ) // Czerwone koło
                            .align(Alignment.Center)
                            .clickable(
                                interactionSource = interactionSource,
                                indication = null
                            ) {
                                onToggleBreakpoint(lineNumber)
                            }
                    )
                }
            } else {
                // W przeciwnym razie wyświetl numer linii
                Text(
                    textAlign = TextAlign.Center,
                    text = lineNumber.toString(),
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = fontSize.sp,
                    style = TextStyle(
                        platformStyle = PlatformTextStyle(includeFontPadding = false),
                        fontFamily = ThemeUtils.fontFamilyFromString(fontFamily)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            when (lineNumber) {
                                in errorMap -> MaterialTheme.colorScheme.errorContainer
                                currentLine -> MaterialTheme.colorScheme.secondary
                                else -> MaterialTheme.colorScheme.inversePrimary
                            }
                        )
                        .clickable(
                            interactionSource = interactionSource,
                            indication = null
                        ) {
                            onToggleBreakpoint(lineNumber)
                        }
                )
            }
        }
    }
}