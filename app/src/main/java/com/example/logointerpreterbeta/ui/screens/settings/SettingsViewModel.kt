package com.example.logointerpreterbeta.ui.screens.settings

import android.util.Log
import androidx.compose.material3.Typography
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.logointerpreterbeta.R
import com.example.logointerpreterbeta.domain.repository.ConfigRepository
import com.example.logointerpreterbeta.domain.repository.ThemeRepository
import com.example.logointerpreterbeta.ui.drawing.AndroidDrawingDelegate
import com.example.logointerpreterbeta.ui.screens.interpreter.components.codeEditor.textFunctions.createTypography
import com.example.logointerpreterbeta.ui.theme.AppTypography
import com.example.logointerpreterbeta.ui.theme.ThemeMode
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val configRepository: ConfigRepository,
    private val drawingDelegate: AndroidDrawingDelegate,
    private val themeRepository: ThemeRepository
) : ViewModel() {
    var selectedTheme by mutableStateOf("Systemowy")
    val themeOptions = listOf("Systemowy", "Jasny", "Ciemny")
    var selectedFont by mutableStateOf("JetBrains Mono")
    var currentTypography by mutableStateOf(AppTypography)
    val fontOptions = listOf("JetBrains Mono", "Comic Sans MS", "Bebas Neue")
    val fonts = listOf(
        AppTypography.bodySmall.fontFamily,
        FontFamily(Font(R.font.comic_sans_ms)),
        FontFamily(Font(R.font.bebas_neue_regular))
    )
    val fontSizeOptions = listOf("26","24","22","20","18", "16", "14","12","10")
    var selectedFontSize by mutableStateOf("18")

    var showSuggestions by mutableStateOf(true)
    var useAutocorrect by mutableStateOf(false)

    init {
        loadSettingsFromDataStore()
    }

    fun changeSelectedTheme(){
        val currentTheme = when (selectedTheme) {
            "Systemowy" -> ThemeMode.SYSTEM_THEME
            "Jasny" -> ThemeMode.LIGHT_THEME
            "Ciemny" -> ThemeMode.DARK_THEME
            else -> ThemeMode.SYSTEM_THEME
        }
        Log.d("SettingsViewModel", "Changing theme to $currentTheme")
        viewModelScope.launch {
            configRepository.updateTheme(currentTheme.value)
            val theme = themeRepository.isDarkTheme()
            Log.d("SettingsViewModel", "Theme is dark: $theme")
            drawingDelegate.updateTheme(theme)
        }
    }

    fun changeSelectedFont(){
        viewModelScope.launch {
            configRepository.updateFont(selectedFont)
        }
        currentTypography = getAppTopography()
    }

    fun getAppTopography(): Typography {
        return when (selectedFont) {
            "JetBrains Mono" -> AppTypography
            "Comic Sans MS" -> createTypography(FontFamily(Font(R.font.comic_sans_ms)))
            "Bebas Neue" -> createTypography(FontFamily(Font(R.font.bebas_neue_regular)))
            else -> AppTypography
        }
    }

    fun changeSelectedFontSize(){
        //currentFontSize = selectedFontSize.toInt()
        viewModelScope.launch {
            configRepository.updateFontSize(selectedFontSize.toInt())
        }
    }

    fun saveShowSuggestions(){
        viewModelScope.launch {
            configRepository.updateShowSuggestions(showSuggestions)
        }
    }

    fun saveUseAutocorrect(){
        viewModelScope.launch {
            configRepository.updateUseAutocorrect(useAutocorrect)
        }
    }

    fun loadSettingsFromDataStore() {
        viewModelScope.launch {
            val config = configRepository.readSettings().first()

            selectedTheme = config.currentTheme.ifEmpty { "Systemowy" }
            //changeSelectedTheme()

            selectedFont = config.currentFont.ifEmpty { "JetBrains Mono" }
            //changeSelectedFont()

            selectedFontSize = config.currentFontSize.takeIf { it > 0 }?.toString() ?: "18"
            //changeSelectedFontSize()

            showSuggestions = config.showSuggestions
            useAutocorrect = config.useAutocorrect
        }
    }
}