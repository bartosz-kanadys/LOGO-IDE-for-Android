package com.example.logointerpreterbeta.ui.screens.library.components

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.logointerpreterbeta.ui.screens.interpreter.components.codeEditor.CodeEditor
import com.example.logointerpreterbeta.ui.theme.LogoInterpreterBetaTheme
import com.example.logointerpreterbeta.ui.screens.interpreter.InterpreterViewModel
import com.example.logointerpreterbeta.domain.models.Procedure
import com.example.logointerpreterbeta.ui.screens.library.FormButton
import com.example.logointerpreterbeta.ui.screens.library.FormTextField
import com.example.logointerpreterbeta.ui.screens.library.LibraryViewModel

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

    LaunchedEffect(Unit) {
        interpreterViewModel.updateCodeState("\n\n\n\n\n\n\n\n\n\n\n\n")
    }

    LazyColumn(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        item {
            Text(text = "Uzupełnij informacje o procedurze")
            FormTextField(
                state = procedureName,
                onValueChange = { procedureName = it },
                label = "Nazwa procedury",
                placeholder = "Nazwa procedury"
            )
        }
        item {
            FormTextField(
                state = procedureDescription,
                onValueChange = { procedureDescription = it },
                label = "Opis procedury",
                placeholder = "Opis procedury"
            )
        }
        item {
            FormTextField(
                state = procedureAuthor,
                onValueChange = { procedureAuthor = it },
                label = "Autor procedury",
                placeholder = "Autor procedury"
            )
        }
        item {
            Text(text = "Podaj kod procedury", Modifier.padding(top = 15.dp))
            CodeEditor(
                codeState = interpreterViewModel.getCodeStateAsTextFieldValue(),
                onCodeChange = interpreterViewModel::onCodeChange,
                errors = interpreterViewModel.errors.collectAsStateWithLifecycle().toString(),
                isSaveOnChange = false,
                modifier = Modifier,
                breakpoints = emptyList(),
                currentLine = -1
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
                    text = "Anuluj",
                    MaterialTheme.colorScheme.errorContainer
                ) { navController.popBackStack() }
                FormButton(text = "Zatwierdź", MaterialTheme.colorScheme.primary) {
                    val code = interpreterViewModel.getCodeStateAsString()
                    val procedure = Procedure(
                        procedureName,
                        procedureDescription,
                        null,
                        code.trim()
                    )
                    if (libraryViewModel.checkProcedureAddForm(
                            procedureName,
                            procedureAuthor,
                            procedureDescription,
                            code.trim(),
                            context
                        )
                    ) {
                        libraryViewModel.addProcedureToLibrary(
                            libraryViewModel.actualLibrary.value!!,
                            procedure
                        )
                        interpreterViewModel.updateCodeState("\n\n\n\n\n\n\n\n\n\n")
                        navController.popBackStack()
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Procedurelist() {
    LogoInterpreterBetaTheme {
        LibraryAddProcedureForm(
            libraryViewModel = hiltViewModel(),
            navController = rememberNavController(),
            interpreterViewModel = hiltViewModel(),
            modifier = Modifier
        )
    }
}