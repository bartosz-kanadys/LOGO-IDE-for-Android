package com.example.logointerpreterbeta.domain.usecase

import com.example.logointerpreterbeta.domain.repository.FileRepository
import javax.inject.Inject

class DeleteFileUseCase @Inject constructor(
    private val fileRepository: FileRepository
) {
    operator fun invoke(fileName: String, projectName: String) {
        fileRepository.deleteFile(fileName, projectName)
    }
}