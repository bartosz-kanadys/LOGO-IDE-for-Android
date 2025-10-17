package com.example.logointerpreterbeta.domain.usecase.library

import com.example.logointerpreterbeta.domain.repository.LibraryRepository
import jakarta.inject.Inject
import jakarta.inject.Singleton

@Singleton
class DeleteLibraryUseCase @Inject constructor(
    private val libraryRepository: LibraryRepository
) {
    suspend operator fun invoke(libraryName: String): Result<Unit> {
        try {
            libraryRepository.deleteLibrary(libraryName)
            return Result.success(Unit)
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }
}