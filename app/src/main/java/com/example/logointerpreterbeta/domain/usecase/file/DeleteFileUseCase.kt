package com.example.logointerpreterbeta.domain.usecase.file

import com.example.logointerpreterbeta.domain.repository.FileRepository
import jakarta.inject.Inject

class DeleteFileUseCase @Inject constructor(
    private val fileRepository: FileRepository
) {
    operator fun invoke(fileName: String, projectName: String) {
        fileRepository.deleteFile(fileName, projectName)
    }
}