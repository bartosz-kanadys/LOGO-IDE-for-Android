package com.example.logointerpreterbeta.domain.repository

import com.example.logointerpreterbeta.domain.models.Library
import com.example.logointerpreterbeta.domain.models.Procedure
import kotlinx.coroutines.flow.Flow

interface LibraryRepository {
    fun getLibraries(): Flow<List<Library>>
    suspend fun createLibrary(library: Library)
    suspend fun deleteLibrary(libraryName: String)
    suspend fun addProcedureToLibrary(libraryName: String, procedure: Procedure)
    suspend fun deleteProcedureFromLibrary(libraryName: String, procedureName: String)
    suspend fun libraryExists(name: String): Boolean
    suspend fun procedureExistsInLibrary(libraryName: String, procedureName: String): Boolean
    fun getCurrentLibraries(): List<Library>

}