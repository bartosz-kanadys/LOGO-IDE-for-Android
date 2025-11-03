package com.example.logointerpreterbeta.ui.screens.library

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.logointerpreterbeta.R
import com.example.logointerpreterbeta.domain.enums.LibraryCodes
import com.example.logointerpreterbeta.ui.screens.interpreter.components.codeEditor.CodeEditor
import com.example.logointerpreterbeta.ui.theme.LogoInterpreterBetaTheme
import com.example.logointerpreterbeta.ui.screens.interpreter.InterpreterViewModel
import com.example.logointerpreterbeta.domain.models.Procedure
import com.example.logointerpreterbeta.ui.screens.library.components.FormButton
import com.example.logointerpreterbeta.ui.screens.library.components.FormTextField
import androidx.compose.runtime.collectAsState
import com.example.logointerpreterbeta.ui.screens.interpreter.InterpreterEvent

@Composable
fun LibraryAddProcedureForm(
    libraryViewModel: LibraryViewModel,
    interpreterViewModel: InterpreterViewModel,
    navController: NavController,
    modifier: Modifier
) {
    var procedureName by rememberSaveable {
        mutableStateOf("")
    }
    var procedureDescription by rememberSaveable {
        mutableStateOf("")
    }
    var procedureAuthor by rememberSaveable {
        mutableStateOf("")
    }
    val context = LocalContext.current

    val interpreterUiState by interpreterViewModel.uiState.collectAsStateWithLifecycle()
    val uiState by libraryViewModel.uiState.collectAsStateWithLifecycle()

    uiState.toastMessage?.let {
        makeToast(context, it)
        libraryViewModel.toastMessageConsumed()
    }

    LaunchedEffect(Unit) {
        interpreterViewModel.onEvent(
            InterpreterEvent.OnCodeChange("\n\n\n\n\n\n\n\n\n\n\n\n", 0)
        )
    }

    LazyColumn(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        item {
            Text(text = stringResource(R.string.complete_procedure_info))
            FormTextField(
                state = procedureName,
                onValueChange = { procedureName = it },
                label = stringResource(R.string.procedure_name),
                placeholder = stringResource(R.string.procedure_name)
            )
        }
        item {
            FormTextField(
                state = procedureDescription,
                onValueChange = { procedureDescription = it },
                label = stringResource(R.string.procedure_description),
                placeholder = stringResource(R.string.procedure_description)
            )
        }
        item {
            FormTextField(
                state = procedureAuthor,
                onValueChange = { procedureAuthor = it },
                label = stringResource(R.string.procedure_author),
                placeholder = stringResource(R.string.procedure_author)
            )
        }
        item {
            Text(
                text = stringResource(R.string.enter_procedure_code),
                Modifier.padding(top = 15.dp)
            )
            CodeEditor(
                code = interpreterUiState.codeEditorState.text,
                onCodeChange = { newText, newCursorPosition ->
                    interpreterViewModel.onEvent(InterpreterEvent.OnCodeChange(newText, newCursorPosition))
                },
                errors = interpreterUiState.errors.toString(),
                isSaveOnChange = false,
                modifier = Modifier,
                breakpoints = emptyList(),
                currentLine = -1,
                onSave = {  },
                isDarkMode =  interpreterUiState.isDarkMode,
                onToggleBreakpoint = { }
            )
        }
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                FormButton(
                    text = stringResource(R.string.cancel),
                    MaterialTheme.colorScheme.errorContainer
                ) {
                    navController.popBackStack()
                }
                FormButton(
                    text = stringResource(R.string.confirm),
                    MaterialTheme.colorScheme.primary
                ) {
                    val code = interpreterUiState.codeEditorState.text
                    val procedure = Procedure(
                        procedureName,
                        procedureDescription,
                        null,
                        code.trim()
                    )
                   libraryViewModel.addProcedureToLibrary(
                        uiState.actualLibrary!!,
                        procedure,
                        author = procedureAuthor,
                        onSuccess = {
                            interpreterViewModel.onEvent(InterpreterEvent.OnCodeChange("\n\n\n\n\n\n\n\n\n\n", 0))
                            navController.popBackStack()
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProcedureList() {
    LogoInterpreterBetaTheme {
        LibraryAddProcedureForm(
            libraryViewModel = hiltViewModel(),
            navController = rememberNavController(),
            interpreterViewModel = hiltViewModel(),
            modifier = Modifier
        )
    }
}