package com.example.logointerpreterbeta.domain.repository

import com.example.logointerpreterbeta.domain.models.Config
import kotlinx.coroutines.flow.Flow

interface ConfigRepository {
    suspend fun updateLastProject(newProjectName: String)
    fun readLastProject(): Flow<String?>
    fun readTheme(): Flow<String>
    suspend fun updateTheme(newTheme: String)
    suspend fun updateFont(newFont: String)
    suspend fun updateFontSize(newFontSize: Int)
    suspend fun updateShowSuggestions(newShowSuggestions: Boolean)
    suspend fun updateUseAutocorrect(newUseAutocorrect: Boolean)
    fun readSettings(): Flow<Config>
}