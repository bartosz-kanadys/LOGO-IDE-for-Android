package com.example.logointerpreterbeta.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Config(
    val lastModifiedProject: String = "",
    val currentTheme: String = "Systemowy",
    val currentFont: String = "JetBrains Mono",
): Parcelable
