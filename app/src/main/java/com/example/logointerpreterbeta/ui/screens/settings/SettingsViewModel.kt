package com.example.logointerpreterbeta.ui.screens.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.logointerpreterbeta.domain.enums.FontsEnum
import com.example.logointerpreterbeta.domain.enums.ThemeMode
import com.example.logointerpreterbeta.domain.usecase.settings.ReadSettingsUseCase
import com.example.logointerpreterbeta.domain.usecase.settings.UpdateAutocorrectUseCase
import com.example.logointerpreterbeta.domain.usecase.settings.UpdateFontSizeUseCase
import com.example.logointerpreterbeta.domain.usecase.settings.UpdateFontUseCase
import com.example.logointerpreterbeta.domain.usecase.settings.UpdateShowSuggestionUseCase
import com.example.logointerpreterbeta.domain.usecase.settings.UpdateThemeUseCase
import com.example.logointerpreterbeta.domain.usecase.shared.ThemeModeCheckUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class SettingsUiState(
    val lastModifiedProject: String = "",
    val currentTheme: ThemeMode = ThemeMode.SYSTEM_THEME,
    val currentFont: FontsEnum = FontsEnum.JETBRAINS_MONO,
    val currentFontSize: Int = 18,
    val showSuggestions: Boolean = true,
    val useAutocorrect: Boolean = false
)

sealed interface SettingsSideEffect {
    data class UpdateDrawingTheme(val isDarkTheme: Boolean) : SettingsSideEffect
}

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val readSettingsUseCase: ReadSettingsUseCase,
    private val updateThemeUseCase: UpdateThemeUseCase,
    private val updateFontUseCase: UpdateFontUseCase,
    private val updateFontSizeUseCase: UpdateFontSizeUseCase,
    private val updateShowSuggestionUseCase: UpdateShowSuggestionUseCase,
    private val updateAutocorrectUseCase: UpdateAutocorrectUseCase,
    private val themeModeCheckUseCase: ThemeModeCheckUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(SettingsUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            val setting  = readSettingsUseCase()
            setting.onSuccess { configFlow ->
                configFlow.collect { config ->
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

    fun changeSelectedTheme(newTheme: ThemeMode) {
        viewModelScope.launch {
            updateThemeUseCase(newTheme)
        }
    }

    fun changeSelectedFont(newFont: FontsEnum) {
        viewModelScope.launch {
            updateFontUseCase(newFont.value)
        }
    }

    fun changeSelectedFontSize(newFontSize: String) {
        viewModelScope.launch {
            updateFontSizeUseCase(newFontSize)
        }
    }

    fun saveShowSuggestions(newValue: Boolean) {
        viewModelScope.launch {
            updateShowSuggestionUseCase(newValue)
        }
    }

    fun saveUseAutocorrect(newValue: Boolean) {
        viewModelScope.launch {
            updateAutocorrectUseCase(newValue)
        }
    }
}