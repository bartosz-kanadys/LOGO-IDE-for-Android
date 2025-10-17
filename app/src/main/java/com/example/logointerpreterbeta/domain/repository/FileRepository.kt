package com.example.logointerpreterbeta.domain.repository

import android.content.Context

interface FileRepository {
    fun createFile(projectName: String, fileName: String, content: String = ""): Boolean
    fun deleteFile(projectName: String, fileName: String): Boolean
    fun readFileContent(fileName: String, projectName: String): Result<String>
    fun writeFileContent(
        fileName: String,
        projectName: String,
        content: String
    ): Boolean
}