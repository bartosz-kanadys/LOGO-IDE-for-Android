package com.example.logointerpreterbeta.domain.usecase.file

import com.example.logointerpreterbeta.domain.repository.FileRepository
import jakarta.inject.Inject
import jakarta.inject.Singleton

@Singleton
class SaveFileUseCase @Inject constructor(
    private val fileRepository: FileRepository
) {
    operator fun invoke(
        fileName: String,
        projectName: String,
        content: String
    ): Result<Unit> {
        return try {
            fileRepository.writeFileContent(fileName, projectName, content)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}