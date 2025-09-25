package com.example.logointerpreterbeta.ui.screens.projects

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.logointerpreterbeta.domain.models.Project
import com.example.logointerpreterbeta.domain.repository.ConfigRepository
import com.example.logointerpreterbeta.domain.repository.FileRepository
import com.example.logointerpreterbeta.domain.repository.ProjectRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ProjectUiState(
    val actualProjectName: String = "",
    val actualFileName: String? = null,
    val projectsMap: Map<String, String> = emptyMap(),
    val project: Project? = null
)

@HiltViewModel
class ProjectViewModel @Inject constructor(
    private val projectRepository: ProjectRepository,
    private val configRepository: ConfigRepository,
    private val fileRepository: FileRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(ProjectUiState())
    val uiState = _uiState.asStateFlow()

    init {
        updateProjectsMap()
    }

    fun updateActualProjectName(newProjectName: String) {
        _uiState.update { it.copy(actualProjectName = newProjectName) }
        viewModelScope.launch {
            configRepository.updateLastProject(newProjectName)
        }
    }

    fun loadLastProjectFromJSON() {
        _uiState.update {
            it.copy(actualProjectName = configRepository.readLastProject().toString())
        }
    }

    fun updateActualFileName(newFileName: String?) {
        _uiState.update { it.copy(actualFileName = newFileName) }
    }

    private fun updateProjectsMap() {
        _uiState.update { it.copy(projectsMap = projectRepository.getProjectsMap()) }
    }

    fun updateProject() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(project = projectRepository.getProject(_uiState.value.actualProjectName))
            }
        }
    }

    fun deleteFileFromProject(fileToDelete: String): Boolean {
        fileRepository.deleteFile(
            _uiState.value.project?.name ?: "",
            fileToDelete
        )
        updateProject()
        if (_uiState.value.project!!.files.isEmpty()) {
            updateActualFileName(null)
            return true
        } else {
            updateActualFileName(_uiState.value.project!!.files.firstOrNull()?.name)
            return false
        }
    }

    fun createFileInProject(newFileName: String) {
        fileRepository.createFile(_uiState.value.actualProjectName, newFileName, "")
        updateProject()
    }

    fun createFileInEmptyProject(newFileName: String) {
        createFileInProject(newFileName)
        if (_uiState.value.project!!.files.isNotEmpty()) {
            updateActualFileName(_uiState.value.project?.files?.firstOrNull()?.name)
        } else {
            updateActualFileName(null)
        }
    }

    fun deleteProjectFromList(projectToDelete: String) {
        if (_uiState.value.actualProjectName == projectToDelete) {
            updateActualProjectName("")
            updateActualFileName(null)
        }
        projectRepository.deleteProject(projectToDelete)
        updateProjectsMap()
        viewModelScope.launch {
            configRepository.updateLastProject("")
        }
    }

    fun createNewProject(newProjectName: String): Boolean {
        val isCreated = projectRepository.createNewProject(newProjectName)
        updateActualProjectName(newProjectName)
        updateProjectsMap()
        updateProject()
        return isCreated
    }

    fun openProject(name: String) {
        updateActualProjectName(name)
        viewModelScope.launch {
            configRepository.updateLastProject(name)
        }
        updateProject()
    }
}