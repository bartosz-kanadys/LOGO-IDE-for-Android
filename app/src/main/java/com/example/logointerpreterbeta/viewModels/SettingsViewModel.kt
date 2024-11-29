package com.example.logointerpreterbeta.viewModels

import android.app.UiModeManager
import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.lifecycle.ViewModel
import com.example.logointerpreterbeta.LogoInterpreter
import com.example.logointerpreterbeta.R
import com.example.logointerpreterbeta.ui.components.codeEditor.textFunctions.createTypography
import com.example.logointerpreterbeta.ui.theme.AppTypography
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    @ApplicationContext private val context: Context
) : ViewModel() {
    companion object{
        var currentTheme  by  mutableStateOf(themeMode.LIGHT_THEME)
        var darkMode by mutableStateOf(false)
        var currentFont by mutableStateOf(AppTypography)
    }
    val uiModeManager = context.getSystemService(Context.UI_MODE_SERVICE) as UiModeManager
    var selectedTheme by mutableStateOf("Systemowy")
    val themeOptions = listOf("Systemowy", "Jasny", "Ciemny")
    var selectedFont by  mutableStateOf("JetBrains Mono")
    val fontOptions = listOf("JetBrains Mono", "Comic Sans MS", "Bebas Neue")
    val fonts = listOf(AppTypography.bodySmall.fontFamily, FontFamily(Font(R.font.comic_sans_ms)),FontFamily(Font(R.font.bebas_neue_regular)))
    init {

    }
    fun changeSelectedTheme(){
        currentTheme = when (selectedTheme) {
            "Systemowy" -> themeMode.SYSTEM_THEME
            "Jasny" -> themeMode.LIGHT_THEME
            "Ciemny" -> themeMode.DARK_THEME
            else -> themeMode.SYSTEM_THEME
        }
    }
    fun changeSelectedFont(){
        currentFont = when (selectedFont) {
            "JetBrains Mono" -> AppTypography
            "Comic Sans MS" -> createTypography(FontFamily(Font(R.font.comic_sans_ms)))
            "Bebas Neue" -> createTypography(FontFamily(Font(R.font.bebas_neue_regular)))
            else -> AppTypography

        }
    }
}