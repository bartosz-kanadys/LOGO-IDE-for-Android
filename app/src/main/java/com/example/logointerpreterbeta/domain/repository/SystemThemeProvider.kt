package com.example.logointerpreterbeta.domain.repository

import kotlinx.coroutines.flow.Flow

interface SystemThemeProvider {
    fun isSystemDarkTheme(): Boolean
    fun observeSystemDarkTheme(): Flow<Boolean>
}