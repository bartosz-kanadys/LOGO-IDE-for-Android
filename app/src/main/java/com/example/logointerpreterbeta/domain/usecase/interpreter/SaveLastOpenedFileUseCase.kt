package com.example.logointerpreterbeta.domain.usecase.interpreter

import com.example.logointerpreterbeta.domain.repository.SessionRepository
import jakarta.inject.Inject

class SaveLastOpenedFileUseCase @Inject constructor(
    private val sessionRepository: SessionRepository
) {
    suspend operator fun invoke(projectName: String, fileName: String): Result<Unit> {
        return try {
            sessionRepository.saveLastOpenedFile(projectName, fileName)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}