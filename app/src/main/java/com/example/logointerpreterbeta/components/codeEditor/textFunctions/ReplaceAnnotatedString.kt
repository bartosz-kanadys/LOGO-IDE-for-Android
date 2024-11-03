package com.example.logointerpreterbeta.components.codeEditor.textFunctions

import androidx.compose.ui.text.AnnotatedString

fun replaceAnnotatedSubstring(
    original: AnnotatedString,
    startIndex: Int,
    endIndex: Int,
    newString: String
): AnnotatedString {
    // Sprawdzenie, czy indeksy są poprawne
    if (startIndex < 0 || endIndex > original.length || startIndex >= endIndex) {
        throw IllegalArgumentException("Invalid indices for the given AnnotatedString.")
    }

    // Budujemy nowy AnnotatedString
    val builder = AnnotatedString.Builder()

    // Dodajemy część przed zamienianym fragmentem
    builder.append(original.subSequence(0, startIndex))

    // Dodajemy nowy tekst z formatowaniem
    builder.append(newString)

    // Dodajemy część po zamienianym fragmencie
    builder.append(original.subSequence(endIndex, original.length))

    return builder.toAnnotatedString()
}