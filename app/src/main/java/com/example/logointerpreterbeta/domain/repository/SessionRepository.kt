package com.example.logointerpreterbeta.domain.repository

import kotlinx.coroutines.flow.Flow

interface SessionRepository {
    fun getLastOpenedFile(): Flow<Pair<String, String>?>
    fun getLastOpenedProject(): Flow<String>
    suspend fun saveLastOpenedFile(projectName: String, fileName: String)
    suspend fun saveLastOpenedProject(projectName: String)
}
