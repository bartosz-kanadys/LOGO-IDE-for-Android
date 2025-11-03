package com.example.logointerpreterbeta.ui.screens.settings.components

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = label,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                style = AppTypography.bodySmall,
                textAlign = TextAlign.Start,
                modifier = Modifier.weight(1f)
            )
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
