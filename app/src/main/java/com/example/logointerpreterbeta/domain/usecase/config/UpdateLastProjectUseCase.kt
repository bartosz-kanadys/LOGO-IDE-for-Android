package com.example.logointerpreterbeta.domain.usecase.config

import com.example.logointerpreterbeta.domain.repository.ConfigRepository
import jakarta.inject.Inject

class UpdateLastProjectUseCase  @Inject constructor(
    private val configRepository: ConfigRepository
) {
    suspend operator fun invoke(projectName: String) {
        configRepository.updateLastProject(projectName)
    }
}