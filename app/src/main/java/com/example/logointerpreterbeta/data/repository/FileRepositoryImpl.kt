package com.example.logointerpreterbeta.data.repository

import android.content.Context
import android.util.Log
import com.example.logointerpreterbeta.domain.repository.FileRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FileRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context
): FileRepository {
    override fun createFile(projectName: String, fileName: String, content: String) {
        val newFile = File(context.getExternalFilesDir(null), "Projects/$projectName/$fileName.txt")

        if (newFile.exists()) {
            Log.i("File", "Plik ${newFile.absolutePath} już istnieje.")
            return
        }

        try {
            FileOutputStream(newFile).use { outputStream ->
                outputStream.write(content.toByteArray())
            }
            Log.i("File", "Zapisano plik: ${newFile.absolutePath}")
        } catch (e: IOException) {
            Log.e("File", "Błąd zapisu pliku: ${e.message}")
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

    override fun readFileContent(context: Context, fileName: String, projectName: String): String? {
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

    override fun writeFileContent(
        context: Context,
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