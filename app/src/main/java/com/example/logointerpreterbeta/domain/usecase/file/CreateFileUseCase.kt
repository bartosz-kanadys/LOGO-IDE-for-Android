package com.example.logointerpreterbeta.domain.usecase.file

import com.example.logointerpreterbeta.domain.repository.FileRepository
import jakarta.inject.Inject

class CreateFileUseCase @Inject constructor(
    private val fileRepository: FileRepository,
) {
    suspend operator fun invoke(
        fileName: String,
        projectName: String,
        content: String
    ): Result<String> {
        return try {
            val success = fileRepository.createFile(projectName, fileName, content)

            if (success) {
                Result.success(fileName)
            } else {
                Result.failure(Exception("Nie udało się utworzyć pliku."))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}