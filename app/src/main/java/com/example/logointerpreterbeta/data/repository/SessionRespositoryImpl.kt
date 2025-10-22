package com.example.logointerpreterbeta.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.example.logointerpreterbeta.data.dataStore.ConfigKeys
import com.example.logointerpreterbeta.domain.repository.SessionRepository
import jakarta.inject.Inject
import jakarta.inject.Singleton
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@Singleton
class SessionRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
): SessionRepository {
    override fun getLastOpenedFile(): Flow<Pair<String, String>?> {
        return dataStore.data.map { prefs ->
            val project = prefs[ConfigKeys.LAST_PROJECT_NAME]
            val file = prefs[ConfigKeys.LAST_FILE_NAME]
            if (project != null && file != null) {
                Pair(project, file)
            } else {
                null
            }
        }
    }

    override suspend fun saveLastOpenedFile(projectName: String, fileName: String) {
        dataStore.edit { prefs ->
            prefs[ConfigKeys.LAST_PROJECT_NAME] = projectName
            prefs[ConfigKeys.LAST_FILE_NAME] = fileName
        }
    }

    override fun getLastOpenedProject(): Flow<String> {
        return dataStore.data.map { prefs ->
            prefs[ConfigKeys.LAST_PROJECT_NAME] ?: ""
        }
    }

    override suspend fun saveLastOpenedProject(projectName: String) {
        dataStore.edit { prefs ->
            prefs[ConfigKeys.LAST_PROJECT_NAME] = projectName
        }
    }
}