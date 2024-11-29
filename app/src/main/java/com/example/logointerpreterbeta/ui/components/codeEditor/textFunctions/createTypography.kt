package com.example.logointerpreterbeta.ui.components.codeEditor.textFunctions
import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.FontFamily
import com.example.logointerpreterbeta.ui.theme.baseline

fun createTypography(fontFamily: FontFamily): Typography {
    return Typography( bodySmall = baseline.bodySmall.copy(fontFamily = fontFamily))
}