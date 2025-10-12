package com.example.logointerpreterbeta.ui.screens.interpreter

import android.content.res.Configuration
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
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
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.logointerpreterbeta.R
import com.example.logointerpreterbeta.domain.models.Project
import com.example.logointerpreterbeta.ui.navigation.StartScreen
import com.example.logointerpreterbeta.ui.screens.interpreter.components.Alert
import com.example.logointerpreterbeta.ui.screens.interpreter.components.ErrorsList
import com.example.logointerpreterbeta.ui.screens.interpreter.components.ImagePanel
import com.example.logointerpreterbeta.ui.screens.interpreter.components.InterpreterButtons
import com.example.logointerpreterbeta.ui.screens.interpreter.components.codeEditor.CodeEditor
import com.example.logointerpreterbeta.ui.screens.projects.ProjectViewModel
import com.example.logointerpreterbeta.ui.screens.settings.SettingsUiState

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun InterpreterApp(
    projectName: String?,
    interpreterViewModel: InterpreterViewModel = viewModel(),
    projectViewModel: ProjectViewModel = viewModel(),
    navController: NavController,
    config: SettingsUiState
) {
    var isAlertEmptyProjectVisible by rememberSaveable { mutableStateOf(false) }
    var isAlertNewFileVisible by rememberSaveable { mutableStateOf(false) }
    var newFileName by rememberSaveable { mutableStateOf("") }
    var visibleMenuFileName by rememberSaveable { mutableStateOf<String?>(null) }
    var fileToDelete by rememberSaveable { mutableStateOf<String?>(null) }

    val errors by interpreterViewModel.errors.collectAsStateWithLifecycle()
    val image by interpreterViewModel.img.collectAsStateWithLifecycle()
    val arrowImage by interpreterViewModel.arrowImg.collectAsStateWithLifecycle()
    val debuggerState by interpreterViewModel.debuggerState.collectAsStateWithLifecycle()
    val turtleState by interpreterViewModel.turtleState.collectAsStateWithLifecycle()
    val isErrorListExpanded by interpreterViewModel.isErrorListExpanded.collectAsStateWithLifecycle()
    val projectState by projectViewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        if (projectName != null) {
            projectViewModel.updateActualProjectName(projectName)
            debuggerState.breakpoints.clear()
        } else {
            navController.navigate(StartScreen)
        }
    }

    LaunchedEffect(projectState.actualProjectName) {
        if (projectState.actualProjectName.isEmpty()) {
            navController.navigate(StartScreen)
            return@LaunchedEffect
        } else {
            projectViewModel.updateProject()
        }
    }

    LaunchedEffect(projectState.project) {
        projectViewModel.updateActualFileName(projectState.project?.files?.firstOrNull()?.name)

        val actualFile = projectState.project?.files?.firstOrNull()?.name

        if (actualFile != null) {
            val content = interpreterViewModel.readFileFromRepository( actualFile, projectState.actualProjectName)
            content.onSuccess {
                interpreterViewModel.updateCodeState(it)
                interpreterViewModel.colorCode()
                interpreterViewModel.interpretCode("")
            }
//            if (content != null) {
//                interpreterViewModel.updateCodeState(content)
//                interpreterViewModel.colorCode()
//                interpreterViewModel.interpretCode("")
//            }
        } else {
            interpreterViewModel.updateCodeState("")
            isAlertEmptyProjectVisible = true
        }
        isAlertEmptyProjectVisible = projectState.project?.files?.isEmpty() ?: false
    }

    //alert when project is empty
    Alert(
        isVisible = isAlertEmptyProjectVisible,
        title = stringResource(R.string.empty_project),
        content = stringResource(R.string.empty_project_full_text, projectState.actualProjectName),
        confirmButtonAction = {
            if (newFileName.isNotEmpty()) {
                projectViewModel.createFileInEmptyProject(newFileName)
                interpreterViewModel.interpretCode("")
                isAlertEmptyProjectVisible = false
                newFileName = ""
            }
        },
        dismissButtonAction = {
            isAlertEmptyProjectVisible = false
            navController.popBackStack()
        },
        textField = {
            TextField(
                value = newFileName,
                onValueChange = { newValue -> newFileName = newValue }
                )
        },
        confirmButtonText = R.string.continue_step
    )

    //alert when creating new file
    Alert(
        isVisible = isAlertNewFileVisible,
        title = stringResource(R.string.new_file),
        content = stringResource(R.string.enter_new_file_name_text),
        confirmButtonAction = {
            if (newFileName.isNotEmpty()) {
                projectViewModel.createFileInProject(newFileName)
                isAlertNewFileVisible = false
                newFileName = ""
            }
        },
        dismissButtonAction = { isAlertNewFileVisible = false },
        textField = {
            TextField(
                value = newFileName,
                onValueChange = { newValue -> newFileName = newValue }
            )
        },
        confirmButtonText = R.string.continue_step
    )

    //alert file to delete
    Alert(
        isVisible = fileToDelete != null,
        title = stringResource(R.string.confirm_delete_file),
        content = stringResource(R.string.confirm_delete_file_full_text, fileToDelete ?: ""),
        confirmButtonAction = {
            isAlertEmptyProjectVisible =
                projectViewModel.deleteFileFromProject(fileToDelete!!)
            fileToDelete = null
        },
        dismissButtonAction = { fileToDelete = null },
        confirmButtonText = R.string.delete
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
                    projectState.project?.files?.let { files ->
                        items(files) { projectFile ->
                            Box(modifier = Modifier
                                .background(
                                    if (projectState.actualFileName == projectFile.name)
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
                                            interpreterViewModel.onTapFileAction(
                                                projectFile = projectFile,
                                                project = projectState.project,
                                                updateActualFileName = { projectViewModel.updateActualFileName(projectFile.name) }
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
                                        text = { Text(stringResource(R.string.delete)) },
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
                            onClick = { isAlertNewFileVisible = true },
                            contentPadding = PaddingValues(0.dp),
                            modifier = Modifier
                                .width(30.dp)
                        ) {
                            Icon(imageVector = Icons.Filled.Add, contentDescription = null)
                        }
                    }
                }
                ErrorsList(
                    errors = errors.toString(),
                    isErrorListVisible = errors.isNotEmpty(),
                    isErrorListExpanded = isErrorListExpanded,
                    onClick = { interpreterViewModel.toggleErrorListVisibility() }
                )
                Box {
                    CodeEditor(
                        codeState = interpreterViewModel.getCodeStateAsTextFieldValue(),
                        onCodeChange = interpreterViewModel::onCodeChange,
                        errors = errors.toString(),
                        breakpoints = debuggerState.breakpoints,
                        currentLine = debuggerState.currentLine,
                        fontFamily = config.currentFont.value,
                        fontSize = config.currentFontSize,
                        onSave = {
                            interpreterViewModel.saveFile(
                                projectState.actualFileName!!,
                                projectState.actualProjectName,
                                it
                            )
                        },
                        onToggleBreakpoint = {
                            interpreterViewModel.toggleBreakpoint(it)
                        }
                    )
                    InterpreterButtons(
                        viewModel = interpreterViewModel,
                        isDebugging = debuggerState.isDebugging,
                        isStepInButtonVisible = debuggerState.showStepInButton,
                        isStepOutButtonVisible = debuggerState.showStepOutButton,
                    )
                }
            }
            Box(
                modifier = Modifier
                    //.fillMaxWidth(0.5f)
                    .zIndex(1f)
            ) {
                ImagePanel(turtleState = turtleState, image.asImageBitmap(), arrowImage.asImageBitmap())
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
                    ImagePanel(turtleState = turtleState, image.asImageBitmap(), arrowImage.asImageBitmap())

                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.BottomStart)
                            .height(35.dp)
                            .padding(horizontal = 2.dp, vertical = 0.dp)
                    ) {
                        projectState.project?.files?.let { files ->
                            items(files) { projectFile ->
                                Box(modifier = Modifier
                                    .background(
                                        if (projectState.actualFileName == projectFile.name)
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
                                                interpreterViewModel.onTapFileAction(
                                                    projectFile = projectFile,
                                                    project = projectState.project,
                                                    updateActualFileName = { projectViewModel.updateActualFileName(projectFile.name) }
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
                                            text = { Text(stringResource(R.string.delete)) },
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
                                onClick = { isAlertNewFileVisible = true },
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
                    errors = errors.toString(),
                    isErrorListVisible = errors.isNotEmpty(),
                    isErrorListExpanded = isErrorListExpanded,
                    onClick = { interpreterViewModel.toggleErrorListVisibility() }
                )
            }
            item {
                Box {
                    CodeEditor(
                        codeState = interpreterViewModel.getCodeStateAsTextFieldValue(),
                        onCodeChange = interpreterViewModel::onCodeChange,
                        errors = errors.toString(),
                        modifier = Modifier,
                        breakpoints = debuggerState.breakpoints,
                        currentLine = debuggerState.currentLine,
                        fontFamily = config.currentFont.value,
                        fontSize = config.currentFontSize,
                        onToggleBreakpoint = {
                            interpreterViewModel.toggleBreakpoint(it)
                        },
                        onSave = {
                            interpreterViewModel.saveFile(
                                projectState.actualFileName!!,
                                projectState.actualProjectName,
                                it
                            )
                        }
                    )
                    InterpreterButtons(
                        viewModel = interpreterViewModel,
                        isDebugging = debuggerState.isDebugging,
                        isStepInButtonVisible = debuggerState.showStepInButton,
                        isStepOutButtonVisible = debuggerState.showStepOutButton,
                    )
                }
            }
        }
    }
}

//@RequiresApi(Build.VERSION_CODES.O)
//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun Preview() {
//    LogoInterpreterBetaTheme(darkTheme = false) {
//        InterpreterApp(
//            interpreterViewModel = hiltViewModel(),
//            navController = rememberNavController(),
//            configRepository = null
//        )
//    }
//}
