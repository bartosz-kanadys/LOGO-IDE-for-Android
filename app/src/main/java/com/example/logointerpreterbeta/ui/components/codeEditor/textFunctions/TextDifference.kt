package com.example.logointerpreterbeta.ui.components.codeEditor.textFunctions

import android.util.Log
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString

fun textDiffrence(
    oldText: AnnotatedString,
    newText: String,
    functionToPerformOnNewText: (String) -> AnnotatedString
): AnnotatedString {
    return buildAnnotatedString {
        //Znalezienie długości wspólnego prefiksu
        val commonPrefixLength = newText.commonPrefixWith(oldText).length
        var lastSpaceInPrefix =
            NearestWordFinder.findNearestSpaceToLeft(newText, commonPrefixLength)
        //Jeśli nie znaleziono w tekście spacji, bierzemy pod uwagę cały tekst
        if (lastSpaceInPrefix == -1) {
            lastSpaceInPrefix = 0
        }
        //Przekopiowanie starego tekstu bez ostatniego słowa
        append(oldText.subSequence(0, lastSpaceInPrefix))
        if (lastSpaceInPrefix < newText.length) {
            //Wywołanie przekazanej funkcji na nowym tekście
            append(functionToPerformOnNewText(newText.substring(lastSpaceInPrefix)))
        }
    }
}