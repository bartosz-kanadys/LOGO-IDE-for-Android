package com.example.logointerpreterbeta.domain.repository

import com.example.logointerpreterbeta.domain.models.Library
import com.example.logointerpreterbeta.domain.models.Procedure

interface LibraryRepository {
    fun addProcedureToLibrary(libraryName: String, procedure: Procedure)
    fun deleteProcedureFromLibrary(libraryName: String, procedureName: String)
    fun loadLibraries(): MutableList<Library>
    fun deleteLibrary(libraryName: String)
    fun createLibrary(library: Library)
}