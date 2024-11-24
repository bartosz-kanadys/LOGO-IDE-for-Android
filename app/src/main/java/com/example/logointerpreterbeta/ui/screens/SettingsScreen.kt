package com.example.logointerpreterbeta.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.logointerpreterbeta.navigation.topBars.TopBarWithMenu
import com.example.logointerpreterbeta.ui.components.settings.Select
import com.example.logointerpreterbeta.ui.components.settings.SettingsOption
import com.example.logointerpreterbeta.ui.theme.AppTypography
import com.example.logointerpreterbeta.ui.theme.LogoInterpreterBetaTheme

@Composable
fun SettingsApp(modifier: Modifier = Modifier) {
    var selectedTheme by remember { mutableStateOf("Systemowy") }
    val themeOptions = listOf("Systemowy", "Jasny", "Ciemny")
    var selectedFont by remember { mutableStateOf("JetBrains Mono") }
    val fontOptions = listOf("JetBrains Mono", "Comic Sans MS")
    Surface(
        modifier = Modifier.fillMaxHeight()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(30.dp)
                .then(modifier)
        ) {
            Text(
                text = "Wygląd",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                style = AppTypography.bodySmall,
                textAlign = TextAlign.Start,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            )
            SettingsOption("Motyw", themeOptions, selectedTheme,{ selectedTheme = it })
            SettingsOption("Czcionka",fontOptions, selectedFont,{ selectedFont = it })
        }
    }
}



@Preview(showBackground = true, showSystemUi = false)
@Composable
fun SettingsPreview() {
    LogoInterpreterBetaTheme {
        Scaffold(
            topBar = {
                TopBarWithMenu("Ustawienia")
            }
        ) { innerPadding ->
            SettingsApp(Modifier.padding(innerPadding))
        }
    }
}