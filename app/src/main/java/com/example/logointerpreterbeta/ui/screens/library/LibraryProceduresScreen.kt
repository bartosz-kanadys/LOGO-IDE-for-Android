package com.example.logointerpreterbeta.ui.screens.library

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.logointerpreterbeta.R
import com.example.logointerpreterbeta.ui.screens.library.components.AddProcedureButton
import com.example.logointerpreterbeta.ui.screens.library.components.ProcedureCard

@Composable
fun LibraryProceduresScreenRoot(
    libraryViewModel: LibraryViewModel,
    onNavigate: () -> Unit,
    modifier: Modifier
) {
    val uiState by libraryViewModel.uiState.collectAsStateWithLifecycle()

    LibraryProceduresScreen(
        uiState = uiState,
        onNavigate = onNavigate,
        onDeleteProcedure = { libraryName, procedureName ->
            libraryViewModel.deleteProcedureFromLibrary(libraryName, procedureName)
        },
        modifier = modifier
    )
}

@Composable
fun LibraryProceduresScreen(
    uiState: LibraryUiState,
    onDeleteProcedure: (String, String) -> Unit,
    onNavigate: () -> Unit,
    modifier: Modifier
) {
    var procedureToDelete by rememberSaveable {
        mutableStateOf<String?>(null)
    }

    val library = uiState.libraries.find { it.name == uiState.actualLibrary }

    AnimatedVisibility(procedureToDelete != null) {
        AlertDialog(
            title = {
                Text(text = stringResource(R.string.delete_procedure))
            },
            text = { Text(text = stringResource(R.string.delete_procedure_text)) },
            onDismissRequest = { },
            confirmButton = {
                TextButton(
                    onClick = {
                        onDeleteProcedure(
                            library?.name ?: "",
                            procedureToDelete!!
                        )
                        procedureToDelete = null
                    }
                ) {
                    Text(stringResource(R.string.delete))
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        procedureToDelete = null
                    }
                ) {
                    Text(stringResource(R.string.back))
                }
            }
        )
    }

    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(10.dp)
    ) {
        item {
            AddProcedureButton {
                onNavigate()
            }
            Spacer(modifier = Modifier.height(10.dp))
        }
        if (library!!.procedures.isEmpty()) {
            item {
                Text(
                    text = stringResource(R.string.empty_library_text),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .padding(top = 10.dp, bottom = 10.dp)
                )
            }
        } else {
            items(library.procedures) { procedure ->
                ProcedureCard(
                    procedureName = procedure.name,
                    procedureDescription = procedure.description,
                    code = procedure.code
                ) {
                    procedureToDelete = procedure.name
                }
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}