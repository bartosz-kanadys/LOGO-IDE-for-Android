package com.example.logointerpreterbeta.domain.repository

import com.example.logointerpreterbeta.domain.enums.ThemeMode
import kotlinx.coroutines.flow.Flow

interface ThemeRepository {
    suspend fun getCurrentTheme(): ThemeMode
    suspend fun setCurrentTheme(theme: ThemeMode)
    fun observeTheme(): Flow<ThemeMode>
    fun isDarkTheme(): Boolean
}
