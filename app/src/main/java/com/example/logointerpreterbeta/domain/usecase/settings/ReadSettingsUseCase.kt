package com.example.logointerpreterbeta.domain.usecase.settings

import com.example.logointerpreterbeta.domain.models.Config
import com.example.logointerpreterbeta.domain.repository.ConfigRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class ReadSettingsUseCase @Inject constructor(
    private val configRepository: ConfigRepository
) {
    suspend operator fun invoke(): Result<Flow<Config>> {
        return try {
            val result = configRepository.readSettings()
            return Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}