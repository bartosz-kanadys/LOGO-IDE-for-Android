package com.example.logointerpreterbeta.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.example.logointerpreterbeta.data.dataStore.ConfigKeys.THEME
import com.example.logointerpreterbeta.domain.repository.SystemThemeProvider
import com.example.logointerpreterbeta.domain.repository.ThemeRepository
import com.example.logointerpreterbeta.ui.theme.ThemeMode
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

class ThemeRepositoryImpl(
    private val dataStore: DataStore<Preferences>,
    private val systemThemeProvider: SystemThemeProvider
): ThemeRepository {

    override suspend fun getCurrentTheme(): ThemeMode {
        return dataStore.data.map { preferences ->
            preferences[THEME]?.let {
                ThemeMode.fromString(it)
            } ?: ThemeMode.SYSTEM_THEME
        }.first()
    }

    override suspend fun setCurrentTheme(theme: ThemeMode) {
        dataStore.edit { preferences ->
            preferences[THEME] = ThemeMode.toString()
        }
    }

    override fun observeTheme(): Flow<ThemeMode> {
        return dataStore.data.map { preferences ->
            preferences[THEME]?.let {
                ThemeMode.fromString(it)
            } ?: ThemeMode.SYSTEM_THEME
        }
    }

    override fun isDarkTheme(): Boolean {
        val currentTheme = runBlocking { getCurrentTheme() }
        return when (currentTheme) {
            ThemeMode.LIGHT_THEME -> false
            ThemeMode.DARK_THEME -> true
            ThemeMode.SYSTEM_THEME -> systemThemeProvider.isSystemDarkTheme()
        }
    }
}