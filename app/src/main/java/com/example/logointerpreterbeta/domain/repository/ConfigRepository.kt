package com.example.logointerpreterbeta.domain.repository

import android.content.Context
import com.example.logointerpreterbeta.domain.models.Config

interface ConfigRepository {
    fun updateLastProjectJSON(newProjectName: String)
    fun readLastProjectJSON(): String?
    fun updateThemeJSON(newTheme: String)
    fun updateFontJSON(newFont: String)
    fun updateFontSizeJSON(newFontSize: Int)
    fun updateShowSuggestionsJSON(newShowSuggestions: Boolean)
    fun updateUseAutocorrectJSON(newUseAutocorrect: Boolean)
    fun readSettingsJSON(): Config?
    fun createConfigFile(context: Context)
}