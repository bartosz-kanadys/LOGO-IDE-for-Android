package com.example.logointerpreterbeta.viewModels

import androidx.lifecycle.ViewModel
import com.example.logointerpreterbeta.repository.ProjectRepository
import com.example.logointerpreterbeta.functions.project.Project
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ProjectViewModel @Inject constructor(
    private val projectRepository: ProjectRepository
) : ViewModel() {
    private val _actualProjectName = MutableStateFlow("")
    val actualProjectName = _actualProjectName.asStateFlow()

    private val _actualFileName = MutableStateFlow<String?>(null)
    val actualFileName = _actualFileName.asStateFlow()

    private val _projectsMap = MutableStateFlow<Map<String, String>>(emptyMap())
    val projectsMap = _projectsMap.asStateFlow()

    private val _project = MutableStateFlow<Project?>(null)
    val project = _project.asStateFlow()

    init {
        updateProjectsMap()
    }

    private fun updateActualProjectName(newProjectName: String) {
        _actualProjectName.value = newProjectName
        projectRepository.updateLastProjectJSON(newProjectName)
    }

    fun loadLastProjectFromJSON() {
        _actualProjectName.value = projectRepository.readLastProjectJSON()!!
    }

    fun updateActualFileName(newFileName: String?) {
        _actualFileName.value = newFileName
    }

    private fun updateProjectsMap() {
        _projectsMap.value = projectRepository.getProjectsMap()
    }

    fun updateProject() {
        _project.value = projectRepository.getProject(_actualProjectName.value)
    }

    fun deleteFileFromProject(fileToDelete: String): Boolean {
        projectRepository.deleteFile(_project.value!!.name, fileToDelete)
        updateProject()
        // Wyczyść nazwę po usunięciu
        if (project.value!!.files.isEmpty()) {
            updateActualFileName(null)
            return true
        } else {
            updateActualFileName(project.value?.files?.firstOrNull()?.name)
            return false
        }
    }

    fun createFileInProject(newFileName: String) {
        projectRepository.createFile(_actualProjectName.value, newFileName, "")
        updateProject()
    }

    fun createFileInEmptyProject(newFileName: String) {
        createFileInProject(newFileName)
        if (project.value!!.files.isNotEmpty()) {
            updateActualFileName(project.value?.files?.firstOrNull()?.name)
        } else {
            updateActualFileName(null)
        }
    }

    fun deleteProjectFromList(projectToDelete: String) {
        if (_actualProjectName.value == projectToDelete) {
            updateActualProjectName("")
            updateActualFileName(null)
        }
        projectRepository.deleteProject(projectToDelete)
        updateProjectsMap()
        projectRepository.updateLastProjectJSON("")
    }

    fun createNewProject(newProjectName: String): Boolean {
        val isCreated = !projectRepository.createNewProject(newProjectName)
        updateActualProjectName(newProjectName)
        updateProjectsMap()
        updateProject()
        return isCreated
    }

    fun openProject(name: String) {
        updateActualProjectName(name)
        projectRepository.updateLastProjectJSON(name)
        updateProject()
    }
}