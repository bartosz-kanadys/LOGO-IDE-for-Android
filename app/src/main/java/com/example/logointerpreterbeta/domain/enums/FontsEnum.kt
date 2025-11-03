package com.example.logointerpreterbeta.domain.enums

enum class FontsEnum(val value: String) {
    JETBRAINS_MONO("JetBrains Mono"),
    COMIC_SANS_MS("Comic Sans MS"),
    BEBAS_NEUE("Bebas Neue");

    companion object {
        fun fromString(value: String): FontsEnum =
            entries.find { it.value == value } ?: JETBRAINS_MONO
    }

    override fun toString(): String = value
}