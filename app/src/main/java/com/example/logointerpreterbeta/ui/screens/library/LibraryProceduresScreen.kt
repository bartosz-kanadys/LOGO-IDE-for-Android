package com.example.logointerpreterbeta.ui.screens.library

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.logointerpreterbeta.domain.interpreter.LogoInterpreter
import com.example.logointerpreterbeta.domain.interpreter.LogoTextColorizer
import com.example.logointerpreterbeta.ui.navigation.LibraryProcedureForm
import com.example.logointerpreterbeta.ui.screens.interpreter.components.codeEditor.CodeEditor
import com.example.logointerpreterbeta.ui.theme.AppTypography
import com.example.logointerpreterbeta.ui.theme.LogoInterpreterBetaTheme

@Composable
fun LibraryProceduresScreen(
    libraryViewModel: LibraryViewModel,
    navController: NavController,
    modifier: Modifier
) {
    val libraries = libraryViewModel.libraries.collectAsStateWithLifecycle()
    val actualLibrary = libraryViewModel.actualLibrary.collectAsStateWithLifecycle()
    var procedureToDelete by rememberSaveable {
        mutableStateOf<String?>(null)
    }

    val library = libraries.value.find { it.name == actualLibrary.value }

    AnimatedVisibility(procedureToDelete != null) {
        AlertDialog(
            title = {
                Text(text = "Usuń procedure")
            },
            text = { Text(text = "Czy napewno chcesz usunąć wybraną procedure?") },
            onDismissRequest = { },
            confirmButton = {
                TextButton(
                    onClick = {
                        libraryViewModel.deleteProcedureFromLibrary(
                            library!!.name,
                            procedureToDelete!!
                        )
                        procedureToDelete = null
                    }
                ) {
                    Text("Usuń")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        procedureToDelete = null
                    }
                ) {
                    Text("Wstecz")
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
                navController.navigate(LibraryProcedureForm)
            }
            Spacer(modifier = Modifier.height(10.dp))
        }
        if (library!!.procedures.isEmpty()) {
            item {
                Text(
                    text = "Nie ma jeszcze procedur w tej bibliotece",
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

@Composable
fun ProcedureCard(
    procedureName: String,
    procedureDescription: String,
    code: String,
    onDeleteClick: () -> Unit
) {
    val coloredCode = LogoTextColorizer.colorizeText(code)
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding()
    ) {

        val linesCount = code.lines().size
        Column(Modifier.padding(8.dp)) {
            Row(Modifier.fillMaxWidth()) {
                Column(Modifier.weight(1f)) {
                    Text(text = procedureName, fontSize = 26.sp)
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(text = procedureDescription, style = AppTypography.bodySmall)
                }
                TextButton(
                    onClick = { onDeleteClick() },
                ) {
                    Icon(imageVector = Icons.Filled.Delete, contentDescription = null)
                }
            }

            Spacer(modifier = Modifier.height(10.dp))
            CodeEditor(
                codeState = TextFieldValue(coloredCode),
                isSaveOnChange = false,
                isEnabled = false,
                isScrollable = false,
                lines = linesCount,
                modifier = Modifier.height((30 + (linesCount - 1) * 23).dp),
                breakpoints = emptyList(),
                currentLine = -1,
            )
        }
    }
}

@Composable
fun AddProcedureButton(onClick: () -> Unit) {
    Button(
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.inversePrimary),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(4.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = "Dodaj procedure",
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 18.sp
            )
        }

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewLibrrayProceduresScreen() {
    LogoInterpreterBetaTheme {
        //LibraryProceduresScreen(libraryViewModel = LibraryViewModel(LocalContext.current), navController = rememberNavController(), modifier = Modifier)
        Column {
            ProcedureCard(
                "Kwadrat",
                "Opis procedury kwadratu co robi",
                "fd 100 rt 90\nnnn\nnn\nn\n\nnn\nnn",
                {})
        }
    }
}