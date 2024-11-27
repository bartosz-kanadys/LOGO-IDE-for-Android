package com.example.logointerpreterbeta.viewModels

import android.app.UiModeManager
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
    var darkMode = false
    var selectedFont by  mutableStateOf("JetBrains Mono")
    val fontOptions = listOf("JetBrains Mono", "Comic Sans MS")
    init {

    }
    fun changeSelectedTheme(theme: String){
        if(selectedTheme=="Systemowy"){
            //darkMode = uiModeManager.nightMode == UiModeManager.MODE_NIGHT_YES
        }
        else if(selectedTheme=="Jasny"){

        }
        else{

        }
    }
}