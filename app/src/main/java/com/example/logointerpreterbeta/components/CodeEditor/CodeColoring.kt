package com.example.logointerpreterbeta.components.CodeEditor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.withStyle

class CodeColoring : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                ColorfulTextField(
                    wordColors = mapOf(
                        "kot" to Color.Magenta,
                        "pies" to Color.Blue,
                        "ptak" to Color.Green
                    )
                )
            }
        }
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit) {
    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            content()
        }
    }
}

@Composable
fun ColorfulTextField(wordColors: Map<String, Color>) {
    var textFieldValue by remember { mutableStateOf(TextFieldValue("")) }

    TextField(
        value = textFieldValue,
        onValueChange = { newValue ->
            textFieldValue = newValue.copy(
                annotatedString = colorizeText(newValue.text, wordColors)
            )
        },
        textStyle = MaterialTheme.typography.bodyLarge
    )
}

fun colorizeText(text: String, wordColors: Map<String, Color>): AnnotatedString {
    return buildAnnotatedString {
        var currentIndex = 0

        // Dla każdego słowa, które ma być pokolorowane
        for ((word, color) in wordColors) {
            var startIndex = text.indexOf(word, currentIndex)

            // Szukanie i kolorowanie wszystkich wystąpień danego słowa
            while (startIndex != -1) {
                // Dodajemy tekst przed słowem
                append(text.substring(currentIndex, startIndex))

                // Kolorujemy słowo
                withStyle(style = SpanStyle(color = color)) {
                    append(word)
                }

                // Aktualizujemy indeksy
                currentIndex = startIndex + word.length
                startIndex = text.indexOf(word, currentIndex)
            }
        }

        // Dodajemy pozostały tekst po ostatnim kolorowanym słowie
        if (currentIndex < text.length) {
            append(text.substring(currentIndex))
        }
    }
}