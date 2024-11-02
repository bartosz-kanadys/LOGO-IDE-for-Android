package com.example.logointerpreterbeta.components.codeEditor.codeSuggestions

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import kotlin.math.roundToInt

@Composable
fun CodeSuggestionPopup(
    suggestions:List<String>,
    cursorOffset: Offset,
    onSuggestionClick: (String) -> Unit
) {
    Popup(
        alignment = Alignment.TopStart,
        offset = IntOffset(
            x = cursorOffset.x.roundToInt(),
            y = cursorOffset.y.roundToInt() + 15
        ),
        //properties = PopupProperties(focusable = false)
    ) {
        Column(
            modifier = Modifier
                .background(Color.White)
                .widthIn(min = 100.dp, max = 200.dp) // Ograniczenie szerokości Popup
                .padding(horizontal = 8.dp)
        ) {
            suggestions.forEach { suggestion ->
                Text(
                    text = suggestion,
                    color = Color.Black,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onSuggestionClick(suggestion)
                        }
                        .padding(8.dp)
                )
            }
        }

    }
}
fun CodeSugestions(){//
    // Lista dostępnych podpowiedzi
  val suggestions = listOf("String", "struct", "stream", "strategy", "structure")
//
//    // Stan do przechowywania tekstu i sugerowanych opcji
//    var textState by mutableStateOf(TextFieldValue(""))
//    var filteredSuggestions by   mutableStateOf(emptyList<String>())
    // Wyświetlanie sugestii w formie rozwijanej listy
    //if (filteredSuggestions.isNotEmpty()) {
}