package com.example.logointerpreterbeta.domain.usecase.project

import com.example.logointerpreterbeta.domain.repository.ProjectRepository
import jakarta.inject.Inject

class DeleteProjectFileUseCase @Inject constructor(
    private val projectRepository: ProjectRepository
) {
    suspend operator fun invoke(projectName: String, fileName: String): Result<String> {
        val success = projectRepository.deleteFile(projectName, fileName)

        return if (success) {
            Result.success(fileName)
        } else {
            Result.failure(Exception("Nie udało się usunąć pliku."))
        }
    }
}