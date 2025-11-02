package com.example.logointerpreterbeta.data.repository

import android.content.Context
import com.example.logointerpreterbeta.domain.models.Library
import com.example.logointerpreterbeta.domain.models.Procedure
import com.example.logointerpreterbeta.domain.repository.LibraryRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import jakarta.inject.Inject
import jakarta.inject.Singleton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import java.io.File


@Singleton
class LibraryRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    // Wstrzykujemy zewnętrzny scope, aby repozytorium nie umarło z ViewModelami
    private val externalScope: CoroutineScope
): LibraryRepository {
    private val gson = Gson()
    private val libraryFile = File(context.getExternalFilesDir(null), "library.json")
    private val listType = object : TypeToken<List<Library>>() {}.type

    // Mutex, aby chronić dostęp do pliku i stanu
    private val mutex = Mutex()

    // źródło prawdy w pamięci
    private val _librariesFlow = MutableStateFlow<List<Library>>(emptyList())

    init {
        // Przy starcie wczytujemy dane z pliku do Flow
        externalScope.launch {
            _librariesFlow.value = loadLibrariesFromFile()
        }
    }

    override fun getLibraries(): Flow<List<Library>> {
        // Zwracamy Flow, aby UI mogło go obserwować
        return _librariesFlow.asStateFlow()
    }

    override fun getCurrentLibraries(): List<Library> {
        // Zwracamy aktualną wartość z Flow
        return _librariesFlow.value
    }

    override suspend fun createLibrary(library: Library) = mutex.withLock {
        val currentLibraries = _librariesFlow.value.toMutableList()
        currentLibraries.add(library)
        _librariesFlow.value = currentLibraries
        saveLibrariesToFile(currentLibraries)
    }

    override suspend fun deleteLibrary(libraryName: String) = mutex.withLock {
        val currentLibraries = _librariesFlow.value.toMutableList()
        val updatedList = currentLibraries.filter { it.name != libraryName }
        _librariesFlow.value = updatedList
        saveLibrariesToFile(updatedList)
    }

    override suspend fun addProcedureToLibrary(libraryName: String, procedure: Procedure) = mutex.withLock {
        val currentLibraries = _librariesFlow.value
        val libraryIndex = currentLibraries.indexOfFirst { it.name == libraryName }

        if (libraryIndex != -1) {
            val libraryToUpdate = currentLibraries[libraryIndex]

            val updatedLibrary = libraryToUpdate.copy(
                procedures = libraryToUpdate.procedures + procedure
            )

            val updatedLibraries = currentLibraries.mapIndexed { index, library ->
                if (index == libraryIndex) updatedLibrary else library
            }

            _librariesFlow.value = updatedLibraries
            saveLibrariesToFile(updatedLibraries)
        }
    }

    override suspend fun deleteProcedureFromLibrary(libraryName: String, procedureName: String) = mutex.withLock {
        val currentLibraries = _librariesFlow.value.toMutableList()
        val libraryIndex = currentLibraries.indexOfFirst { it.name == libraryName }

        if (libraryIndex != -1) {
            val library = currentLibraries[libraryIndex]
            val updatedProcedures = library.procedures.filter { it.name != procedureName }
            val updatedLibrary = library.copy(procedures = updatedProcedures)

            currentLibraries[libraryIndex] = updatedLibrary
            _librariesFlow.value = currentLibraries
            saveLibrariesToFile(currentLibraries)
        }
    }

    override suspend fun libraryExists(name: String): Boolean {
        // Sprawdzamy na aktualnej wartości z Flow, bez czytania pliku
        return _librariesFlow.value.any { it.name == name }
    }

    override suspend fun procedureExistsInLibrary(libraryName: String, procedureName: String): Boolean {
        val currentLibraries = _librariesFlow.value
        val library = currentLibraries.find { it.name == libraryName }
        return library?.procedures?.any { it.name == procedureName } ?: false
    }

    // Prywatna funkcja do zapisu w tle
    private suspend fun saveLibrariesToFile(libraries: List<Library>) = withContext(Dispatchers.IO) {
        val jsonString = gson.toJson(libraries)
        libraryFile.writeText(jsonString)
    }

    // Prywatna funkcja do odczytu w tle
    private fun loadLibrariesFromFile(): List<Library> {
        return if (libraryFile.exists()) {
            val jsonString = libraryFile.readText()
            gson.fromJson(jsonString, listType) ?: emptyList()
        } else {
            emptyList()
        }
    }
}