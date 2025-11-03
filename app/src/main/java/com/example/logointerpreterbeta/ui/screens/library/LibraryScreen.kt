package com.example.logointerpreterbeta.ui.screens.library

import android.content.Context
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.logointerpreterbeta.R
import com.example.logointerpreterbeta.domain.enums.LibraryCodes
import com.example.logointerpreterbeta.ui.navigation.LibraryForm
import com.example.logointerpreterbeta.ui.navigation.LibraryProcedures
import com.example.logointerpreterbeta.ui.screens.library.components.AddNewLibraryButton
import com.example.logointerpreterbeta.ui.screens.library.components.LibraryCard
import com.example.logointerpreterbeta.ui.theme.LogoInterpreterBetaTheme

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
        },
        onToastConsumed = {
            libraryViewModel.toastMessageConsumed()
        }
    )
}

@Composable
fun LibraryScreen(
    uiState: LibraryUiState,
    onLibraryFormNavigate: () -> Unit,
    onLibraryDelete: (String) -> Unit,
    onLibraryUpdate: (String) -> Unit,
    onToastConsumed: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var libraryToDelete by rememberSaveable { mutableStateOf<String?>(null) }
    val context = LocalContext.current

    uiState.toastMessage?.let {
        makeToast(context, uiState.toastMessage)
        onToastConsumed()
    }

    AnimatedVisibility(libraryToDelete != null) {
        AlertDialog(
            onDismissRequest = { libraryToDelete = null },
            title = { Text(stringResource(R.string.confirm_delete)) },
            text = { Text(stringResource(R.string.delete_library_text, libraryToDelete ?: "😯")) },
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
        LibraryCodes.OK -> {
            Toast.makeText(context, context.getString(R.string.library_created), Toast.LENGTH_LONG).show()
        }
        LibraryCodes.UNKNOWN_ERROR -> {
            Toast.makeText(context, context.getString(R.string.unknown_error), Toast.LENGTH_LONG).show()
        }
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
            onLibraryUpdate = { },
            onToastConsumed = { }
        )
    }
}
