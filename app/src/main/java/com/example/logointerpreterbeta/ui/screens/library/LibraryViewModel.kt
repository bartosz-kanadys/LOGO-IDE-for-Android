package com.example.logointerpreterbeta.ui.screens.library

import androidx.lifecycle.ViewModel
import com.example.logointerpreterbeta.domain.models.Library
import com.example.logointerpreterbeta.domain.models.Procedure
import com.example.logointerpreterbeta.domain.repository.LibraryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

enum class LibraryCodes {
    DESC_TOO_LONG,
    PROCEDURE_EXIST,
    FILL_ALL_FIELDS,
    LIBRARY_EXIST,
    OK
}

data class LibraryUiState(
    val libraries: List<Library> = emptyList(),
    val actualLibrary: String? = null
)

@HiltViewModel
class LibraryViewModel @Inject constructor(
    private val libraryRepository: LibraryRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<LibraryUiState>(LibraryUiState())
    val uiState = _uiState.asStateFlow()

    init {
        updateLibraries()
    }

    fun updateActualLibrary(name: String?) {
        _uiState.update { currentState ->
            currentState.copy(
                actualLibrary = name
            )
        }
    }

    fun addLibrary(library: Library) {
        _uiState.update { currentState ->
            currentState.copy(
                libraries = currentState.libraries + library
            )
        }
    }

    fun deleteLibrary(libraryName: String) {
        _uiState.update { currentState ->
            currentState.copy(
                libraries = currentState.libraries.filter { it.name != libraryName }
            )
        }
        libraryRepository.deleteLibrary(libraryName)
    }

    fun updateLibraries() {
        _uiState.update {
            it.copy(
                libraries = libraryRepository.loadLibraries()
            )
        }
    }

    fun createLibrary(name: String, desc: String, author: String): LibraryCodes {
        if (name.isEmpty() || desc.isEmpty() || author.isEmpty()) {
            return LibraryCodes.FILL_ALL_FIELDS
        } else if (desc.length > 50) {
            return LibraryCodes.DESC_TOO_LONG
        } else if (_uiState.value.libraries.find { it.name == name } != null) {
            return LibraryCodes.LIBRARY_EXIST
        }

        val library =
            Library(name = name, description = desc, author = author, procedures = emptyList())
        libraryRepository.createLibrary(library)
        addLibrary(library)
        return LibraryCodes.OK
    }

    fun checkProcedureAddForm(
        name: String,
        author: String,
        desc: String,
        code: String,
    ): LibraryCodes {
        if (name.isEmpty() || author.isEmpty() || desc.isEmpty() || code.isEmpty()) {
            return LibraryCodes.FILL_ALL_FIELDS
        } else if (_uiState.value.libraries.find {
                it.name == _uiState.value.actualLibrary
            }?.procedures?.find {
                it.name == name
            } != null
        ) {
            return LibraryCodes.PROCEDURE_EXIST
        }
        return LibraryCodes.OK
    }

    fun addProcedureToLibrary(libraryName: String, procedure: Procedure) {
        libraryRepository.addProcedureToLibrary(libraryName, procedure)
        updateLibraries()
    }

    fun deleteProcedureFromLibrary(libraryName: String, procedureName: String) {
        libraryRepository.deleteProcedureFromLibrary(libraryName, procedureName)
        updateLibraries()
    }
}