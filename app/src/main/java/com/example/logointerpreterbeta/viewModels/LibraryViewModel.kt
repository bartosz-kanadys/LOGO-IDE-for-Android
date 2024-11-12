package com.example.logointerpreterbeta.viewModels

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.logointerpreterbeta.functions.library.addProcedureToLibraryJSON
import com.example.logointerpreterbeta.functions.library.createLibraryJSON
import com.example.logointerpreterbeta.functions.library.deleteLibraryFromJSON
import com.example.logointerpreterbeta.functions.library.deleteProcedureFromLibraryJSON
import com.example.logointerpreterbeta.functions.library.loadLibraries
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LibraryViewModel(context: Context) : ViewModel() {
    private val _libraries = MutableStateFlow(loadLibraries(context))
    val libraries: StateFlow<List<Library>> = _libraries

    private val _actualLibrary = MutableStateFlow<String?>(null)
    val actualLibrary: StateFlow<String?> = _actualLibrary

    fun updateActualLibrary(name: String?) {
        _actualLibrary.value = name
    }

    fun addLibrary(library: Library) {
        _libraries.value.add(library)
    }

    fun deleteLibrary(libraryName: String, context: Context) {
        _libraries.value = _libraries.value.filter { it.name != libraryName }.toMutableList()
        deleteLibraryFromJSON(context, libraryName)
    }

    fun updateLibraries(context: Context) {
        _libraries.value = loadLibraries(context)
    }

    fun createLibrary(context: Context, name: String, desc: String, author: String): Boolean {
        if (name.isEmpty() || desc.isEmpty() || author.isEmpty()) {
            Toast.makeText(context, "Uzupełnij wszytskie pola", Toast.LENGTH_LONG).show()
            return false
        } else if (desc.length > 50) {
            Toast.makeText(context, "Opis nie może być dłuższy niż 50 znaków", Toast.LENGTH_LONG)
                .show()
            return false
        } else if (_libraries.value.find { it.name == name } != null) {
            Toast.makeText(context, "Biblioteka o takiej nazwie już istnieje", Toast.LENGTH_LONG)
                .show()
            return false
        }

        val library =
            Library(name = name, description = desc, author = author, procedures = emptyList())
        createLibraryJSON(context, library)
        addLibrary(library)
        return true
    }

    fun checkProcedureAddForm(
        name: String,
        author: String,
        desc: String,
        code: String,
        context: Context
    ): Boolean {
        if (name.isEmpty() || author.isEmpty() || desc.isEmpty() || code.isEmpty()) {
            Toast.makeText(context, "Uzupełnij wszytskie pola", Toast.LENGTH_LONG).show()
            return false
        } else if (_libraries.value.find { it.name == actualLibrary.value }?.procedures?.find { it.name == name } != null) {
            Toast.makeText(
                context,
                "Procedura o takiej nazwie już istnieje w tej bibliotece",
                Toast.LENGTH_LONG
            )
                .show()
            return false
        }
        return true
    }

    fun addProcedureToLibrary(context: Context, libraryName: String, procedure: Procedure) {
        addProcedureToLibraryJSON(context, libraryName, procedure)
        updateLibraries(context)
    }

    fun deleteProcedureFromLibrary(context: Context, libraryName: String, procedureName: String) {
        deleteProcedureFromLibraryJSON(context, libraryName, procedureName)
        updateLibraries(context)
    }
}