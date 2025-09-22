package com.example.logointerpreterbeta.data.dataStore

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object ConfigKeys {
    val LAST_PROJECT = stringPreferencesKey("last_project")
    val THEME = stringPreferencesKey("theme")
    val FONT = stringPreferencesKey("font")
    val FONT_SIZE = intPreferencesKey("font_size")
    val SHOW_SUGGESTIONS = booleanPreferencesKey("show_suggestions")
    val USE_AUTOCORRECT = booleanPreferencesKey("use_autocorrect")
}
