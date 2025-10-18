package com.example.logointerpreterbeta.ui.screens.settings

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.logointerpreterbeta.R
import com.example.logointerpreterbeta.domain.enums.FontsEnum
import com.example.logointerpreterbeta.domain.enums.ThemeMode
import com.example.logointerpreterbeta.ui.drawing.AndroidDrawingDelegate
import com.example.logointerpreterbeta.ui.screens.interpreter.components.codeEditor.textFunctions.createTypography
import com.example.logointerpreterbeta.ui.screens.settings.components.SettingsOption
import com.example.logointerpreterbeta.ui.screens.settings.components.SettingsSwitch
import com.example.logointerpreterbeta.ui.screens.settings.components.fontSizeOptions
import com.example.logointerpreterbeta.ui.screens.settings.components.fonts
import com.example.logointerpreterbeta.ui.theme.AppTypography
import com.example.logointerpreterbeta.ui.theme.LogoInterpreterBetaTheme

@Composable
fun SettingsScreenRoot(
    modifier: Modifier = Modifier,
    viewModel: SettingsViewModel = viewModel(),
    drawingDelegate: AndroidDrawingDelegate
) {
    val state = viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(state.value.currentTheme) {
        viewModel.sideEffects.collect { effect ->
            when (effect) {
                is SettingsSideEffect.UpdateDrawingTheme -> {
                    drawingDelegate.updateTheme(effect.isDarkTheme)
                }
            }
        }
    }

    SettingsScreen(
        uiState = state.value,
        onThemeChanged = {
            viewModel.changeSelectedTheme(it)
        },
        onFontChanged = {
            viewModel.changeSelectedFont(it)
        },
        onFontSizeChanged = {
            viewModel.changeSelectedFontSize(it)
        },
        onSuggestionsChanged = {
            viewModel.saveShowSuggestions(it)
        },
        onAutocorrectChanged = {
            viewModel.saveUseAutocorrect(it)
        },
        modifier = modifier
    )
}

@Composable
fun SettingsScreen(
    uiState: SettingsUiState,
    onThemeChanged: (ThemeMode) -> Unit,
    onFontChanged: (FontsEnum) -> Unit,
    onFontSizeChanged: (String) -> Unit,
    onSuggestionsChanged: (Boolean) -> Unit,
    onAutocorrectChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val currentTypography by remember(uiState.currentFont) {
        derivedStateOf {
            when (uiState.currentFont) {
                FontsEnum.JETBRAINS_MONO -> AppTypography
                FontsEnum.COMIC_SANS_MS -> createTypography(FontFamily(Font(R.font.comic_sans_ms)))
                FontsEnum.BEBAS_NEUE -> createTypography(FontFamily(Font(R.font.bebas_neue_regular)))
            }
        }
    }

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
                text = stringResource(R.string.appearance),
                style = AppTypography.titleLarge.copy(
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold

                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            )
            SettingsOption(
                label = stringResource(R.string.theme),
                options = ThemeMode.entries.map { it.value },
                selectedOption = uiState.currentTheme.value,
                onOptionSelected = {
                    onThemeChanged(ThemeMode.fromString(it))
                }
            )
            SettingsOption(
                label = stringResource(R.string.font),
                options = FontsEnum.entries.map { it.value },
                selectedOption = uiState.currentFont.value,
                onOptionSelected = {
                    onFontChanged(FontsEnum.fromString(it))
                },
                fonts = fonts,
                selectedFont = currentTypography.bodySmall.fontFamily
            )
            SettingsOption(
                label = stringResource(R.string.font_size),
                options = fontSizeOptions,
                selectedOption = uiState.currentFontSize.toString(),
                onOptionSelected = {
                    onFontSizeChanged(it)
                },
                fontSizes = fontSizeOptions,
            )
            Text(
                text = stringResource(R.string.example_text),
                style = TextStyle(
                    fontFamily = currentTypography.bodySmall.fontFamily,
                    fontSize = uiState.currentFontSize.sp
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp, bottom = 8.dp)
            )
            Text(
                text = stringResource(R.string.editor_settings),
                style = AppTypography.titleLarge.copy(
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold
                ),
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp, top = 16.dp)
            )
            SettingsSwitch(
                label = stringResource(R.string.hints),
                isSwitchOn = uiState.showSuggestions,
                onSwitchToggled = {
                    onSuggestionsChanged(it)
                }
            )
            SettingsSwitch(
                label = stringResource(R.string.autocorrect),
                isSwitchOn = uiState.useAutocorrect,
                onSwitchToggled = {
                    onAutocorrectChanged(it)
                }
            )
        }
    }
}


@Preview(showBackground = true, showSystemUi = false)
@Composable
fun SettingsPreview() {
    LogoInterpreterBetaTheme {
        SettingsScreen(
            uiState = SettingsUiState(),
            onThemeChanged = {},
            onFontChanged = {},
            onFontSizeChanged = {},
            onSuggestionsChanged = {},
            onAutocorrectChanged = {}
        )
    }
}