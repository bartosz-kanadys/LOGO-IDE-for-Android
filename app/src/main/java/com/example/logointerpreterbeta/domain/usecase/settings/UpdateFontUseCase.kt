package com.example.logointerpreterbeta.domain.usecase.settings

import com.example.logointerpreterbeta.domain.enums.FontsEnum
import com.example.logointerpreterbeta.domain.repository.ConfigRepository
import jakarta.inject.Inject

class UpdateFontUseCase @Inject constructor(
    private val configRepository: ConfigRepository
) {
    suspend operator fun invoke(newFont: String): Result<Unit> {
        val valid = FontsEnum.entries.any { it.value == newFont }

        return try {
            if (valid) {
                configRepository.updateFont(newFont)
                return Result.success(Unit)
            } else {
                return Result.failure(IllegalArgumentException("Invalid font"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}