package com.example.logointerpreterbeta.ui.screens.projects

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.logointerpreterbeta.domain.models.Project
import com.example.logointerpreterbeta.domain.usecase.config.ReadLastProjectUseCase
import com.example.logointerpreterbeta.domain.usecase.config.UpdateLastProjectUseCase
import com.example.logointerpreterbeta.domain.usecase.project.CreateProjectFileUseCase
import com.example.logointerpreterbeta.domain.usecase.project.CreateProjectResult
import com.example.logointerpreterbeta.domain.usecase.project.CreateProjectUseCase
import com.example.logointerpreterbeta.domain.usecase.project.DeleteProjectFileUseCase
import com.example.logointerpreterbeta.domain.usecase.project.DeleteProjectUseCase
import com.example.logointerpreterbeta.domain.usecase.project.GetProjectUseCase
import com.example.logointerpreterbeta.domain.usecase.project.GetProjectsMapUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class ProjectUiState(
    val actualProjectName: String = "",
    val actualFileName: String? = null,
    val projectsMap: Map<String, String> = emptyMap(),
    val project: Project? = null,
    val isCreatingNewProject: Boolean = false,
    val newProjectName: String = "",
    val alertState: AlertState = AlertState.None,
)

sealed class AlertState {
    data object None : AlertState() // Brak alertu
    data class Success(val name: String) : AlertState()
    data object NameEmpty : AlertState()
    data object NameTooLong : AlertState()
    data object ProjectExists : AlertState()
    data object GenericError : AlertState()
    data class ConfirmDelete(val projectName: String) : AlertState()
}

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class ProjectViewModel @Inject constructor(
    private val createProjectUseCase: CreateProjectUseCase,
    private val deleteProjectUseCase: DeleteProjectUseCase,
    private val createProjectFileUseCase: CreateProjectFileUseCase,
    private val deleteProjectFileUseCase: DeleteProjectFileUseCase,
    private val readLastProjectUseCase: ReadLastProjectUseCase,
    private val updateLastProjectUseCase: UpdateLastProjectUseCase,
    private val getProjectUseCase: GetProjectUseCase,
    private val getProjectsMapUseCase: GetProjectsMapUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProjectUiState())
    val uiState: StateFlow<ProjectUiState> = _uiState.asStateFlow()

    init {
        // Wczytanie początkowego stanu (ostatnio otwartego projektu)
        viewModelScope.launch {
            val lastProject = readLastProjectUseCase() ?: ""
            _uiState.update { it.copy(actualProjectName = lastProject) }
        }

        viewModelScope.launch {
            getProjectsMapUseCase().collect { projectsMap ->
                _uiState.update { it.copy(projectsMap = projectsMap) }
            }
        }

        viewModelScope.launch {
            _uiState
                .map { it.actualProjectName } // tylko zmiana nazwy
                .distinctUntilChanged()       // Reaguj tylko, gdy nazwa faktycznie się zmieni
                .flatMapLatest { name ->      // Anuluj poprzednie i pobierz nowe dane
                    if (name.isNotBlank()) getProjectUseCase(name) else flowOf(null)
                }
                .collect { projectData ->
                    _uiState.update { it.copy(project = projectData) }
                }
        }
    }

    fun updateActualProjectName(newProjectName: String) {
        _uiState.update { it.copy(actualProjectName = newProjectName) }
        viewModelScope.launch {
            updateLastProjectUseCase(newProjectName)
        }
    }

    fun updateActualFileName(newFileName: String?) {
        _uiState.update { it.copy(actualFileName = newFileName) }
    }

    fun onNewProjectNameChange(name: String) {
        _uiState.update { it.copy(newProjectName = name) }
    }

    fun toggleCreateNewProject() {
        _uiState.update {
            it.copy(
                isCreatingNewProject = !it.isCreatingNewProject,
                newProjectName = ""
            )
        }
    }

    fun deleteFile(fileToDelete: String) {
        viewModelScope.launch {
            val currentState = uiState.value
            val actualProject = currentState.actualProjectName
            if (actualProject.isBlank()) return@launch

            deleteProjectFileUseCase(actualProject, fileToDelete)

            if (currentState.actualFileName == fileToDelete) {
                // Logika wyboru nowego aktywnego pliku powinna opierać się na stanie PRZED usunięciem
                val newFileToOpen = currentState.project?.files
                    ?.filterNot { it.name == fileToDelete } // Logicznie usuń plik z listy w pamięci
                    ?.firstOrNull()?.name
                updateActualFileName(newFileToOpen)
            }
        }
    }

    fun createFile(newFileName: String) {
        viewModelScope.launch {
            val actualProject = uiState.value.actualProjectName

            val createdFileName = createProjectFileUseCase(actualProject, newFileName, "")

            createdFileName.onSuccess {
                updateActualFileName(createdFileName.getOrThrow())
            }
            createdFileName.onFailure {
                _uiState.update { it.copy(alertState = AlertState.GenericError) }
            }
        }
    }

    fun deleteProjectFromList(projectToDelete: String) {
        viewModelScope.launch {
            val currentProject = uiState.value.actualProjectName

            if (currentProject == projectToDelete) {
                updateActualProjectName("")
            }

            deleteProjectUseCase(projectToDelete, currentProject)
        }
    }

    fun createNewProject() {
        viewModelScope.launch {
            val result = createProjectUseCase(_uiState.value.newProjectName)
            when (result) {
                CreateProjectResult.EmptyName -> _uiState.update { it.copy(alertState = AlertState.NameEmpty) }
                CreateProjectResult.NameAlreadyExists -> _uiState.update { it.copy(alertState = AlertState.ProjectExists) }
                CreateProjectResult.Success -> _uiState.update {
                    it.copy(
                        alertState = AlertState.Success(
                            _uiState.value.newProjectName
                        )
                    )
                }

                CreateProjectResult.TooLongName -> _uiState.update { it.copy(alertState = AlertState.NameTooLong) }
            }
        }
    }

    fun onDeleteProjectClicked(projectName: String) {
        _uiState.update { it.copy(alertState = AlertState.ConfirmDelete(projectName)) }
    }

    fun dismissAlert() {
        _uiState.update { it.copy(alertState = AlertState.None) }
    }

    fun openProject(name: String) {
        viewModelScope.launch {
            updateLastProjectUseCase(name)
        }
    }
}