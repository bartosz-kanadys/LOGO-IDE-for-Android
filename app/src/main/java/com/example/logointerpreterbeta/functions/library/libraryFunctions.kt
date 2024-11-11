package com.example.logointerpreterbeta.functions.library

import android.content.Context
import com.example.logointerpreterbeta.viewModels.Library
import com.example.logointerpreterbeta.viewModels.Procedure
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File

fun createLibraryJSON(context: Context, library: Library) {
    val libraryFile = File(context.getExternalFilesDir(null), "library.json")

    // Jeśli plik nie istnieje, utwórz go z jedną biblioteką
    if (!libraryFile.exists()) {
        val gson = Gson()
        val jsonString = gson.toJson(listOf(library)) // Zamień bibliotekę na JSON w postaci listy
        libraryFile.writeText(jsonString)
        return
    }

    // Jeśli plik już istnieje, wczytujemy dane
    val gson = Gson()
    val jsonString = libraryFile.readText()

    // Zdeserializuj JSON na listę obiektów Library
    val listType = object : TypeToken<List<Library>>() {}.type
    val libraries: MutableList<Library> = gson.fromJson(jsonString, listType)

    // Dodaj nową bibliotekę do listy
    libraries.add(library)

    // Przekształć zaktualizowaną listę z powrotem na JSON i zapisz do pliku
    val updatedJson = gson.toJson(libraries)
    libraryFile.writeText(updatedJson)
}

fun loadLibraries(context: Context): MutableList<Library> {
    val libraryFile = File(context.getExternalFilesDir(null), "library.json")

    // Sprawdź, czy plik istnieje
    if (!libraryFile.exists()) {
        // Jeśli plik nie istnieje, zwróć pustą listę
        return mutableListOf()
    }

    // Wczytujemy zawartość pliku JSON
    val jsonString = libraryFile.readText()

    // Inicjalizujemy Gson i TypeToken
    val gson = Gson()
    val listType = object : TypeToken<List<Library>>() {}.type

    // Deserializujemy JSON do listy obiektów Library
    return gson.fromJson(jsonString, listType)
}

fun deleteLibraryFromJSON(context: Context, libraryName: String) {
    val libraryFile = File(context.getExternalFilesDir(null), "library.json")

    if (!libraryFile.exists()) {
        return
    }

    val jsonString = libraryFile.readText()

    val gson = Gson()
    val listType = object : TypeToken<List<Library>>() {}.type

    // Deserializujemy JSON do listy obiektów Library
    val libraries: MutableList<Library> = gson.fromJson(jsonString, listType)

    // Usuwamy bibliotekę na podstawie nazwy
    val libraryToRemove = libraries.find { it.name == libraryName }
    if (libraryToRemove != null) {
        libraries.remove(libraryToRemove)

        // Przekształć zaktualizowaną listę z powrotem na JSON i zapisz do pliku
        val updatedJson = gson.toJson(libraries)
        libraryFile.writeText(updatedJson)
    }
}

fun addProcedureToLibraryJSON(context: Context, libraryName: String, procedure: Procedure) {
    val libraryFile = File(context.getExternalFilesDir(null), "library.json")

    if (!libraryFile.exists()) {
        return
    }

    val jsonString = libraryFile.readText()

    val gson = Gson()
    val listType = object : TypeToken<List<Library>>() {}.type

    // Deserializujemy JSON do listy obiektów Library
    val libraries: MutableList<Library> = gson.fromJson(jsonString, listType)

    // Znajdujemy bibliotekę o podanej nazwie
    val library = libraries.find { it.name == libraryName }
    if (library != null) {
        // Tworzymy nową listę procedur z dodaną nową procedurą
        val updatedProcedures = library.procedures.toMutableList().apply {
            add(procedure)
        }

        // Tworzymy nowy obiekt Library z zaktualizowaną listą procedur
        val updatedLibrary = library.copy(procedures = updatedProcedures)

        // Aktualizujemy listę bibliotek
        libraries[libraries.indexOf(library)] = updatedLibrary

        // Przekształcamy zaktualizowaną listę bibliotek na JSON i zapisujemy do pliku
        val updatedJson = gson.toJson(libraries)
        libraryFile.writeText(updatedJson)
    }
}

fun deleteProcedureFromLibraryJSON(context: Context, libraryName: String, procedureName: String) {
    val libraryFile = File(context.getExternalFilesDir(null), "library.json")

    if (!libraryFile.exists()) {
        return
    }

    val jsonString = libraryFile.readText()
    val gson = Gson()
    val listType = object : TypeToken<List<Library>>() {}.type

    // Deserializujemy JSON do listy obiektów Library
    val libraries: MutableList<Library> = gson.fromJson(jsonString, listType)

    // Znajdujemy bibliotekę o podanej nazwie
    val library = libraries.find { it.name == libraryName }
    if (library != null) {
        // Tworzymy nową listę procedur bez procedury o podanej nazwie
        val updatedProcedures = library.procedures.filter { it.name != procedureName }

        // Tworzymy nowy obiekt Library z zaktualizowaną listą procedur
        val updatedLibrary = library.copy(procedures = updatedProcedures)

        // Aktualizujemy listę bibliotek
        libraries[libraries.indexOf(library)] = updatedLibrary

        // Przekształcamy zaktualizowaną listę bibliotek na JSON i zapisujemy do pliku
        val updatedJson = gson.toJson(libraries)
        libraryFile.writeText(updatedJson)
    }
}

