package com.example.logointerpreterbeta.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.layout
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.logointerpreterbeta.activities.prepareErrorList
import com.example.logointerpreterbeta.ui.theme.AppTypography

@Composable
fun CodeEditor(codeState: String, errors: String, onCodeChange: (String) -> Unit, modifier: Modifier) {
    val linesCount = codeState.lines().size
    val scrollState = rememberScrollState()
    val errorsList = if (errors.isNotEmpty()) {
        errors.removeSurrounding("[", "]")
            .split(",")
            .toMutableList()
    } else mutableListOf(":)")
    val errorMap = prepareErrorList(errorsList)

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
                .background(MaterialTheme.colorScheme.surface)
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
                        .background(if (i in errorMap) MaterialTheme.colorScheme.errorContainer else MaterialTheme.colorScheme.surface)
                )
            }
        }

        // Pole tekstowe
        Box(modifier = Modifier
            .fillMaxSize()
            .background(Color.Red)
        ) {
            TextField(
                value = codeState,
                onValueChange = {newValue -> onCodeChange(newValue) },
                minLines = 10,
                textStyle = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = AppTypography.bodySmall.fontFamily,
                ),
                shape = RoundedCornerShape(0.dp),
                modifier = Modifier
                    .background(Color.Red, shape = RectangleShape)
                    .fillMaxSize()
                    .padding(0.dp)
                    .verticalScroll(scrollState)
                    .background(Color.White)

                  //  .padding(horizontal = 10.dp, vertical = 10.dp)

            )
        }
    }
}

@Preview
@Composable
fun aa() {
    CodeEditor(codeState = "", errors = "", onCodeChange = {}, modifier = Modifier)
}