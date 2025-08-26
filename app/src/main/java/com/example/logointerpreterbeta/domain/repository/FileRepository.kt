package com.example.logointerpreterbeta.domain.repository

import android.content.Context

interface FileRepository {
    fun createFile(projectName: String, fileName: String, content: String = "")
    fun deleteFile(projectName: String, fileName: String): Boolean
    fun readFileContent(context: Context, fileName: String, projectName: String): String?
    fun writeFileContent(
        context: Context,
        fileName: String,
        projectName: String,
        content: String
    ): Boolean
}