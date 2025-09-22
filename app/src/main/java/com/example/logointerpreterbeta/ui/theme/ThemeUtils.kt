package com.example.logointerpreterbeta.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.example.logointerpreterbeta.R

object ThemeUtils {
    @Composable
    fun isDarkTheme(themePreference: ThemeMode): Boolean {
        return when (themePreference) {
            ThemeMode.LIGHT_THEME -> false
            ThemeMode.DARK_THEME -> true
            ThemeMode.SYSTEM_THEME -> isSystemInDarkTheme()
        }
    }

    fun fontFamilyFromString(fontName: String): FontFamily? {
        return when (fontName) {
            "JetBrains Mono" -> AppTypography.bodySmall.fontFamily
            "Comic Sans MS" -> FontFamily(Font(R.font.comic_sans_ms))
            "Bebas Neue" -> FontFamily(Font(R.font.bebas_neue_regular))
            else -> AppTypography.bodySmall.fontFamily
        }
    }


//    @Composable
//    fun rememberIsDarkTheme(themePreference: ThemePreference): Boolean {
//        val isSystemDark = isSystemInDarkTheme()
//
//        return remember(themePreference, isSystemDark) {
//            when (themePreference) {
//                ThemePreference.LIGHT -> false
//                ThemePreference.DARK -> true
//                ThemePreference.SYSTEM -> isSystemDark
//            }
//        }
//    }
}