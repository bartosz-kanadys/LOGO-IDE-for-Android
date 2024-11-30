package com.example.logointerpreterbeta.ui.components.codeEditor.textFunctions
import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.example.logointerpreterbeta.ui.theme.baseline

fun createTypography(fontFamily: FontFamily, lineHeight: Float = 1.5f): Typography {
    return Typography( bodySmall = baseline.bodySmall.copy(fontFamily = fontFamily, lineHeight = lineHeight.sp))
}