package com.example.logointerpreterbeta.domain.usecase

import com.example.logointerpreterbeta.domain.repository.ThemeRepository
import jakarta.inject.Inject
import jakarta.inject.Singleton

@Singleton
class ThemeModeCheckUseCase @Inject constructor(
    private val themeRepository: ThemeRepository
) {
    operator fun invoke(): Boolean {
        return themeRepository.isDarkTheme()
    }
}