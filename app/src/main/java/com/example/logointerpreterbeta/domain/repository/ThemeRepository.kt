package com.example.logointerpreterbeta.domain.repository

import com.example.logointerpreterbeta.domain.enums.ThemeMode
import kotlinx.coroutines.flow.Flow

interface ThemeRepository {
    fun observeTheme(): Flow<ThemeMode>
    fun isDarkTheme(): Boolean
}
