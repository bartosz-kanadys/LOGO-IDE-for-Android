package com.example.logointerpreterbeta.ui.components.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.logointerpreterbeta.ui.theme.AppTypography

@Composable
fun SettingsOption(
    label: String,
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
    fonts: List<FontFamily?> = emptyList(),
    fontSizes: List<String> = emptyList(),
    selectedFont: Typography = AppTypography,
    selectedFontSize: Int = 18
){
    Column (
        modifier = Modifier
    ) {
        Text(
            text = label,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            style = AppTypography.bodySmall,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        )
        Select(
            options = options,
            selectedOption = selectedOption,
            onOptionSelected = onOptionSelected,
            fonts = fonts,
            selectedFont = selectedFont,
            fontSizes = fontSizes
        )
    }
}