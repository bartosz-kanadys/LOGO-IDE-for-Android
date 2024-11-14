package com.example.logointerpreterbeta.viewModels

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.logointerpreterbeta.repository.LibraryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class LibraryViewModel @Inject constructor(
    private val libraryRepository: LibraryRepository
) : ViewModel() {
    private val _libraries = MutableStateFlow<MutableList<Library>>(mutableListOf())
    val libraries = _libraries.asStateFlow()

    private val _actualLibrary = MutableStateFlow<String?>(null)
    val actualLibrary = _actualLibrary.asStateFlow()

    init {
        updateLibraries()
    }

    fun updateActualLibrary(name: String?) {
        _actualLibrary.value = name
    }

    private fun addLibrary(library: Library) {
        _libraries.value.add(library)
    }

    fun deleteLibrary(libraryName: String) {
        _libraries.value = _libraries.value.filter { it.name != libraryName }.toMutableList()
        libraryRepository.deleteLibrary(libraryName)
    }

    private fun updateLibraries() {
        _libraries.value = libraryRepository.loadLibraries()
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
        libraryRepository.createLibrray(library)
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

    fun addProcedureToLibrary(libraryName: String, procedure: Procedure) {
        libraryRepository.addProcedureToLibrary(libraryName, procedure)
        updateLibraries()
    }

    fun deleteProcedureFromLibrary(libraryName: String, procedureName: String) {
        libraryRepository.deleteProcedureFromLibrray(libraryName, procedureName)
        updateLibraries()
    }
}