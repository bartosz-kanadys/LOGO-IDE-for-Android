package com.example.logointerpreterbeta

import android.content.Context
import android.widget.Toast
import com.example.logointerpreterbeta.domain.models.Library
import com.example.logointerpreterbeta.domain.models.Procedure
import com.example.logointerpreterbeta.data.repository.LibraryRepositoryImpl
import com.example.logointerpreterbeta.ui.viewModels.LibraryViewModel
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.unmockkStatic
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class LibraryViewModelTest {

    private lateinit var libraryViewModel: LibraryViewModel
    private val mockLibraryRepository = mockk<LibraryRepositoryImpl>(relaxed = true)
    private val mockContext = mockk<Context>(relaxed = true)

    @Before
    fun setup() {
        libraryViewModel = LibraryViewModel(mockLibraryRepository)
    }

    @Test
    fun `createLibrary should add a new library`() {

        val result = libraryViewModel.createLibrary(
            context = mockk(relaxed = true),
            name = "Test Library",
            desc = "Description",
            author = "Author"
        )

        assertTrue(result)
        verify { mockLibraryRepository.createLibrary(any()) }
        assertEquals(1, libraryViewModel.libraries.value.size)
        assertEquals("Test Library", libraryViewModel.libraries.value.first().name)
    }

    @Test
    fun `checkProcedureAddForm should return false if any field is empty`() {
        // Mockowanie Toast
        mockkStatic(Toast::class)
        val mockToast = mockk<Toast>()
        every { Toast.makeText(any(), any<String>(), any()) } returns mockToast
        every { mockToast.show() } just Runs

        val result = libraryViewModel.checkProcedureAddForm(
            name = "",
            author = "Author",
            desc = "Desc",
            code = "Code",
            context = mockk(relaxed = true)
        )

        assertFalse(result)

        // Weryfikacja, że Toast został wywołany
        verify { Toast.makeText(any(), "Uzupełnij wszytskie pola", Toast.LENGTH_LONG) }
        verify { mockToast.show() }


        // Odmockowanie
        unmockkStatic(Toast::class)
    }

    @Test
    fun `deleteLibrary should remove library from state and repository`() {
        val library = Library("Test", "Description", "Author", emptyList())
        libraryViewModel.addLibrary(library)

        libraryViewModel.deleteLibrary("Test")

        assertTrue(libraryViewModel.libraries.value.isEmpty())
        verify { mockLibraryRepository.deleteLibrary("Test") }
    }

    @Test
    fun `updateLibraries should load libraries from repository`()  {
        // Given
        val libraries = mutableListOf(Library("TestLib", "TestDesc", "Author", emptyList()))
        every { mockLibraryRepository.loadLibraries() } returns libraries

        // When
        libraryViewModel.updateLibraries()

        // Then
        assertEquals(libraries[0], libraryViewModel.libraries.value[0])
        verify { mockLibraryRepository.loadLibraries() }
    }

    @Test
    fun `addLibrary should add a new library to the list`() {
        // Given
        val library = Library("NewLibrary", "Desc", "Author", emptyList())

        // When
        libraryViewModel.addLibrary(library)

        // Then
        assertEquals(1, libraryViewModel.libraries.value.size)
        assertEquals(library, libraryViewModel.libraries.value.first())
    }

    @Test
    fun `deleteLibrary should remove a library from the list`() {
        // Given
        val library = Library("LibToDelete", "Desc", "Author", emptyList())
        libraryViewModel.addLibrary(library)

        // When
        libraryViewModel.deleteLibrary(library.name)

        // Then
        assertEquals(0, libraryViewModel.libraries.value.size)
        verify { mockLibraryRepository.deleteLibrary(library.name) }
    }

    @Test
    fun `createLibrary should return false and show toast if fields are empty`()  {
        mockkStatic(Toast::class)
        val mockToast = mockk<Toast>()
        every { Toast.makeText(any(), any<String>(), any()) } returns mockToast
        every { mockToast.show() } just Runs

        // Given
        every { mockContext.getString(any()) } returns "Uzupełnij wszystkie pola"

        // When
        val result = libraryViewModel.createLibrary(mockContext, "", "", "")

        // Then
        assertEquals(false, result)
        verify { Toast.makeText(any(), "Uzupełnij wszystkie pola", Toast.LENGTH_LONG) }
        verify { mockToast.show() }

        // Odmockowanie
        unmockkStatic(Toast::class)
    }

    @Test
    fun `createLibrary should return false and show toast if description is too long`()  {
        // Given
        val longDesc = "A".repeat(51)

        mockkStatic(Toast::class)
        val mockToast = mockk<Toast>()
        every { Toast.makeText(any(), any<String>(), any()) } returns mockToast
        every { mockToast.show() } just Runs

        // When
        val result = libraryViewModel.createLibrary(mockContext, "Name", longDesc, "Author")

        // Then
        assertEquals(false, result)
        verify { Toast.makeText(any(), "Opis nie może być dłuższy niż 50 znaków", Toast.LENGTH_LONG) }
        verify { mockToast.show() }

        // Odmockowanie
        unmockkStatic(Toast::class)
    }

    @Test
    fun `createLibrary should return false if library name already exists`() {
        mockkStatic(Toast::class)
        val mockToast = mockk<Toast>()
        every { Toast.makeText(any(), any<String>(), any()) } returns mockToast
        every { mockToast.show() } just Runs

        // Given
        val existingLibrary = Library("ExistingLib", "Desc", "Author", emptyList())
        libraryViewModel.addLibrary(existingLibrary)

        // When
        val result = libraryViewModel.createLibrary(mockContext, "ExistingLib", "Desc", "Author")

        // Then
        assertEquals(false, result)
        verify { Toast.makeText(any(), "Biblioteka o takiej nazwie już istnieje", Toast.LENGTH_LONG) }
        verify { mockToast.show() }

        // Odmockowanie
        unmockkStatic(Toast::class)
    }

    @Test
    fun `createLibrary should add a new library if validation passes`() {
        // Given
        val name = "NewLib"
        val desc = "Valid description"
        val author = "Author"
        val newLibrary = Library(name, author, desc, emptyList())

        // When
        val result = libraryViewModel.createLibrary(mockContext, name, desc, author)

        // Then
        assertEquals(true, result)
        assertEquals(1, libraryViewModel.libraries.value.size)
        assertEquals(newLibrary, libraryViewModel.libraries.value.first())
        verify { mockLibraryRepository.createLibrary(newLibrary) }
    }

    @Test
    fun `addProcedureToLibrary should add a procedure to the library`() {
        // Given
        val library = Library("Test", "Description", "Author", emptyList())
        libraryViewModel.addLibrary(library)
        val procedure = Procedure("Procedure1", "Code", null, "Desc")
        val libraryAfterAdding = Library("Test", "Description", "Author", listOf(procedure))

        // Mockowanie metody w repozytorium
        every { mockLibraryRepository.addProcedureToLibrary(library.name, procedure) } returns Unit
        every { mockLibraryRepository.loadLibraries() } returns mutableListOf(libraryAfterAdding)

        // When
        libraryViewModel.addProcedureToLibrary(library.name, procedure)

        // Then
        verify { mockLibraryRepository.addProcedureToLibrary(library.name, procedure) }

        // Sprawdzamy, czy procedura została dodana
        assertEquals(1, libraryViewModel.libraries.value.size)
        assertEquals(procedure, libraryViewModel.libraries.value[0].procedures[0])
    }

    @Test
    fun `deleteProcedureFromLibrary should remove a procedure from the library`()  {
        val procedure = Procedure("Procedure1", "Code", null, "Desc")

        val library = Library("Test", "Description", "Author", mutableListOf(procedure))
        libraryViewModel.addLibrary(library)
        val libraryAfterDeleting= Library("Test", "Description", "Author", emptyList())

        every { mockLibraryRepository.loadLibraries() } returns mutableListOf(libraryAfterDeleting)

        // When
        libraryViewModel.deleteProcedureFromLibrary(library.name, procedure.name)

        // Then
        verify { mockLibraryRepository.deleteProcedureFromLibrary(library.name, procedure.name) }
        assertEquals(0, libraryViewModel.libraries.value.first().procedures.size)
    }

}



