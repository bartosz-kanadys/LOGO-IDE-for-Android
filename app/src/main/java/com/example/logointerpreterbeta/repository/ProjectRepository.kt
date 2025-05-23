package com.example.logointerpreterbeta.repository

import android.content.Context
import android.icu.text.SimpleDateFormat
import android.util.Log
import com.example.logointerpreterbeta.models.Project
import com.example.logointerpreterbeta.models.ProjectFile
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File
import java.util.Date
import java.util.Locale
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProjectRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {
    fun getProjectsMap(): Map<String, String> {
        val projectsFolder = File(context.getExternalFilesDir(null), "Projects")

        if (!projectsFolder.exists() || !projectsFolder.isDirectory) {
            projectsFolder.mkdirs()
            return emptyMap()
        }

        val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        val folderMap = emptyMap<String, String>().toMutableMap()

        projectsFolder.listFiles()?.forEach {
            if (it.isDirectory) {
                folderMap[it.name] = dateFormat.format(Date(it.lastModified()))
                Log.i("Folder", "Znaleziony folder: ${it.name} z datą: ${folderMap[it.name]}")
            }
        }
        Log.i("FF", folderMap.toString())
        return folderMap
    }

    fun getProject(name: String): Project? {
        val directory = File(context.getExternalFilesDir(null), "Projects/$name")
        if (!directory.exists() || !directory.isDirectory) {
            // Jeśli folder nie istnieje lub nie jest katalogiem, zwróć null
            return null
        }

        // Tworzenie listy plików .txt w folderze
        val projectFiles = directory.listFiles { file -> file.extension == "txt" }?.map { file ->
            // Tworzenie obiektu ProjectFile dla każdego pliku .txt
            ProjectFile(
                name = file.name,
                pathToFile = file.absolutePath,
                fileType = file.extension
            )
        } ?: emptyList()

        // Tworzenie obiektu Project z nazwą folderu i listą plików
        return Project(
            name = directory.name,
            pathToFolder = directory.absolutePath,
            files = projectFiles
        )
    }

    fun createNewProject(name: String): Boolean {
        val projectsFolder = File(context.getExternalFilesDir(null), "Projects/$name")
        if (projectsFolder.exists()) {
            return false
        }
        projectsFolder.mkdirs()
        return true
    }

    fun deleteProject(name: String): Boolean{
        val projectFolder = File(context.getExternalFilesDir(null), "Projects/$name")

        if (!projectFolder.exists() || !projectFolder.isDirectory) {
            return false
        }

        return deleteRecursively(projectFolder)
    }

    private fun deleteRecursively(file: File): Boolean {
        if (file.isDirectory) {
            file.listFiles()?.forEach { child ->
                deleteRecursively(child)
            }
        }
        return file.delete()
    }
}
