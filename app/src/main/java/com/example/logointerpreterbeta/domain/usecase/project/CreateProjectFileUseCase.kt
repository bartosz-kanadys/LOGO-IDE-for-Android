package com.example.logointerpreterbeta.domain.usecase.project

import com.example.logointerpreterbeta.domain.repository.ProjectRepository
import jakarta.inject.Inject

class CreateProjectFileUseCase @Inject constructor(
    private val projectRepository: ProjectRepository
) {
    suspend operator fun invoke(projectName: String, fileName: String, content: String): Result<String> {
        val success = projectRepository.createFile(projectName, fileName, content)

        return if (success) {
            Result.success(fileName)
        } else {
            Result.failure(Exception("Nie udało się utworzyć pliku."))
        }
    }
}