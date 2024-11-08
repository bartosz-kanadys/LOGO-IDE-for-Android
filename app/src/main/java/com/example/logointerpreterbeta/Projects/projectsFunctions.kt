package com.example.logointerpreterbeta.Projects
import android.content.Context
import android.icu.text.SimpleDateFormat
import android.util.Log
import com.google.gson.Gson
import java.io.File
import com.google.gson.reflect.TypeToken
import java.util.Date
import java.util.Locale


fun createProjectJSON(name: String, context: Context) : Boolean {
   // val projectFiles = emptyList<ProjectFile>()

//    val project = Project(
//        name = name,
//        pathToFolder = "/Projects/$name",
//        files = projectFiles
//    )

//    val gson = Gson()
//    val jsonFile = File(context.getExternalFilesDir(null), "projects.json")

//    val projectsList: MutableList<Project> = if (jsonFile.exists()) {
//        // Jeśli plik istnieje, odczytaj listę projektów
//        try {
//            val json = jsonFile.readText()
//            val type = object : TypeToken<MutableList<Project>>() {}.type
//            gson.fromJson(json, type) ?: mutableListOf()
//        } catch (e: Exception) {
//            Log.e("JSON", "Błąd podczas odczytu pliku JSON: ${e.message}")
//            mutableListOf()
//        }
//    } else {
//        // Jeśli plik nie istnieje, utwórz nową listę
//        mutableListOf()
//    }

//    // Sprawdzenie, czy projekt o tej nazwie już istnieje
//    if (projectsList.any { it.name == name }) {
//        Log.i("JSON", "Projekt o nazwie '$name' już istnieje i nie zostanie dodany.")
//        return false
//    }
//
//    // Dodaj nowy projekt do listy
//    projectsList.add(project)
//
//    // Zapisz zaktualizowaną listę do pliku
//    val updatedJson = gson.toJson(projectsList)
//    jsonFile.writeText(updatedJson)
//

    val projectsFolder = File(context.getExternalFilesDir(null), "Projects/$name")
    if (projectsFolder.exists()){
        return false
    }
    projectsFolder.mkdirs()


    //Log.i("JSON", "Zapisano projekt do pliku: ${jsonFile.path}")
    return true

}

fun getProjectFromDirectory(directory: File): Project? {
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

fun getProjectFoldersMap(context: Context): Map<String,String> {
    val projectsFolder = File(context.getExternalFilesDir(null), "Projects")

    if (!projectsFolder.exists() || !projectsFolder.isDirectory) {
        projectsFolder.mkdirs()
        return emptyMap()
    }

    val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
    val folderMap = emptyMap<String,String>().toMutableMap()

    projectsFolder.listFiles()?.forEach {
        if (it.isDirectory) {
            folderMap[it.name] = dateFormat.format(Date(it.lastModified()))
            Log.i("Folder", "Znaleziony folder: ${it.name} z datą: ${folderMap[it.name]}")
        }
    }
    Log.i("FF", folderMap.toString())
    return folderMap
}
