package com.example.logointerpreterbeta.domain.usecase.library

import com.example.logointerpreterbeta.domain.models.Library
import com.example.logointerpreterbeta.domain.repository.LibraryRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetLibrariesUseCase @Inject constructor(
    private val libraryRepository: LibraryRepository
) {
    operator fun invoke(): Flow<List<Library>> {
        return libraryRepository.getLibraries()
    }
}