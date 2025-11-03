package com.example.logointerpreterbeta.domain.usecase.config

import com.example.logointerpreterbeta.domain.repository.ConfigRepository
import com.example.logointerpreterbeta.domain.repository.SessionRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.first

class ReadLastProjectUseCase @Inject constructor(
    private val sessionRepository: SessionRepository
) {
    suspend operator fun invoke(): String? {
        return sessionRepository.getLastOpenedProject().first()
    }
}