package com.example.logointerpreterbeta.repository

import android.content.Context
import com.example.logointerpreterbeta.functions.library.addProcedureToLibraryJSON
import com.example.logointerpreterbeta.functions.library.createLibraryJSON
import com.example.logointerpreterbeta.functions.library.deleteLibraryFromJSON
import com.example.logointerpreterbeta.functions.library.deleteProcedureFromLibraryJSON
import com.example.logointerpreterbeta.functions.library.loadLibrariesFromJSON
import com.example.logointerpreterbeta.models.Library
import com.example.logointerpreterbeta.models.Procedure
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LibraryRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {
    fun addProcedureToLibrary(libraryName: String, procedure: Procedure) =
        addProcedureToLibraryJSON(context, libraryName, procedure)

    fun deleteProcedureFromLibrary(libraryName: String, procedureName: String) =
        deleteProcedureFromLibraryJSON(context, libraryName, procedureName)

    fun loadLibraries(): MutableList<Library> {
        return loadLibrariesFromJSON(context)
    }

    fun deleteLibrary(libraryName: String) = deleteLibraryFromJSON(context, libraryName)

    fun createLibrary(library: Library) = createLibraryJSON(context, library)


}