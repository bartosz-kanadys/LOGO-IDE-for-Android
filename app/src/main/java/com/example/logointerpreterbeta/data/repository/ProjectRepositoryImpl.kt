package com.example.logointerpreterbeta.data.repository

import android.content.Context
import android.icu.text.SimpleDateFormat
import android.util.Log
import com.example.logointerpreterbeta.domain.models.Library
import com.example.logointerpreterbeta.domain.models.Project
import com.example.logointerpreterbeta.domain.models.ProjectFile
import com.example.logointerpreterbeta.domain.repository.FileRepository
import com.example.logointerpreterbeta.domain.repository.ProjectRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import jakarta.inject.Inject
import jakarta.inject.Singleton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import java.io.File
import java.util.Date
import java.util.Locale

@Singleton
class ProjectRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val externalScope: CoroutineScope,
    private val fileRepository: FileRepository
): ProjectRepository {

    private val mutex = Mutex()
    private val projectsFolder = File(context.getExternalFilesDir(null), "Projects")

    private val _projectsMapFlow = MutableStateFlow<Map<String, String>>(emptyMap())

    private val _projectFlow = MutableStateFlow<Project?>(null)

    init {
        if (!projectsFolder.exists()) {
            projectsFolder.mkdirs()
        }
        externalScope.launch{
            refreshProjectsMap()
        }
    }

    override fun getProjectsMap(): Flow<Map<String, String>> = _projectsMapFlow.asStateFlow()

    override fun getProject(name: String): Flow<Project?> {
        // Odśwież dane dla tego konkretnego projektu
        CoroutineScope(Dispatchers.IO).launch {
            refreshProject(name)
        }
        return _projectFlow.asStateFlow()
    }

    override suspend fun createNewProject(name: String): Boolean = withContext(Dispatchers.IO) {
        val newProjectFolder = File(projectsFolder, name)
        if (newProjectFolder.exists()) {
            return@withContext false
        }
        val success = newProjectFolder.mkdirs()
        if (success) {
            refreshProjectsMap() // Odśwież mapę projektów
        }
        return@withContext success
    }

    override suspend fun deleteProject(name: String) = withContext(Dispatchers.IO) {
        val projectFolder = File(projectsFolder, name)
        if (projectFolder.exists()) {
            projectFolder.deleteRecursively()
            refreshProjectsMap()
            // Jeśli usunięty projekt był aktualnie załadowany, wyczyść go
            if (_projectFlow.value?.name == name) {
                _projectFlow.value = null
            }
        }
    }

    override suspend fun createFile(
        projectName: String,
        fileName: String,
        content: String
    ): Boolean {
        if (fileRepository.createFile(projectName, fileName, content)){
            refreshProject(projectName)
            return true
        } else {
            return false
        }
    }


    override suspend fun deleteFile(
        projectName: String,
        fileName: String
    ): Boolean {
        if (fileRepository.deleteFile(projectName, fileName)){
            refreshProject(projectName)
            return true
        } else {
            return false
        }
    }

    suspend fun refreshProjectsMap() = mutex.withLock {
        val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        val updatedMap = projectsFolder.listFiles { file -> file.isDirectory }
            ?.associate { it.name to dateFormat.format(Date(it.lastModified())) } ?: emptyMap()
        _projectsMapFlow.value = updatedMap
    }

    private suspend fun refreshProject(name: String) = mutex.withLock {
        val directory = File(projectsFolder, name)
        if (!directory.exists()) {
            _projectFlow.value = null
            return@withLock
        }

        val projectFiles = directory.listFiles { file -> file.extension == "txt" }?.map {
            ProjectFile(name = it.name, pathToFile = it.absolutePath, fileType = it.extension)
        } ?: emptyList()

        _projectFlow.value = Project(
            name = directory.name,
            pathToFolder = directory.absolutePath,
            files = projectFiles
        )
    }
}
