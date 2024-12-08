package com.example.logointerpreterbeta.ui.screens

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Configuration
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.logointerpreterbeta.models.Project
import com.example.logointerpreterbeta.models.ProjectFile
import com.example.logointerpreterbeta.navigation.StartScreen
import com.example.logointerpreterbeta.repository.FileRepository
import com.example.logointerpreterbeta.ui.components.Alert
import com.example.logointerpreterbeta.ui.components.ErrorsList
import com.example.logointerpreterbeta.ui.components.ImagePanel
import com.example.logointerpreterbeta.ui.components.InterpreterButtons
import com.example.logointerpreterbeta.ui.components.codeEditor.CodeEditor
import com.example.logointerpreterbeta.ui.theme.LogoInterpreterBetaTheme
import com.example.logointerpreterbeta.viewModels.InterpreterViewModel
import com.example.logointerpreterbeta.viewModels.ProjectViewModel
import com.example.logointerpreterbeta.visitors.DebuggerVisitor


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun InterpreterApp(
    interpreterViewModel: InterpreterViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    projectViewModel: ProjectViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    navController: NavController,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val isDark = isSystemInDarkTheme()
    val isDarkTheme by rememberSaveable { mutableStateOf(isDark) }
    var isAlertEmptyProjectVisable by rememberSaveable { mutableStateOf(false) }
    var isAlertNewFileVisable by rememberSaveable { mutableStateOf(false) }
    var newFileName by rememberSaveable { mutableStateOf("") }

    var visibleMenuFileName by rememberSaveable { mutableStateOf<String?>(null) }
    var fileToDelete by rememberSaveable { mutableStateOf<String?>(null) }

    val actualFileName by projectViewModel.actualFileName.collectAsStateWithLifecycle()
    val actualProjectName by projectViewModel.actualProjectName.collectAsStateWithLifecycle()
    val project by projectViewModel.project.collectAsStateWithLifecycle()

    val fileRepository = FileRepository(context)

    LaunchedEffect(isDarkTheme) {
        interpreterViewModel.interpretCode()
        interpreterViewModel.interpretCode()
        interpreterViewModel.colorCode()
    }
    LaunchedEffect(Unit) {
        if (actualProjectName == "") {
            navController.navigate(StartScreen)
            return@LaunchedEffect
        }
        projectViewModel.updateProject()
        isAlertEmptyProjectVisable = project!!.files.isEmpty()
        projectViewModel.updateActualFileName(project?.files?.firstOrNull()?.name)

        if (actualProjectName.isEmpty()) {
            navController.navigate(StartScreen)
        }
        val actualFile = project?.files?.firstOrNull()?.name

        if (actualFile != null) {
            val content = fileRepository.readFileContent(context, actualFile, project!!.name)
            if (content != null) {
                interpreterViewModel.updateCodeState(content)
                interpreterViewModel.colorCode()
                interpreterViewModel.interpretCode("")
            }
        } else {
            interpreterViewModel.updateCodeState("")
            isAlertEmptyProjectVisable = true
        }

    }

    //alert gdy pusty projekt
    Alert(
        isVisible = isAlertEmptyProjectVisable,
        title = "Pusty projekt",
        content = "W projekcie '${actualProjectName}' nie ma jeszcze żadnego pliku! Wpisz niżej nazwę pierwszego pliku aby go utworzyć.",
        confirmButtonAction = {
            if (newFileName.isNotEmpty()) {
                projectViewModel.createFileInEmptyProject(newFileName)
                interpreterViewModel.interpretCode("")
                isAlertEmptyProjectVisable = false
                newFileName = ""
            }
        },
        dismissButtonAction = {
            isAlertEmptyProjectVisable = false
            navController.popBackStack()
        },
        textField = {
            TextField(
                value = newFileName,
                onValueChange = { newValue -> newFileName = newValue },
            )
        }
    )

    //alert gdy tworznie nowego pliku
    Alert(
        isVisible = isAlertNewFileVisable,
        title = "Nowy plik",
        content = "Podaj nazwę pliku, który zostanie dodany do projektu,",
        confirmButtonAction = {
            if (newFileName.isNotEmpty()) {
                projectViewModel.createFileInProject(newFileName)
                isAlertNewFileVisable = false
                newFileName = ""
            }
        },
        dismissButtonAction = { isAlertNewFileVisable = false },
        textField = {
            TextField(
                value = newFileName,
                onValueChange = { newValue -> newFileName = newValue }
            )
        }
    )

    //alert potwierdzenia usuniecia
    Alert(
        isVisible = fileToDelete != null,
        title = "Potwierdzenie usunięcia",
        content = "Czy na pewno chcesz usunąć plik '${fileToDelete}'?",
        confirmButtonAction = {
            isAlertEmptyProjectVisable =
                projectViewModel.deleteFileFromProject(fileToDelete!!)
            fileToDelete = null
        },
        dismissButtonAction = { fileToDelete = null },
        confirmButtonText = "Usuń"
    )

    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
    if (isLandscape) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.55f)
                    .zIndex(2f)
                    .background(MaterialTheme.colorScheme.surface)
            ) {
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(35.dp)
                        .padding(horizontal = 2.dp, vertical = 0.dp)
                ) {
                    project?.files?.let { files ->
                        items(files) { projectFile ->
                            Box(modifier = Modifier
                                .background(
                                    if (actualFileName == projectFile.name)
                                        MaterialTheme.colorScheme.tertiaryContainer
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
                                            onTapFileAction(
                                                interpreterViewModel,
                                                fileRepository,
                                                context,
                                                projectFile,
                                                project,
                                                projectViewModel
                                            )
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
                ErrorsList(
                    errors = InterpreterViewModel.errors.collectAsStateWithLifecycle().value.toString(),
                    isErrorListVisable = InterpreterViewModel.isErrorListVisable,
                    isErrorListExpanded = interpreterViewModel.isErrorListExpanded,
                    onClick = { interpreterViewModel.toggleErrorListVisibility() }
                )
                Box {
                    CodeEditor(
                        projectViewModel = projectViewModel,
                        interpreterViewModel = interpreterViewModel,
                        codeState = interpreterViewModel.getCodeStateAsTextFieldValue(),
                        onCodeChange = interpreterViewModel::onCodeChange,
                        errors = InterpreterViewModel.errors.collectAsStateWithLifecycle().value.toString(),
                        modifier = Modifier
                    )
                    InterpreterButtons(interpreterViewModel)
                }
            }
            Box(
                modifier = Modifier
                    //.fillMaxWidth(0.5f)
                    .zIndex(1f)
            ) {
                ImagePanel()
            }
        }
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
        ) {
            item {
                Box(
                    modifier = Modifier
                        .fillParentMaxHeight(0.6f)
                        .fillMaxWidth()
                ) {
                    ImagePanel()

                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.BottomStart)
                            .height(35.dp)
                            .padding(horizontal = 2.dp, vertical = 0.dp)
                    ) {
                        project?.files?.let { files ->
                            items(files) { projectFile ->
                                Box(modifier = Modifier
                                    .background(
                                        if (actualFileName == projectFile.name)
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
                                                onTapFileAction(
                                                    interpreterViewModel,
                                                    fileRepository,
                                                    context,
                                                    projectFile,
                                                    project,
                                                    projectViewModel
                                                )
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
                    errors = InterpreterViewModel.errors.collectAsStateWithLifecycle().value.toString(),
                    isErrorListVisable = InterpreterViewModel.isErrorListVisable,
                    isErrorListExpanded = interpreterViewModel.isErrorListExpanded,
                    onClick = { interpreterViewModel.toggleErrorListVisibility() }
                )
            }
            item {
                Box {
                    CodeEditor(
                        projectViewModel = projectViewModel,
                        interpreterViewModel = interpreterViewModel,
                        fileRepository = fileRepository,
                        codeState = interpreterViewModel.getCodeStateAsTextFieldValue(),
                        onCodeChange = interpreterViewModel::onCodeChange,
                        errors = InterpreterViewModel.errors.collectAsStateWithLifecycle().value.toString(),
                        modifier = Modifier
                    )
                    InterpreterButtons(interpreterViewModel)
                }
            }
        }
    }


}


private fun onTapFileAction(
    interpreterViewModel: InterpreterViewModel,
    fileRepository: FileRepository,
    context: Context,
    projectFile: ProjectFile,
    project: Project?,
    projectViewModel: ProjectViewModel
) {
    DebuggerVisitor.breakpoints.clear()
    interpreterViewModel.updateCodeState(
        fileRepository.readFileContent(
            context,
            projectFile.name,
            project!!.name
        )!!
    )
    interpreterViewModel.colorCode()
    projectViewModel.updateActualFileName(projectFile.name)
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Preview() {
    LogoInterpreterBetaTheme(darkTheme = false) {
        InterpreterApp(
            interpreterViewModel = hiltViewModel(),
            navController = rememberNavController()
        )
    }
}
