package com.example.logointerpreterbeta.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.example.logointerpreterbeta.data.dataStore.ConfigKeys
import com.example.logointerpreterbeta.data.dataStore.ConfigKeys.THEME
import com.example.logointerpreterbeta.domain.enums.ThemeMode
import com.example.logointerpreterbeta.domain.models.Config
import com.example.logointerpreterbeta.domain.repository.ConfigRepository
import jakarta.inject.Inject
import jakarta.inject.Singleton
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

@Singleton
class ConfigRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : ConfigRepository {

    override fun readTheme(): Flow<String> =
        dataStore.data.map { prefs ->
            prefs[ConfigKeys.THEME] ?: "System"
        }.catch { exception ->
            emit("System")
        }

    override suspend fun getCurrentTheme(): ThemeMode {
        return dataStore.data.map { preferences ->
            preferences[THEME]?.let {
                ThemeMode.fromString(it)
            } ?: ThemeMode.SYSTEM_THEME
        }.first()
    }

    override suspend fun updateTheme(newTheme: ThemeMode) {
        dataStore.edit { preferences ->
            preferences[THEME] = newTheme.toString()
        }
    }

    override suspend fun updateFont(newFont: String) {
        dataStore.edit { it[ConfigKeys.FONT] = newFont }
    }

    override suspend fun updateFontSize(newFontSize: Int) {
        dataStore.edit { it[ConfigKeys.FONT_SIZE] = newFontSize }
    }

    override suspend fun updateShowSuggestions(newShowSuggestions: Boolean) {
        dataStore.edit { it[ConfigKeys.SHOW_SUGGESTIONS] = newShowSuggestions }
    }

    override suspend fun updateUseAutocorrect(newUseAutocorrect: Boolean) {
        dataStore.edit { it[ConfigKeys.USE_AUTOCORRECT] = newUseAutocorrect }
    }

    override fun readSettings(): Flow<Config> =
        dataStore.data.map { prefs ->
            Config(
                currentTheme = prefs[ConfigKeys.THEME] ?: "System",
                currentFont = prefs[ConfigKeys.FONT] ?: "default",
                currentFontSize = prefs[ConfigKeys.FONT_SIZE] ?: 14,
                showSuggestions = prefs[ConfigKeys.SHOW_SUGGESTIONS] ?: true,
                useAutocorrect = prefs[ConfigKeys.USE_AUTOCORRECT] ?: false
            )
        }
}