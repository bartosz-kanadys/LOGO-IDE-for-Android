package com.example.logointerpreterbeta.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.example.logointerpreterbeta.data.dataStore.ConfigKeys
import com.example.logointerpreterbeta.domain.models.Config
import com.example.logointerpreterbeta.domain.repository.ConfigRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ConfigRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : ConfigRepository {

    override suspend fun updateLastProject(newProjectName: String) {
        dataStore.edit { prefs ->
            prefs[ConfigKeys.LAST_PROJECT] = newProjectName
        }
    }

    override fun readLastProject(): Flow<String?> =
        dataStore.data.map { prefs -> prefs[ConfigKeys.LAST_PROJECT] }

    override fun readTheme(): Flow<String> =
        dataStore.data.map { prefs -> prefs[ConfigKeys.THEME] as String }

    override suspend fun updateTheme(newTheme: String) {
        dataStore.edit { it[ConfigKeys.THEME] = newTheme }
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
                lastModifiedProject = prefs[ConfigKeys.LAST_PROJECT] ?: "",
                currentTheme = prefs[ConfigKeys.THEME] ?: "light",
                currentFont = prefs[ConfigKeys.FONT] ?: "default",
                currentFontSize = prefs[ConfigKeys.FONT_SIZE] ?: 14,
                showSuggestions = prefs[ConfigKeys.SHOW_SUGGESTIONS] ?: true,
                useAutocorrect = prefs[ConfigKeys.USE_AUTOCORRECT] ?: false
            )
        }
}