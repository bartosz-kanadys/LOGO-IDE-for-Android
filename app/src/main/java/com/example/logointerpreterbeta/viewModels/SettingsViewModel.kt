package com.example.logointerpreterbeta.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.logointerpreterbeta.LogoInterpreter
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor() : ViewModel() {
    var selectedTheme by mutableStateOf("Systemowy")
    val themeOptions = listOf("Systemowy", "Jasny", "Ciemny")
    var selectedFont by  mutableStateOf("JetBrains Mono")
    val fontOptions = listOf("JetBrains Mono", "Comic Sans MS")
    init {

    }
}