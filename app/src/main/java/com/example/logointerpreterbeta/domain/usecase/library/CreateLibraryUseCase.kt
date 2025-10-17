package com.example.logointerpreterbeta.domain.usecase.library

import com.example.logointerpreterbeta.domain.enums.LibraryCodes
import com.example.logointerpreterbeta.domain.models.Library
import com.example.logointerpreterbeta.domain.repository.LibraryRepository
import jakarta.inject.Inject
import jakarta.inject.Singleton

sealed class LibraryCreateResult(
    val code: LibraryCodes,
    val library: Library? = null
) {
    class Success(library: Library) : LibraryCreateResult(LibraryCodes.OK, library)
    class FillAllFields : LibraryCreateResult(LibraryCodes.FILL_ALL_FIELDS)
    class DescTooLong : LibraryCreateResult(LibraryCodes.DESC_TOO_LONG)
    class LibraryExist : LibraryCreateResult(LibraryCodes.LIBRARY_EXIST)
}

@Singleton
class CreateLibraryUseCase @Inject constructor(
    private val libraryRepository: LibraryRepository,
) {
    suspend operator fun invoke (name: String, desc: String, author: String): LibraryCreateResult {
        if (name.isEmpty() || desc.isEmpty() || author.isEmpty()) {
            return LibraryCreateResult.FillAllFields()
        } else if (desc.length > 50) {
            return LibraryCreateResult.DescTooLong()
        } else if (libraryRepository.libraryExists(name)) {
            return LibraryCreateResult.LibraryExist()
        }

        val library =
            Library(name = name, description = desc, author = author, procedures = emptyList())
        libraryRepository.createLibrary(library)
        return LibraryCreateResult.Success(library)
    }
}