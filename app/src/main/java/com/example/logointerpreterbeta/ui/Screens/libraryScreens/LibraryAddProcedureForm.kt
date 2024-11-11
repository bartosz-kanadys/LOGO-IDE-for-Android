package com.example.logointerpreterbeta.ui.Screens.libraryScreens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.logointerpreterbeta.ui.components.codeEditor.CodeEditor
import com.example.logointerpreterbeta.ui.theme.AppTypography
import com.example.logointerpreterbeta.ui.theme.LogoInterpreterBetaTheme
import com.example.logointerpreterbeta.viewModels.InterpreterViewModel
import com.example.logointerpreterbeta.viewModels.LibraryViewModel
import com.example.logointerpreterbeta.viewModels.Procedure
import java.lang.reflect.Parameter

@Composable
fun LibraryAddProcedureForm(libraryViewModel: LibraryViewModel, interpreterViewModel: InterpreterViewModel, navController: NavController, modifier: Modifier) {
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
    LazyColumn (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .padding(15.dp)
    ){
        item{
            Text(text = "Uzupełnij informacje o procedurze")
            FormTextField(
                state = procedureName,
                onValueChange = {procedureName = it},
                label = "Nazwa procedury" ,
                placeholder = "Nazwa procedury")
        }
        item{
            FormTextField(
                state = procedureDescription,
                onValueChange = {procedureDescription= it},
                label = "Opis procedury" ,
                placeholder = "Opis procedury")
        }
        item{
            FormTextField(
                state = procedureAuthor,
                onValueChange = {procedureAuthor= it},
                label = "Autor procedury" ,
                placeholder = "Autor procedury")
        }
        item {
            Text(text = "Podaj kod procedury", Modifier.padding(top = 15.dp))
            CodeEditor(
                interpreterViewModel = interpreterViewModel,
                codeState = interpreterViewModel.codeState,
                onCodeChange = interpreterViewModel::onCodeChange,
                errors = interpreterViewModel.errors,
                isSaveOnChange = false,
                modifier = Modifier
                    .padding(top = 15.dp)

            )
        }
        item {
            Row(
                modifier = Modifier.fillMaxWidth().padding(top = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                FormButton(text = "Anuluj", MaterialTheme.colorScheme.errorContainer) { navController.popBackStack() }
                FormButton(text = "Zatwierdź", MaterialTheme.colorScheme.primary) {
                    val code = interpreterViewModel.codeState.text
                    val procedure = Procedure(
                        procedureName,
                        procedureDescription,
                        null,
                        code.trim())
                    if (libraryViewModel.checkProcedureAddForm(
                        procedureName,
                        procedureAuthor,
                        procedureDescription,
                        code.trim(),
                        context
                    )) {
                        libraryViewModel.addProcedureToLibrary(
                            context,
                            libraryViewModel.actualLibrary.value!!,
                            procedure)
                        interpreterViewModel.codeState = TextFieldValue("\n\n\n\n\n\n\n\n\n\n")
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
            libraryViewModel = LibraryViewModel(LocalContext.current),
            navController = rememberNavController(),
            interpreterViewModel = InterpreterViewModel(LocalContext.current),
            modifier = Modifier)
    }
}