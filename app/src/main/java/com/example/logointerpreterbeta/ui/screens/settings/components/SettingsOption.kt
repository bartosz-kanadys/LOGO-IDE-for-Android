package com.example.logointerpreterbeta.ui.screens.settings.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
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
    fonts: List<FontFamily?> = emptyList(),
    fontSizes: List<String> = emptyList(),
    selectedFont: FontFamily? = AppTypography.bodySmall.fontFamily,
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
        DropdownSelect(
            options = options,
            selectedOption = selectedOption,
            onOptionSelected = onOptionSelected,
            fonts = fonts,
            selectedFontFamily = selectedFont,
            fontSizes = fontSizes
        )
    }
}