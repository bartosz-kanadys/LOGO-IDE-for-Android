package com.example.logointerpreterbeta.data.repository

import android.content.ComponentCallbacks2
import android.content.Context
import android.content.res.Configuration
import com.example.logointerpreterbeta.domain.repository.SystemThemeProvider
import dagger.hilt.android.qualifiers.ApplicationContext
import jakarta.inject.Inject
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class AndroidSystemThemeProvider @Inject constructor(
    @ApplicationContext private val context: Context
) : SystemThemeProvider {

    override fun isSystemDarkTheme(): Boolean {
        val configuration = context.resources.configuration
        return (configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES
    }

    override fun observeSystemDarkTheme(): Flow<Boolean> = callbackFlow {
        // Zarejestruj listener zmian konfiguracji
        val callbacks = object : ComponentCallbacks2 {
            override fun onConfigurationChanged(newConfig: Configuration) {
                //Emituj nową wartość, gdy system się zmieni
                val isDark = (newConfig.uiMode and Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES
                trySend(isDark) // trySend jest bezpieczne z dowolnego wątku
            }
            override fun onLowMemory() {}
            override fun onTrimMemory(level: Int) {}
        }

        context.registerComponentCallbacks(callbacks)

        //Wyemituj aktualną wartość natychmiast po subskrypcji
        trySend(isSystemDarkTheme())

        //Odrejestruj listener, gdy Flow zostanie anulowany
        awaitClose {
            context.unregisterComponentCallbacks(callbacks)
        }
    }
}