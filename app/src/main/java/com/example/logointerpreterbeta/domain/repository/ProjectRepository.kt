package com.example.logointerpreterbeta.domain.repository

import com.example.logointerpreterbeta.domain.models.Project
import java.io.File

interface ProjectRepository {
    fun getProjectsMap(): Map<String, String>
    fun getProject(name: String): Project?
    fun createNewProject(name: String): Boolean
    fun deleteProject(name: String): Boolean
    fun deleteRecursively(file: File): Boolean
}