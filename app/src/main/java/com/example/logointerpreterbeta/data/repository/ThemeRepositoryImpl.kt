package com.example.logointerpreterbeta.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.logointerpreterbeta.data.dataStore.ConfigKeys.THEME
import com.example.logointerpreterbeta.domain.repository.SystemThemeProvider
import com.example.logointerpreterbeta.domain.repository.ThemeRepository
import com.example.logointerpreterbeta.domain.enums.ThemeMode
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class ThemeRepositoryImpl(
    private val dataStore: DataStore<Preferences>,
    private val systemThemeProvider: SystemThemeProvider
): ThemeRepository {

    private suspend fun getCurrentTheme(): ThemeMode {
        return dataStore.data.map { preferences ->
            preferences[THEME]?.let {
                ThemeMode.fromString(it)
            } ?: ThemeMode.SYSTEM_THEME
        }.first()
    }

    override fun observeTheme(): Flow<ThemeMode> {
        return dataStore.data.map { preferences ->
            preferences[THEME]?.let {
                ThemeMode.fromString(it)
            } ?: ThemeMode.SYSTEM_THEME
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun observeThemeBoolean(): Flow<Boolean> {
        //Rozpocznij od obserwowania ustawień (LIGHT, DARK, SYSTEM)
        return observeTheme().flatMapLatest { themeMode ->
            // Użyj flatMapLatest, aby przełączyć się na odpowiedni Flow źródłowy
            when (themeMode) {
                ThemeMode.LIGHT_THEME -> flowOf(false)
                ThemeMode.DARK_THEME -> flowOf(true)
                // Jeśli "SYSTEM", przełącz się na obserwowanie providera systemowego
                ThemeMode.SYSTEM_THEME -> systemThemeProvider.observeSystemDarkTheme()
            }
        }
    }

    override suspend fun isDarkTheme(): Boolean {
        val currentTheme = getCurrentTheme()
        return when (currentTheme) {
            ThemeMode.LIGHT_THEME -> false
            ThemeMode.DARK_THEME -> true
            ThemeMode.SYSTEM_THEME -> systemThemeProvider.isSystemDarkTheme()
        }
    }
}