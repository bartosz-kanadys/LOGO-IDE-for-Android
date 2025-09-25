package com.example.logointerpreterbeta.ui.screens.settings.components

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
import androidx.compose.ui.unit.sp
import com.example.logointerpreterbeta.ui.theme.AppTypography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownSelect(
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
    fonts: List<FontFamily?> = emptyList(),
    selectedFontFamily: FontFamily? = AppTypography.bodySmall.fontFamily,
    fontSizes: List<String> = emptyList(),
    selectedFontSize: Int = MaterialTheme.typography.bodyMedium.fontSize.value.toInt()
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = modifier
    ) {
        OutlinedTextField(
            value = selectedOption,
            onValueChange = {},
            readOnly = true,
            label = { },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            textStyle = TextStyle(
                fontFamily = selectedFontFamily,
                fontSize = selectedFontSize.sp
            ),
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEachIndexed { index, option ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = option,
                            style = TextStyle(
                                fontFamily = fonts.getOrNull(index) ?: AppTypography.bodySmall.fontFamily,
                                fontSize = fontSizes.getOrNull(index)?.toIntOrNull()?.sp ?:
                                    MaterialTheme.typography.bodyMedium.fontSize.value.toInt().sp
                            )
                        )
                    },
                    onClick = {
                        onOptionSelected(option)
                        expanded = false
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

    DropdownSelect(
        options = options,
        selectedOption = selected,
        onOptionSelected = { selected = it }
    )
}