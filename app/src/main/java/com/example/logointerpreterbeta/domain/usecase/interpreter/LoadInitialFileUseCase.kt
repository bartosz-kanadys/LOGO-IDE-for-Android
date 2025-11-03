package com.example.logointerpreterbeta.domain.usecase.interpreter

import com.example.logointerpreterbeta.domain.models.Project
import com.example.logointerpreterbeta.domain.repository.SessionRepository
import com.example.logointerpreterbeta.domain.usecase.file.ReadFileUseCase
import jakarta.inject.Inject
import kotlinx.coroutines.flow.firstOrNull

class LoadInitialFileUseCase @Inject constructor(
    private val sessionRepository: SessionRepository,
    private val readFilesUseCase: ReadFileUseCase
) {
    suspend operator fun invoke(project: Project): Result<Pair<String, String>> {
        // 1. Spróbuj załadować ostatnią sesję DLA TEGO PROJEKTU
        val lastFile = sessionRepository.getLastOpenedFile().firstOrNull()
        if (lastFile != null && project.name == lastFile.first) {
            val (projectName, fileName) = lastFile
            readFilesUseCase(fileName, projectName).onSuccess { code ->
                return Result.success(fileName to code)
            }
        }

        // 2. Załaduj pierwszy plik z projektu
        val firstFile = project.files.firstOrNull()
        if (firstFile != null) {
            readFilesUseCase(firstFile.name, project.name).onSuccess { code ->
                sessionRepository.saveLastOpenedFile(project.name, firstFile.name)
                return Result.success(firstFile.name to code)
            }
        }

        // 3. Brak plików lub błąd odczytu
        return Result.failure(Exception("No files available to load."))
    }
}