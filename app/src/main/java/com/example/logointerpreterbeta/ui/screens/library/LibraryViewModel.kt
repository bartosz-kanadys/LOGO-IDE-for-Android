package com.example.logointerpreterbeta.ui.screens.library

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.logointerpreterbeta.domain.enums.LibraryCodes
import com.example.logointerpreterbeta.domain.models.Library
import com.example.logointerpreterbeta.domain.models.Procedure
import com.example.logointerpreterbeta.domain.repository.LibraryRepository
import com.example.logointerpreterbeta.domain.usecase.library.AddProcedureToLibraryUseCase
import com.example.logointerpreterbeta.domain.usecase.library.CreateLibraryUseCase
import com.example.logointerpreterbeta.domain.usecase.library.DeleteLibraryUseCase
import com.example.logointerpreterbeta.domain.usecase.library.DeleteProcedureFromLibraryUseCase
import com.example.logointerpreterbeta.domain.usecase.library.LibraryCreateResult
import com.example.logointerpreterbeta.domain.usecase.library.LibraryValidationResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class LibraryUiState(
    val libraries: List<Library> = emptyList(),
    val actualLibrary: String? = null,
    val toastMessage: LibraryCodes? = null
)

@HiltViewModel
class LibraryViewModel @Inject constructor(
    private val libraryRepository: LibraryRepository,
    private val createLibraryUseCase: CreateLibraryUseCase,
    private val deleteLibraryUseCase: DeleteLibraryUseCase,
    private val addProcedureToLibraryUseCase: AddProcedureToLibraryUseCase,
    private val deleteProcedureFromLibraryUseCase: DeleteProcedureFromLibraryUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<LibraryUiState>(LibraryUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            libraryRepository.getLibraries().collect { libraries ->
                _uiState.update { it.copy(libraries = libraries) }
            }
        }
    }

    fun updateActualLibrary(name: String?) {
        _uiState.update { currentState ->
            currentState.copy(
                actualLibrary = name
            )
        }
    }

    fun deleteLibrary(libraryName: String) {
        viewModelScope.launch {
            val result = deleteLibraryUseCase(libraryName)
            result.onFailure {
                _uiState.update {
                    it.copy(toastMessage = LibraryCodes.UNKNOWN_ERROR)
                }
            }
        }
    }

    fun createLibrary(name: String, desc: String, author: String, onSuccess: () -> Unit){
        viewModelScope.launch {
            val result = createLibraryUseCase(name, desc, author)
            when (result) {
                is LibraryCreateResult.Success -> { onSuccess() }
                else -> {
                    _uiState.update {
                        it.copy(toastMessage = result.code)
                    }
                }
            }
        }
    }

    fun addProcedureToLibrary(libraryName: String, procedure: Procedure, author: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            if (_uiState.value.actualLibrary != null ) {
                val result = addProcedureToLibraryUseCase(libraryName, procedure, author)
                when (result) {
                    is LibraryValidationResult.Success -> { onSuccess() }
                    else -> {
                        _uiState.update {
                            it.copy(toastMessage = result.code)
                        }
                    }
                }
            }
        }
    }

    fun deleteProcedureFromLibrary(libraryName: String, procedureName: String) {
        viewModelScope.launch {
            val result = deleteProcedureFromLibraryUseCase(libraryName, procedureName)
            result.onFailure {
                _uiState.update {
                    it.copy(toastMessage = LibraryCodes.UNKNOWN_ERROR)
                }
            }
        }
    }

    fun toastMessageConsumed() {
        _uiState.update { it.copy(toastMessage = null) }
    }
}