package com.example.logointerpreterbeta.projects

import android.content.Context
import android.util.Log
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

fun createFile(
    fileName: String,
    projectName: String,
    fileContent: String,
    context: Context
) {
    val newFile = File(context.getExternalFilesDir(null), "Projects/$projectName/$fileName.txt")
    Log.i("File", "elooo.")

    if (newFile.exists()) {
        Log.i("File", "Plik ${newFile.absolutePath} już istnieje.")
        return
    }

    try {
        FileOutputStream(newFile).use { outputStream ->
            outputStream.write(fileContent.toByteArray())
        }
        Log.i("File", "Zapisano plik: ${newFile.absolutePath}")
    } catch (e: IOException) {
        Log.e("File", "Błąd zapisu pliku: ${e.message}")
    }
}

fun deleteFile(
    fileName: String,
    projectName: String,
    context: Context
): Boolean {
    val fileToDelete = File(context.getExternalFilesDir(null), "Projects/$projectName/$fileName")

    return if (fileToDelete.exists()) {
        val result = fileToDelete.delete()
        if (result) {
            Log.i("File", "Plik ${fileToDelete.absolutePath} został usunięty.")
        } else {
            Log.e("File", "Nie udało się usunąć pliku: ${fileToDelete.absolutePath}.")
        }
        result
    } else {
        Log.i("File", "Plik ${fileToDelete.absolutePath} nie istnieje.")
        false
    }
}

fun readFileContent(context: Context, fileName: String, projectName: String): String? {
    val file = File(context.getExternalFilesDir(null), "Projects/$projectName/$fileName")

    return if (file.exists() && file.isFile) {
        try {
            file.readText()
        } catch (e: IOException) {
            Log.e("ReadFile", "Błąd podczas odczytu pliku: ${e.message}")
            null
        }
    } else {
        Log.e("ReadFile", "Plik nie istnieje lub nie jest plikiem")
        null
    }
}

fun writeFileContent(context: Context, fileName: String, projectName: String, content: String): Boolean {
    val file = File(context.getExternalFilesDir(null), "Projects/$projectName/$fileName")

    return try {
        file.writeText(content)
        true
    } catch (e: IOException) {
        Log.e("WriteFile", "Błąd podczas zapisu do pliku: ${e.message}")
        false
    }
}

fun renameFile(context: Context, oldFileName: String, newFileName: String, projectName: String): Boolean {
    val folder = File(context.getExternalFilesDir(null), "Projects/$projectName")
    if (!folder.exists() || !folder.isDirectory) {
        return false
    }

    val oldFile = File(folder, oldFileName)
    val newFile = File(folder, newFileName+".txt")

    return try {
        if (oldFile.exists()) {
            // Skopiowanie zawartości starego pliku do nowego
            oldFile.copyTo(newFile, overwrite = true)
            // Usunięcie starego pliku po skopiowaniu zawartości
            oldFile.delete()
        } else {
            false // Stary plik nie istnieje
        }
    } catch (e: IOException) {
        e.printStackTrace()
        false // Zwróć false w przypadku błędu
    }
}

