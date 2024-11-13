package com.example.logointerpreterbeta.Repository

import android.content.Context
import com.example.logointerpreterbeta.functions.config.readLastModifiedProject
import com.example.logointerpreterbeta.functions.config.updateLastModifiedProjectJSON
import com.example.logointerpreterbeta.functions.project.Project
import com.example.logointerpreterbeta.functions.project.createFileInDevice
import com.example.logointerpreterbeta.functions.project.createProjectFolder
import com.example.logointerpreterbeta.functions.project.deleteFileInDevice
import com.example.logointerpreterbeta.functions.project.deleteProjectFolder
import com.example.logointerpreterbeta.functions.project.getProjectFoldersMap
import com.example.logointerpreterbeta.functions.project.getProjectFromDirectory
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProjectRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {
    fun getProjectsMap(): Map<String, String> = getProjectFoldersMap(context)

    fun getProject(name: String): Project? = getProjectFromDirectory(
        File(context.getExternalFilesDir(null), "Projects/$name")
    )

    fun createNewProject(name: String): Boolean = createProjectFolder(name, context)

    fun deleteProject(name: String) = deleteProjectFolder(name, context)

    fun createFile(projectName: String, fileName: String, content: String = "") =
        createFileInDevice(fileName, projectName, content, context)

    fun deleteFile(projectName: String, fileName: String) =
        deleteFileInDevice(fileName, projectName, context)

    fun updateLastProjectJSON(newProjectName: String) =
        updateLastModifiedProjectJSON(context, newProjectName)

    fun readLastProjectJSON(): String? {
        return readLastModifiedProject(context)
    }
}
