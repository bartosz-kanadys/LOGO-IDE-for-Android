package com.example.logointerpreterbeta.domain.usecase.settings

import com.example.logointerpreterbeta.domain.repository.ConfigRepository
import jakarta.inject.Inject

class UpdateFontSizeUseCase @Inject constructor(
    private val configRepository: ConfigRepository,
) {
    suspend operator fun invoke(newFontSizeStr: String): Result<Unit> {
        val newSize = newFontSizeStr.toIntOrNull()?.coerceIn(8, 32)

        return try {
            if (newSize != null) {
                configRepository.updateFontSize(newSize)
                return Result.success(Unit)
            } else {
                return Result.failure(IllegalArgumentException("Invalid font size"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}