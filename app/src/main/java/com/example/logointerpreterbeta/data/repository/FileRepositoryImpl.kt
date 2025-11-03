package com.example.logointerpreterbeta.data.repository

import android.content.Context
import android.util.Log
import com.example.logointerpreterbeta.domain.repository.FileRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import jakarta.inject.Inject
import jakarta.inject.Singleton
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

@Singleton
class FileRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context
): FileRepository {
    override fun createFile(projectName: String, fileName: String, content: String): Boolean {
        val newFile = File(context.getExternalFilesDir(null), "Projects/$projectName/$fileName.txt")

        if (newFile.exists()) {
            Log.i("File", "Plik ${newFile.absolutePath} już istnieje.")
            return false
        }

        try {
            FileOutputStream(newFile).use { outputStream ->
                outputStream.write(content.toByteArray())
            }
            Log.i("File", "Zapisano plik: ${newFile.absolutePath}")
            return true
        } catch (e: IOException) {
            Log.e("File", "Błąd zapisu pliku: ${e.message}")
            return false
        }
    }

    override fun deleteFile(projectName: String, fileName: String): Boolean {
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

    override fun readFileContent(fileName: String, projectName: String): Result<String> {
        val file = File(context.getExternalFilesDir(null), "Projects/$projectName/$fileName")

        return runCatching {
            require(file.exists() && file.isFile) {
                "File: '$fileName' in project '$projectName' doesn't exist or is not a file."
            }
            file.readText()
        }.onFailure { e ->
            Log.e("ReadFile", "Error during reading file: ${e.message}", e)
        }
    }

    override fun writeFileContent(
        fileName: String,
        projectName: String,
        content: String
    ): Boolean {
        val file = File(context.getExternalFilesDir(null), "Projects/$projectName/$fileName")

        return try {
            file.writeText(content)
            true
        } catch (e: IOException) {
            Log.e("WriteFile", "Błąd podczas zapisu do pliku: ${e.message}")
            false
        }
    }

}