package com.example.logointerpreterbeta.data.repository

import android.content.Context
import android.content.res.Configuration
import com.example.logointerpreterbeta.domain.repository.SystemThemeProvider
import dagger.hilt.android.qualifiers.ApplicationContext
import jakarta.inject.Inject

class AndroidSystemThemeProvider @Inject constructor(
    @ApplicationContext private val context: Context
) : SystemThemeProvider {

    override fun isSystemDarkTheme(): Boolean {
        val configuration = context.resources.configuration
        return (configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES
    }
}