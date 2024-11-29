package com.example.logointerpreterbeta.viewModels

import android.app.UiModeManager
import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.logointerpreterbeta.LogoInterpreter
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    @ApplicationContext private val context: Context
) : ViewModel() {
    companion object{
        var darkMode  by  mutableStateOf(false)
    }
    val uiModeManager = context.getSystemService(Context.UI_MODE_SERVICE) as UiModeManager
    var selectedTheme by mutableStateOf("Systemowy")
    val themeOptions = listOf("Systemowy", "Jasny", "Ciemny")
    var selectedFont by  mutableStateOf("JetBrains Mono")
    val fontOptions = listOf("JetBrains Mono", "Comic Sans MS")
    init {

    }
    fun changeSelectedTheme(){
        darkMode = when (selectedTheme) {
            "Systemowy" -> uiModeManager.nightMode == UiModeManager.MODE_NIGHT_YES
            "Jasny" -> false
            "Ciemny" -> true
            else -> false
        }
    }
}