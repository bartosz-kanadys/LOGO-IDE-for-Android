package com.example.logointerpreterbeta.ui.components.settings

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import com.example.logointerpreterbeta.ui.theme.AppTypography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Select(
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
    fonts: List<FontFamily?> = emptyList()
) {
    var expanded by remember { mutableStateOf(false) } // Kontroluje widoczność menu

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = modifier
    ) {
        OutlinedTextField(
            value = selectedOption,
            onValueChange = {},
            readOnly = true, // Blokujemy ręczne wprowadzanie tekstu
            label = { },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            modifier = Modifier
                .menuAnchor() // Kluczowy element do poprawnego pozycjonowania menu
                .fillMaxWidth()
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            var i=0;
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(
                        text = option,
                        style = TextStyle(
                            fontFamily = if(fonts.isNotEmpty()) fonts[i++] else AppTypography.bodySmall.fontFamily
                        )
                    ) },
                    onClick = {
                        onOptionSelected(option) // Wybranie opcji
                        expanded = false // Zamknięcie menu
                    }
                )
            }
        }
    }
}



@Composable
@Preview(showBackground = true)
fun PreviewCustomSelect() {
    var selected by remember { mutableStateOf("Option 1") }
    val options = listOf("Option 1", "Option 2", "Option 3", "Option 4")

    Select(
        options = options,
        selectedOption = selected,
        onOptionSelected = { selected = it }
    )
}