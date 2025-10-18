package com.example.logointerpreterbeta.domain.usecase.settings

import com.example.logointerpreterbeta.domain.repository.ConfigRepository
import jakarta.inject.Inject

class UpdateAutocorrectUseCase @Inject constructor(
    private val configRepository: ConfigRepository
) {
    suspend operator fun invoke(newValue: Boolean): Result<Unit> {
        return try {
            configRepository.updateUseAutocorrect(newValue)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}