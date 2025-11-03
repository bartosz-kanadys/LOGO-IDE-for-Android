package com.example.logointerpreterbeta.ui.screens.interpreter.components.codeEditor

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.logointerpreterbeta.ui.theme.ThemeUtils

@Composable
fun DisplayOnlyCodeEditor(
    codeState: TextFieldValue,
    modifier: Modifier = Modifier,
    fontSize: Int = 18,
    fontFamily: String = "JetBrains Mono",
) {
    val linesCount = codeState.text.lines().size
    val editorHeight = (30 + (linesCount - 1) * 20).dp

    Row(
        modifier = modifier
            .height(editorHeight)
            .fillMaxWidth()
            .shadow(18.dp)
    ) {
        EditorLines(
            linesCount = linesCount,
            breakpoints = emptyList(),
            currentLine = 0,
            fontSize = fontSize,
            fontFamily = fontFamily,
            errors = "",
        ) { }
        Column {
            Box(modifier = modifier.fillMaxSize()) {
                BasicTextField(
                    enabled = false,
                    value = codeState,
                    onValueChange = { },
                    minLines = linesCount,
                    textStyle = TextStyle(
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = fontSize.sp,
                        fontFamily = ThemeUtils.fontFamilyFromString(fontFamily)
                    ),
                    modifier = modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.surfaceContainer)
                        .padding(top = 2.dp, start = 10.dp, end = 10.dp)
                )
            }
        }
    }
}
