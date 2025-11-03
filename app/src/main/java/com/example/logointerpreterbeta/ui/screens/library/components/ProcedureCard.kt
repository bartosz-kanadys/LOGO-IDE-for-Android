package com.example.logointerpreterbeta.ui.screens.library.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.logointerpreterbeta.ui.utils.LogoTextColorizer
import com.example.logointerpreterbeta.ui.screens.interpreter.components.codeEditor.DisplayOnlyCodeEditor
import com.example.logointerpreterbeta.ui.theme.AppTypography
import com.example.logointerpreterbeta.ui.theme.LogoInterpreterBetaTheme

@Composable
fun ProcedureCard(
    procedureName: String,
    procedureDescription: String,
    code: String,
    onDeleteClick: () -> Unit
) {
    val coloredCode = LogoTextColorizer.colorizeText(code, false)
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding()
    ) {

        val linesCount = code.lines().size
        Column(Modifier.padding(8.dp)) {
            Row(Modifier.fillMaxWidth()) {
                Column(Modifier.weight(1f)) {
                    Text(text = procedureName, fontSize = 26.sp)
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(text = procedureDescription, style = AppTypography.bodySmall)
                }
                TextButton(
                    onClick = { onDeleteClick() },
                ) {
                    Icon(imageVector = Icons.Filled.Delete, contentDescription = null)
                }
            }

            Spacer(modifier = Modifier.height(10.dp))
            DisplayOnlyCodeEditor(
                codeState = TextFieldValue(coloredCode),
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewProcedureCard() {
    LogoInterpreterBetaTheme {
        Column {
            ProcedureCard(
                "Kwadrat",
                "Opis procedury kwadratu co robi",
                "fd 100 rt 90\nnnn\nnn\nn\n\nnn\nnn",
                {})
        }
    }
}