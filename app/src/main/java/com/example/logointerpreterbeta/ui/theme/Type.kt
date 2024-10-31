package com.example.logointerpreterbeta.ui.theme


import android.annotation.SuppressLint
import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

import androidx.compose.ui.text.googlefonts.GoogleFont
//import androidx.compose.ui.text.googlefonts.Font
import com.example.logointerpreterbeta.R

@SuppressLint("ResourceType")
val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.xml.font_certs
)

val bodyFontFamily = FontFamily(
    Font(R.font.jet_brains_mono_regular, FontWeight.Normal)
//    Font(
//        googleFont = GoogleFont("JetBrains Mono"),
//        fontProvider = provider,
//    )
)

val displayFontFamily = FontFamily(
    Font(R.font.jet_brains_mono_regular, FontWeight.Normal)
//    Font(
//        Font(R.font.jet_brains_mono_regular, FontWeight.Normal)
//        googleFont = GoogleFont("JetBrains Mono"),
//        fontProvider = provider,
//    )
)

// Default Material 3 typography values
val baseline = Typography()

val AppTypography = Typography(
    displayLarge = baseline.displayLarge.copy(fontFamily = displayFontFamily),
    displayMedium = baseline.displayMedium.copy(fontFamily = displayFontFamily),
    displaySmall = baseline.displaySmall.copy(fontFamily = displayFontFamily),
    headlineLarge = baseline.headlineLarge.copy(fontFamily = displayFontFamily),
    headlineMedium = baseline.headlineMedium.copy(fontFamily = displayFontFamily),
    headlineSmall = baseline.headlineSmall.copy(fontFamily = displayFontFamily),
    titleLarge = baseline.titleLarge.copy(fontFamily = displayFontFamily),
    titleMedium = baseline.titleMedium.copy(fontFamily = displayFontFamily),
    titleSmall = baseline.titleSmall.copy(fontFamily = displayFontFamily),
    bodyLarge = baseline.bodyLarge.copy(fontFamily = bodyFontFamily),
    bodyMedium = baseline.bodyMedium.copy(fontFamily = bodyFontFamily),
    bodySmall = baseline.bodySmall.copy(fontFamily = bodyFontFamily),
    labelLarge = baseline.labelLarge.copy(fontFamily = bodyFontFamily),
    labelMedium = baseline.labelMedium.copy(fontFamily = bodyFontFamily),
    labelSmall = baseline.labelSmall.copy(fontFamily = bodyFontFamily),
)

//val jetBrainsMono = FontFamily(
//    Font(R.font.jet_brains_mono_regular, FontWeight.Normal)
//)