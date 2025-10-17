package com.example.logointerpreterbeta.domain.repository

import com.example.logointerpreterbeta.domain.models.Project
import kotlinx.coroutines.flow.Flow
import java.io.File

interface ProjectRepository {
    fun getProjectsMap(): Flow<Map<String, String>>
    fun getProject(name: String): Flow<Project?>
    suspend fun createNewProject(name: String): Boolean
    suspend fun deleteProject(name: String)
    suspend fun createFile(projectName: String, fileName: String, content: String = ""): Boolean
    suspend fun deleteFile(projectName: String, fileName: String): Boolean

}
