package com.example.logointerpreterbeta.domain.usecase.settings

import com.example.logointerpreterbeta.domain.repository.ThemeRepository
import com.example.logointerpreterbeta.ui.theme.ThemeMode
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetThemePreferenceUseCase @Inject constructor(
    private val themeRepository: ThemeRepository
) {
    operator fun invoke(): Flow<ThemeMode> {
        return themeRepository.observeTheme()
    }
}