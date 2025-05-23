package com.example.logointerpreterbeta.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.logointerpreterbeta.ui.components.settings.SettingsOption
import com.example.logointerpreterbeta.ui.components.settings.SettingsSwitch
import com.example.logointerpreterbeta.ui.theme.AppTypography
import com.example.logointerpreterbeta.viewModels.SettingsViewModel

@Composable
fun SettingsApp(
    settingsViewModel: SettingsViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    modifier: Modifier = Modifier
) {

    Surface(
        modifier = Modifier.fillMaxHeight()
    ) {
        val scrollState = rememberScrollState()
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .verticalScroll(scrollState)
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
            SettingsOption("Motyw", settingsViewModel.themeOptions, settingsViewModel.selectedTheme,{
                settingsViewModel.selectedTheme = it
                settingsViewModel.changeSelectedTheme()
                },
            )
            SettingsOption("Czcionka",settingsViewModel.fontOptions, settingsViewModel.selectedFont,{
                settingsViewModel.selectedFont = it
                settingsViewModel.changeSelectedFont()
                },
                fonts = settingsViewModel.fonts,
                selectedFont = SettingsViewModel.currentFont
            )
            SettingsOption("Rozmiar czcionki",settingsViewModel.fontSizeOptions, settingsViewModel.selectedFontSize,{
                settingsViewModel.selectedFontSize = it
                settingsViewModel.changeSelectedFontSize()
            },
                fontSizes = settingsViewModel.fontSizeOptions
            )
            Text(
                text = "Przykładowy tekst",
                style = TextStyle(
                    fontFamily = SettingsViewModel.currentFont.bodySmall.fontFamily,
                    fontSize = SettingsViewModel.currentFontSize.sp
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp, bottom = 8.dp)
            )
            Text(
                text = "Ustawienia edytora",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                style = AppTypography.bodySmall,
                textAlign = TextAlign.Start,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp, top = 16.dp)
            )
            SettingsSwitch(
                label="Podpowiedzi",
                isSwitchOn = SettingsViewModel.showSuggestions,
                onSwitchToggled = {
                    SettingsViewModel.showSuggestions = it
                    settingsViewModel.saveShowSuggestions()
                }
            )
//            SettingsSwitch(
//                label="Autokorekta",
//                isSwitchOn = SettingsViewModel.useAutocorrect,
//                onSwitchToggled = {
//                    SettingsViewModel.useAutocorrect = it
//                    settingsViewModel.saveUseAutocorrect()
//                }
//            )
        }
    }
}


//
//@Preview(showBackground = true, showSystemUi = false)
//@Composable
//fun SettingsPreview() {
//    LogoInterpreterBetaTheme {
//        Scaffold(
//            topBar = {
//                TopBarWithMenu("Ustawienia")
//            }
//        ) { @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter") innerPadding ->
//           // SettingsApp(Modifier.padding(innerPadding))
//        }
//    }
//}