package com.example.logointerpreterbeta.ui.screens.settings

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.lifecycle.ViewModel
import com.example.logointerpreterbeta.R
import com.example.logointerpreterbeta.domain.repository.ConfigRepository
import com.example.logointerpreterbeta.ui.drawing.AndroidDrawingDelegate
import com.example.logointerpreterbeta.ui.screens.interpreter.components.codeEditor.textFunctions.createTypography
import com.example.logointerpreterbeta.ui.theme.AppTypography
import com.example.logointerpreterbeta.ui.theme.ThemeMode
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val configRepository: ConfigRepository,
    private val drawingDelegate: AndroidDrawingDelegate
) : ViewModel() {
    companion object{
        var currentTheme  by mutableStateOf(ThemeMode.LIGHT_THEME)
        var darkMode by mutableStateOf(false)
        var currentFont by mutableStateOf(AppTypography)
        var currentFontSize by mutableStateOf(18)
        var showSuggestions by mutableStateOf(true)
        var useAutocorrect by mutableStateOf(false)
    }
    var selectedTheme by mutableStateOf("Systemowy")
    val themeOptions = listOf("Systemowy", "Jasny", "Ciemny")
    var selectedFont by mutableStateOf("JetBrains Mono")
    val fontOptions = listOf("JetBrains Mono", "Comic Sans MS", "Bebas Neue")
    val fonts = listOf(
        AppTypography.bodySmall.fontFamily,
        FontFamily(Font(R.font.comic_sans_ms)),
        FontFamily(Font(R.font.bebas_neue_regular))
    )
    val fontSizeOptions = listOf("26","24","22","20","18", "16", "14","12","10")
    var selectedFontSize by mutableStateOf("18")

    init {

    }
    fun changeSelectedTheme(){
        currentTheme = when (selectedTheme) {
            "Systemowy" -> ThemeMode.SYSTEM_THEME
            "Jasny" -> ThemeMode.LIGHT_THEME
            "Ciemny" -> ThemeMode.DARK_THEME
            else -> ThemeMode.SYSTEM_THEME
        }
        configRepository.updateThemeJSON(selectedTheme)
        drawingDelegate.updateTheme(
            currentTheme == ThemeMode.DARK_THEME
            || currentTheme == ThemeMode.SYSTEM_THEME && darkMode
        )
    }
    fun changeSelectedFont(){
        currentFont = when (selectedFont) {
            "JetBrains Mono" -> AppTypography
            "Comic Sans MS" -> createTypography(FontFamily(Font(R.font.comic_sans_ms)))
            "Bebas Neue" -> createTypography(FontFamily(Font(R.font.bebas_neue_regular)))
            else -> AppTypography

        }
        configRepository.updateFontJSON(selectedFont)
    }
    fun changeSelectedFontSize(){
        currentFontSize = selectedFontSize.toInt()
        configRepository.updateFontSizeJSON(currentFontSize)
    }
    fun saveShowSuggestions(){
        configRepository.updateShowSuggestionsJSON(showSuggestions)
    }
    fun saveUseAutocorrect(){
        configRepository.updateUseAutocorrectJSON(useAutocorrect)
    }
    fun loadSettingsFromJson(){
        val config = configRepository.readSettingsJSON()
        selectedTheme = config?.currentTheme ?: "Systemowy"
        changeSelectedTheme()
        selectedFont = config?.currentFont ?: "JetBrains Mono"
        changeSelectedFont()
        selectedFontSize = config?.currentFontSize.toString() ?: "18"
        changeSelectedFontSize()
        showSuggestions = config?.showSuggestions ?: true
        useAutocorrect = config?.useAutocorrect ?: false

    }
}