package com.example.logointerpreterbeta.viewModels

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.logointerpreterbeta.functions.config.updateLastModifiedProjectJSON
import com.example.logointerpreterbeta.functions.project.Project
import com.example.logointerpreterbeta.functions.project.createFile
import com.example.logointerpreterbeta.functions.project.createProject
import com.example.logointerpreterbeta.functions.project.deleteFile
import com.example.logointerpreterbeta.functions.project.deleteProject
import com.example.logointerpreterbeta.functions.project.getProjectFoldersMap
import com.example.logointerpreterbeta.functions.project.getProjectFromDirectory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.io.File

class ProjectViewModel(context: Context) : ViewModel() {
    private val _actualProjectName = MutableStateFlow("")
    val actualProjectName: StateFlow<String> = _actualProjectName

    private val _actualFileName = MutableStateFlow<String?>(null)
    val actualFileName: StateFlow<String?> = _actualFileName

    private val _projectsMap = MutableStateFlow(getProjectFoldersMap(context))
    val projectsMap: StateFlow<Map<String, String>> = _projectsMap

    private val _project = MutableStateFlow<Project?>(
        null
//        getProjectFromDirectory(
        //File(context.getExternalFilesDir(null),"Projects/${_actualProjectName.value}"))
    )
    val project: StateFlow<Project?> = _project

    fun updateActualProjectName(context: Context, newProjectName: String) {
        _actualProjectName.value = newProjectName
        updateLastModifiedProjectJSON(context, newProjectName)
    }

    fun updateActualFileName(newFileName: String?) {
        _actualFileName.value = newFileName
    }

    fun updateProjectsMap(context: Context) {
        _projectsMap.value = getProjectFoldersMap(context)
    }

    fun updateProject(context: Context) {
        _project.value = getProjectFromDirectory(
            File(context.getExternalFilesDir(null), "Projects/${_actualProjectName.value}")
        )
    }

    fun deleteFileFromProject(context: Context, fileToDelete: String): Boolean {
        deleteFile(
            fileToDelete,
            _project.value!!.name,
            context
        )
        updateProject(context)
        // Wyczyść nazwę po usunięciu
        if (project.value!!.files.isEmpty()) {
            updateActualFileName(null)
            return true
        } else {
            updateActualFileName(project.value?.files?.firstOrNull()?.name)
            return false
        }
    }

    fun createFileInProject(context: Context, newFileName: String) {
        createFile(newFileName, _actualProjectName.value, "", context)
        updateProject(context)
    }

    fun createFileInEmptyProject(context: Context, newFileName: String) {
        createFileInProject(context, newFileName)
        if (project.value!!.files.isNotEmpty()) {
            updateActualFileName(project.value?.files?.firstOrNull()?.name)
        } else {
            updateActualFileName(null)
        }
    }

    fun deleteProjectFromList(context: Context, projectToDelete: String) {
        if (_actualProjectName.value == projectToDelete) {
            updateActualProjectName(context, "")
            updateActualFileName(null)
        }
        deleteProject(projectToDelete, context)
        updateProjectsMap(context)
        updateLastModifiedProjectJSON(context, "")
    }

    fun createNewProject(context: Context, newProjectName: String): Boolean {
        val isCreated = !createProject(newProjectName, context)
        updateActualProjectName(context, newProjectName)
        updateProjectsMap(context)
        updateProject(context)
        return isCreated
    }

    fun openProject(context: Context, name: String) {
        updateActualProjectName(context, name)
        updateLastModifiedProjectJSON(context, name)
        updateProject(context)
    }
}