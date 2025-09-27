package com.example.logointerpreterbeta.ui.screens.library

import android.content.Context
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Bookmarks
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.logointerpreterbeta.R
import com.example.logointerpreterbeta.ui.navigation.LibraryForm
import com.example.logointerpreterbeta.ui.navigation.LibraryProcedures
import com.example.logointerpreterbeta.ui.theme.AppTypography
import com.example.logointerpreterbeta.ui.theme.LogoInterpreterBetaTheme
import com.example.logointerpreterbeta.ui.screens.library.LibraryViewModel
import com.example.logointerpreterbeta.ui.screens.library.components.AddNewLibraryButton
import com.example.logointerpreterbeta.ui.screens.library.components.LibraryCard
import com.example.logointerpreterbeta.ui.screens.start.Screen

@Composable
fun LibraryScreenRoot(
    libraryViewModel: LibraryViewModel,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val uiState by libraryViewModel.uiState.collectAsStateWithLifecycle()

    LibraryScreen(
        modifier = modifier,
        uiState = uiState,
        onLibraryFormNavigate = {
            navController.navigate(LibraryForm)
        },
        onLibraryDelete = {
            libraryViewModel.deleteLibrary(it)
        },
        onLibraryUpdate = {
            libraryViewModel.updateActualLibrary(it)
            navController.navigate(LibraryProcedures)
        }
    )
}

@Composable
fun LibraryScreen(
    uiState: LibraryUiState,
    onLibraryFormNavigate: () -> Unit,
    onLibraryDelete: (String) -> Unit,
    onLibraryUpdate: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    var libraryToDelete by rememberSaveable { mutableStateOf<String?>(null) }

    AnimatedVisibility(libraryToDelete != null) {
        AlertDialog(
            onDismissRequest = { libraryToDelete = null },
            title = { Text(stringResource(R.string.confirm_delete)) },
            text = { Text(stringResource(R.string.delete_library_text, libraryToDelete!!)) },
            confirmButton = {
                TextButton(
                    onClick = {
                        onLibraryDelete(libraryToDelete!!)
                        libraryToDelete = null
                    }
                ) {
                    Text(stringResource(R.string.delete))
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { libraryToDelete = null } // Anuluj usunięcie
                ) {
                    Text(stringResource(R.string.cancel))
                }
            }
        )
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(2), // 2 elementy na wiersz
        contentPadding = PaddingValues(10.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
    ) {
        item {
            AddNewLibraryButton {
                onLibraryFormNavigate()
            }
        }
        items(uiState.libraries) { library ->
            LibraryCard(
                libraryName = library.name,
                libraryDescription = library.description,
                procedureCount = library.procedures.size,
                author = library.author,
                onClick = {
                    onLibraryUpdate(library.name)
                },
                onDeleteClick = { libraryToDelete = library.name }
            )
        }
    }
}

fun makeToast(context: Context, code: LibraryCodes) {
    when (code) {
        LibraryCodes.DESC_TOO_LONG -> {
            Toast.makeText(context, context.getString(R.string.desc_too_long), Toast.LENGTH_LONG).show()
        }
        LibraryCodes.PROCEDURE_EXIST -> { }
        LibraryCodes.FILL_ALL_FIELDS -> {
            Toast.makeText(context, context.getString(R.string.fill_all_fields), Toast.LENGTH_LONG).show()
        }
        LibraryCodes.LIBRARY_EXIST -> {
            Toast.makeText(context, context.getString(R.string.library_exists), Toast.LENGTH_LONG).show()
        }
        LibraryCodes.OK -> { }
    }
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewLibraryScreen() {
    LogoInterpreterBetaTheme {
        LibraryScreen(
            modifier = Modifier,
            uiState = LibraryUiState(),
            onLibraryFormNavigate = { },
            onLibraryDelete = { },
            onLibraryUpdate = { }
        )
    }
}
