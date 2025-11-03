package com.example.logointerpreterbeta.domain.usecase.shared

import com.example.logointerpreterbeta.domain.repository.ThemeRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class ObserveThemeUseCase @Inject constructor(
    private val themeRepository: ThemeRepository
) {
    operator fun invoke(): Flow<Boolean> {
        return themeRepository.observeThemeBoolean()
    }
}