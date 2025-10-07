package com.example.logointerpreterbeta.ui.screens.settings

import androidx.compose.material3.Typography
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
import com.example.logointerpreterbeta.ui.theme.FontsEnum
import com.example.logointerpreterbeta.ui.theme.ThemeMode
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class SettingsUiState(
    val lastModifiedProject: String = "",
    val currentTheme: ThemeMode = ThemeMode.SYSTEM_THEME,
    val currentFont: FontsEnum = FontsEnum.JETBRAINS_MONO,
    val currentFontSize: Int = 18,
    val currentTopography: Typography = AppTypography,
    val showSuggestions: Boolean = true,
    val useAutocorrect: Boolean = false
)

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val configRepository: ConfigRepository,
    private val drawingDelegate: AndroidDrawingDelegate,
    private val themeRepository: ThemeRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(SettingsUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadSettingsFromDataStore()
    }

    fun changeSelectedTheme(newTheme: ThemeMode) {
        _uiState.update { it.copy(currentTheme = newTheme) }

        viewModelScope.launch {
            configRepository.updateTheme(newTheme.value)
            val theme = themeRepository.isDarkTheme()
            drawingDelegate.updateTheme(theme)
        }
    }

    fun changeSelectedFont(newFont: FontsEnum) {
        viewModelScope.launch {
            configRepository.updateFont(newFont.value)
        }
        _uiState.update {
            it.copy(
                currentTopography = getAppTopography(),
                currentFont = newFont
            )
        }
    }

    fun getAppTopography(): Typography {
        return when (_uiState.value.currentFont) {
            FontsEnum.JETBRAINS_MONO -> AppTypography
            FontsEnum.COMIC_SANS_MS -> createTypography(FontFamily(Font(R.font.comic_sans_ms)))
            FontsEnum.BEBAS_NEUE -> createTypography(FontFamily(Font(R.font.bebas_neue_regular)))
        }
    }

    fun changeSelectedFontSize(newFontSize: String) {
        viewModelScope.launch {
            configRepository.updateFontSize(_uiState.value.currentFontSize)
        }
        _uiState.update { it.copy(currentFontSize = newFontSize.toInt()) }
    }

    fun saveShowSuggestions(newValue: Boolean) {
        viewModelScope.launch {
            configRepository.updateShowSuggestions(newValue)
        }
        _uiState.update { it.copy(showSuggestions = newValue) }
    }

    fun saveUseAutocorrect(newValue: Boolean) {
        viewModelScope.launch {
            configRepository.updateUseAutocorrect(newValue)
        }
        _uiState.update { it.copy(useAutocorrect = newValue) }
    }

    fun loadSettingsFromDataStore() {
        viewModelScope.launch {
            configRepository.readSettings().collect { config ->
                _uiState.update {
                    it.copy(
                        lastModifiedProject = config.lastModifiedProject,
                        currentTheme = ThemeMode.fromString(config.currentTheme),
                        currentFont = FontsEnum.fromString(config.currentFont),
                        currentFontSize = config.currentFontSize.takeIf { it > 0 } ?: 18,
                        showSuggestions = config.showSuggestions,
                        useAutocorrect = config.useAutocorrect
                    )
                }
            }
        }
    }
}