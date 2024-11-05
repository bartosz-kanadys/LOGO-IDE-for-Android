package com.example.logointerpreterbeta.components.codeEditor.textFunctions

import android.util.Log
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString

fun textDiffrence(oldText: AnnotatedString, newText: String, functionToPerformOnNewText: (String) -> AnnotatedString): AnnotatedString {
    return buildAnnotatedString {
        val commonPrefixLength = newText.commonPrefixWith(oldText).length
        var lastSpaceInPrefix =
            NearestWordFinder.findNearestSpaceToLeft(newText, commonPrefixLength)
        if(lastSpaceInPrefix==-1) {
            lastSpaceInPrefix=0
        }
        //Log.i("oldText",oldText.substring(0, lastSpaceInPrefix))
        append(oldText.subSequence(0, lastSpaceInPrefix))
        if (lastSpaceInPrefix < newText.length) {
            //Log.i("newText",newText.substring(lastSpaceInPrefix))
            append(functionToPerformOnNewText(newText.substring(lastSpaceInPrefix)))
        }
    }
}