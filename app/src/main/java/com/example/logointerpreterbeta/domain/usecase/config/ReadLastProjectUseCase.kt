package com.example.logointerpreterbeta.domain.usecase.config

import com.example.logointerpreterbeta.domain.repository.ConfigRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class ReadLastProjectUseCase @Inject constructor(
    private val configRepository: ConfigRepository
) {
    suspend operator fun invoke(): String? {
        return configRepository.readLastProject().first()
    }
}