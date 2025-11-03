package com.example.logointerpreterbeta.domain.usecase.config

import com.example.logointerpreterbeta.domain.repository.ConfigRepository
import com.example.logointerpreterbeta.domain.repository.SessionRepository
import jakarta.inject.Inject

class UpdateLastProjectUseCase  @Inject constructor(
    private val sessionRepository: SessionRepository
) {
    suspend operator fun invoke(projectName: String) {
        sessionRepository.saveLastOpenedProject(projectName)
    }
}