package com.example.logointerpreterbeta.ui.components.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.logointerpreterbeta.ui.components.codeEditor.CodeEditor
import com.example.logointerpreterbeta.ui.theme.AppTypography

@Composable
fun SettingsSwitch(
    label: String,
    isSwitchOn: Boolean,
    onSwitchToggled: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically, // Wyśrodkowanie w pionie
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            // Tekst opcji po lewej stronie
            Text(
                text = label,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                style = AppTypography.bodySmall,
                textAlign = TextAlign.Start,
                modifier = Modifier.weight(1f) // Zajmowanie całej dostępnej przestrzeni
            )
            // Przełącznik po prawej stronie
            Switch(
                checked = isSwitchOn,
                onCheckedChange = onSwitchToggled
            )
        }
    }
}
@Preview
@Composable
fun Settings() {
    SettingsSwitch(
        label = "test",
        isSwitchOn = true,
        onSwitchToggled = { },
        modifier = Modifier
    )
}
