package com.example.logointerpreterbeta.components.codeEditor

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle



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