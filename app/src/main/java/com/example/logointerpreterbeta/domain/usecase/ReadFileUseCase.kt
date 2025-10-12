package com.example.logointerpreterbeta.domain.usecase

import com.example.logointerpreterbeta.domain.repository.FileRepository
import jakarta.inject.Inject
import jakarta.inject.Singleton

@Singleton
class ReadFileUseCase @Inject constructor(
    private val fileRepository: FileRepository
) {
    operator fun invoke(
        fileName: String,
        projectName: String
    ): Result<String> {
            val content = fileRepository.readFileContent(fileName, projectName)
            return if (content.isSuccess) {
                Result.success(content.getOrThrow())
            } else {
                Result.failure(content.exceptionOrNull() ?: Exception("Unknown error"))
            }
    }
}