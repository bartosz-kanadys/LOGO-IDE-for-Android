package com.example.logointerpreterbeta.domain.repository

import com.example.logointerpreterbeta.domain.enums.ThemeMode
import com.example.logointerpreterbeta.domain.models.Config
import kotlinx.coroutines.flow.Flow

interface ConfigRepository {
    fun readTheme(): Flow<String>
    suspend fun getCurrentTheme(): ThemeMode
    suspend fun updateTheme(newTheme: ThemeMode)
    suspend fun updateFont(newFont: String)
    suspend fun updateFontSize(newFontSize: Int)
    suspend fun updateShowSuggestions(newShowSuggestions: Boolean)
    suspend fun updateUseAutocorrect(newUseAutocorrect: Boolean)
    fun readSettings(): Flow<Config>
}