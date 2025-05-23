package com.example.logointerpreterbeta.ui.components.codeEditor.codeSuggestions

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import com.example.logointerpreterbeta.ui.theme.LogoInterpreterBetaTheme
import kotlin.math.roundToInt

@Composable
fun CodeSuggestionPopup(
    suggestions: List<String>,
    cursorOffset: Offset,
    onSuggestionClick: (String) -> Unit
) {
    Popup(
        alignment = Alignment.TopStart,
        offset = IntOffset(
            x = cursorOffset.x.roundToInt(),
            y = cursorOffset.y.roundToInt() + 15
        )
    ) {
        // Ustal maksymalną szerokość dla Popup
        LazyColumn(
            modifier = Modifier
                .background(
                    MaterialTheme.colorScheme.primaryContainer,
                    shape = RoundedCornerShape(10.dp)
                )
                .widthIn(min = 100.dp, max = 200.dp)
                .heightIn(max = 200.dp) // Ustal maksymalną wysokość, bez min
                .padding(3.dp)
        ) {
            items(suggestions) { suggestion ->
                Box(modifier = Modifier.padding(bottom = 2.dp)) {
                    Text(
                        text = suggestion,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier
                            .background(
                                MaterialTheme.colorScheme.inversePrimary,
                                shape = RoundedCornerShape(7.dp)
                            )
                            .fillMaxWidth()
                            .clickable {
                                onSuggestionClick(suggestion)
                            }
                            .padding(4.dp) // Padding dla każdego elementu
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun AA() {
    LogoInterpreterBetaTheme(darkTheme = false) {
        CodeSuggestionPopup(suggestions = listOf("fd", "forward"), cursorOffset = Offset.Zero) {
        }
    }
}

@Preview
@Composable
fun BB() {
    LogoInterpreterBetaTheme(darkTheme = true) {
        CodeSuggestionPopup(suggestions = listOf("fd", "forward"), cursorOffset = Offset.Zero) {
        }
    }
}


