package com.example.logointerpreterbeta.domain.usecase.library

import com.example.logointerpreterbeta.domain.enums.LibraryCodes
import com.example.logointerpreterbeta.domain.models.Procedure
import com.example.logointerpreterbeta.domain.repository.LibraryRepository
import jakarta.inject.Inject
import jakarta.inject.Singleton


sealed class LibraryValidationResult(
    val code: LibraryCodes,
) {
    class Success : LibraryValidationResult(LibraryCodes.OK)
    class FillAllFields : LibraryValidationResult(LibraryCodes.FILL_ALL_FIELDS)
    class DescTooLong : LibraryValidationResult(LibraryCodes.DESC_TOO_LONG)
    class ProcedureExist : LibraryValidationResult(LibraryCodes.LIBRARY_EXIST)
    class UnknownError : LibraryValidationResult(LibraryCodes.UNKNOWN_ERROR)
}

@Singleton
class AddProcedureToLibraryUseCase @Inject constructor(
    private val libraryRepository: LibraryRepository
) {
    suspend operator fun invoke(
        libraryName: String,
        procedure: Procedure,
        author: String
    ): LibraryValidationResult {
        //Validation
        if (procedure.name.isEmpty() || author.isEmpty() || procedure.description.isEmpty() || procedure.code.isEmpty()) {
            return LibraryValidationResult.FillAllFields()
        }
        if (libraryRepository.procedureExistsInLibrary(libraryName, procedure.name)) {
            return LibraryValidationResult.ProcedureExist()
        }
        if (procedure.description.length > 25) {
            return LibraryValidationResult.DescTooLong()
        }

        //Adding procedure
        try {
            libraryRepository.addProcedureToLibrary(libraryName, procedure)
            return LibraryValidationResult.Success()
        } catch (e: Exception) {
            return LibraryValidationResult.UnknownError()
        }
    }
}