package com.example.logointerpreterbeta.ui.theme

enum class ThemeMode(val value: String) {
    SYSTEM_THEME ("System"),
    LIGHT_THEME("Light"),
    DARK_THEME("Dark");

    companion object {
        fun fromString(value: String): ThemeMode =
            entries.find { it.value == value } ?: SYSTEM_THEME
    }

    override fun toString(): String = value
}