package com.example.logointerpreterbeta.activities

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.logointerpreterbeta.Projects.createFile
import com.example.logointerpreterbeta.Projects.deleteFile
import com.example.logointerpreterbeta.Projects.getProjectFromDirectory
import com.example.logointerpreterbeta.Projects.readFileContent
import com.example.logointerpreterbeta.Projects.writeFileContent
import com.example.logointerpreterbeta.components.ErrorsList
import com.example.logointerpreterbeta.components.ImagePanel
import com.example.logointerpreterbeta.components.codeEditor.CodeEditor
import com.example.logointerpreterbeta.ui.theme.LogoInterpreterBetaTheme
import com.example.logointerpreterbeta.viewModels.InterpreterViewModel
import java.io.File

class InterpreterActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContent {
//            LogoInterpreterBetaTheme {
//                //InterpreterApp(viewModel = viewModel)
//            }
//        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun InterpreterApp(
    viewModel: InterpreterViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    navController: NavController,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val isDark = isSystemInDarkTheme()
    val isDarkTheme by rememberSaveable { mutableStateOf(isDark) }
    var isAlertEmptyProjectVisable by rememberSaveable { mutableStateOf(false) }
    var isAlertNewFileVisable by rememberSaveable { mutableStateOf(false) }
    var newFileName by rememberSaveable { mutableStateOf("") }
    var project by rememberSaveable {
        mutableStateOf(
            getProjectFromDirectory(
            File(context.getExternalFilesDir(null),"Projects/${viewModel.acctualProjectName}")
        )
        )
    }

    var visibleMenuFileName by rememberSaveable { mutableStateOf<String?>(null) }
    var fileToDelete by rememberSaveable { mutableStateOf<String?>(null) }
    var acctualFile by rememberSaveable { mutableStateOf<String?>(project?.files?.firstOrNull()?.name) }
    viewModel.acctualFileName = acctualFile


    LaunchedEffect(isDarkTheme) {
        viewModel.interpretCode()
        viewModel.interpretCode()
        viewModel.colorCode()
    }
    LaunchedEffect(Unit) {

        project = getProjectFromDirectory(
            File(context.getExternalFilesDir(null),"Projects/${viewModel.acctualProjectName}")
        )
        acctualFile = project?.files?.firstOrNull()?.name
        viewModel.acctualFileName = acctualFile
        if (viewModel.acctualProjectName == "") {
            navController.navigate(StartScreen)
        }
        if (acctualFile != null) {
            viewModel.codeState = TextFieldValue(
                readFileContent(
                    context,
                    acctualFile!!,
                    project!!.name
                )!!
            )
            viewModel.colorCode()
            //viewModel.interpretCode("")
        }
    }

    //alert gdy pusty projekt
    AnimatedVisibility(visible = project!!.files.isEmpty()) {
        AlertDialog(
            title = {
                Text(text = "Pusty projekt")
            },
            text = {
                Column {
                    Text(text = "W projekcie '${viewModel.acctualProjectName}' nie ma jeszcze żadnego pliku! " +
                            "Wpisz niżej nazwę pierwszego pliku aby go utworzyć.")
                    TextField(value = newFileName, onValueChange = { newValue -> newFileName = newValue},Modifier.padding(top = 10.dp))
                }

            },
            onDismissRequest = {

            },
            confirmButton = {
                TextButton(
                    onClick = {
                        if (newFileName.isNotEmpty()) {
                            createFile(newFileName, viewModel.acctualProjectName, "", context)
                            isAlertEmptyProjectVisable = false
                            project = getProjectFromDirectory(
                                        File(
                                            context.getExternalFilesDir(null),
                                            "Projects/${viewModel.acctualProjectName}"
                                        )
                                    )
                            newFileName = ""
                            acctualFile = project!!.files[0].name
                            viewModel.acctualFileName = acctualFile

                        }
                    }
                ) {
                    Text("Dalej")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        isAlertEmptyProjectVisable = false
                        navController.popBackStack()
                    }
                ) {
                    Text("Wstecz")
                }
            }
            )
    }
    //alert gdy tworzenie nowego pliku
    AnimatedVisibility(visible = isAlertNewFileVisable) {
        AlertDialog(
            title = {
                Text(text = "Nowy plik")
            },
            text = {
                Column {
                    Text(text = "Podaj nazwę pliku, który zostanie dodany do projektu")
                    TextField(value = newFileName, onValueChange = { newValue -> newFileName = newValue},Modifier.padding(top = 10.dp))
                }

            },
            onDismissRequest = {

            },
            confirmButton = {
                TextButton(
                    onClick = {
                        if (newFileName.isNotEmpty()) {
                            createFile(newFileName, viewModel.acctualProjectName, "", context)
                            isAlertNewFileVisable = false
                            project = getProjectFromDirectory(
                                File(context.getExternalFilesDir(null),"Projects/${viewModel.acctualProjectName}")
                            )
                            newFileName = ""
                        }
                    }
                ) {
                    Text("Dalej")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        isAlertNewFileVisable = false
                    }
                ) {
                    Text("Wstecz")
                }
            }
        )
    }
    //alert potwierdzenia usuniecia
    AnimatedVisibility(visible = fileToDelete != null) {
        AlertDialog(
            onDismissRequest = { fileToDelete = null },
            title = { Text("Potwierdzenie usunięcia") },
            text = { Text("Czy na pewno chcesz usunąć plik '${fileToDelete}'?") },
            confirmButton = {
                TextButton(
                    onClick = {
                        deleteFile(fileToDelete!!, project!!.name, context)
                        project = getProjectFromDirectory(
                            File(
                                context.getExternalFilesDir(null),
                                "Projects/${viewModel.acctualProjectName}"
                            )
                        )
                        fileToDelete = null // Wyczyść nazwę po usunięciu
                        acctualFile = project!!.files[0].name
                    }
                ) {
                    Text("Usuń")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { fileToDelete = null } // Anuluj usunięcie
                ) {
                    Text("Anuluj")
                }
            }
        )
    }

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {
            Box(
                modifier = Modifier
                    // .height(400.dp)
                    .fillParentMaxHeight(0.6f)
                    .fillMaxWidth()

            ){
                ImagePanel()

                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomStart)
                        .height(35.dp)
                        .padding(horizontal = 2.dp, vertical = 0.dp)
                ) {
                    items(project!!.files) { projectFile ->
                        Box(modifier = Modifier
                            .background(
                                if (acctualFile == projectFile.name)
                                    MaterialTheme.colorScheme.inversePrimary
                                else
                                    MaterialTheme.colorScheme.secondaryContainer,
                                RoundedCornerShape(12.dp, 12.dp, 0.dp, 0.dp)
                            )
                            .pointerInput(Unit) {
                                detectTapGestures(
                                    onLongPress = {
                                        visibleMenuFileName = projectFile.name
                                    },
                                    onTap = {
                                        writeFileContent(
                                            context,
                                            acctualFile!!,
                                            project!!.name,
                                            viewModel.codeState.text
                                        )
                                        viewModel.codeState = TextFieldValue(
                                            readFileContent(
                                                context,
                                                projectFile.name,
                                                project!!.name
                                            )!!
                                        )
                                        viewModel.colorCode()
                                        acctualFile = projectFile.name
                                        viewModel.acctualFileName = acctualFile!!
                                    }
                                )
                            }
                            .padding(horizontal = 15.dp, vertical = 6.dp)
                        ) {
                            Text(text = projectFile.name)
                            DropdownMenu(
                                expanded = visibleMenuFileName == projectFile.name,
                                onDismissRequest = { visibleMenuFileName = null }
                            ) {
                                DropdownMenuItem(
                                    text = { Text("Usuń") },
                                    onClick = {
                                        fileToDelete = projectFile.name
                                        visibleMenuFileName = null
                                    }
                                )
                            }
                        }

                    }
                    item {
                        TextButton(
                            onClick = { isAlertNewFileVisable = true },
                            contentPadding = PaddingValues(0.dp),
                            modifier = Modifier
                                .width(30.dp)
                        ) {
                            Icon(imageVector = Icons.Filled.Add, contentDescription = null)
                        }
                    }
                }
            }
        }
        item {
            ErrorsList(
                errors = viewModel.errors,
                isErrorListVisable = viewModel.isErrorListVisable,
                isErrorListExpanded = viewModel.isErrorListExpanded,
                onClick = { viewModel.toggleErrorListVisibility() }
            )
        }
        item {
            Box {
                CodeEditor(
                    viewModel = viewModel,
                    codeState = viewModel.codeState,
                    onCodeChange = viewModel::onCodeChange,
                    errors = viewModel.errors,
                    modifier = Modifier
                )
                Button(
                    shape = CircleShape,
                    contentPadding = PaddingValues(0.dp),
                    colors = ButtonDefaults.buttonColors(
                        MaterialTheme.colorScheme.primaryContainer
                    ),
                    onClick = { viewModel.interpretCode() },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(top = 15.dp, end = 5.dp)
                        .width(45.dp)
                        .height(45.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.PlayArrow,
                        contentDescription = null,
                        modifier = Modifier
                            .width(40.dp)
                            .align(Alignment.CenterVertically)
                    )
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Preview() {
    LogoInterpreterBetaTheme(darkTheme = false) {
        InterpreterApp(viewModel = InterpreterViewModel(context = LocalContext.current), navController = rememberNavController())
    }
}
