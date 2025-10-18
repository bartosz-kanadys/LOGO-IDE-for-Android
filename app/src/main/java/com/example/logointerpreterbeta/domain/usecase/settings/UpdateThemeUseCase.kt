package com.example.logointerpreterbeta.domain.usecase.settings

import com.example.logointerpreterbeta.domain.enums.ThemeMode
import com.example.logointerpreterbeta.domain.repository.ThemeRepository
import jakarta.inject.Inject

class UpdateThemeUseCase @Inject constructor(
    private val themeRepository: ThemeRepository
) {
    suspend operator fun invoke(newTheme: ThemeMode): Result<Unit> {
        val valid = ThemeMode.entries.any { it == newTheme }
        return try {
            if (valid) {
                themeRepository.setCurrentTheme(newTheme)
                return Result.success(Unit)
            } else {
                return Result.failure(IllegalArgumentException("Invalid theme"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}