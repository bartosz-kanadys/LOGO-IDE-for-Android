package com.example.logointerpreterbeta.ui.screens.projects

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.logointerpreterbeta.R
import com.example.logointerpreterbeta.ui.screens.projects.components.CreateProjectButton
import com.example.logointerpreterbeta.ui.screens.projects.components.CreateTextFieldWithNameButton
import com.example.logointerpreterbeta.ui.screens.projects.components.ProjectButton
import com.example.logointerpreterbeta.ui.screens.projects.components.ProjectNameTextField
import com.example.logointerpreterbeta.ui.theme.LogoInterpreterBetaTheme

@Composable
fun ProjectScreenRoot(
    projectViewModel: ProjectViewModel,
    modifier: Modifier = Modifier,
    navController: NavController
) {
    val state = projectViewModel.uiState.collectAsStateWithLifecycle()

    ProjectsScreen(
        uiState = state.value,
        onDeleteProject = { projectViewModel.deleteProjectFromList(it) },
        onDeleteProjectClicked = { projectViewModel.onDeleteProjectClicked(it) },
        onCreatingProject = { projectViewModel.createNewProject() },
        onOpenProject = {
            projectViewModel.openProject(it)
            navController.navigate("InterpreterApp/$it")
        },
        onDismissAlert = { projectViewModel.dismissAlert() },
        onNameChange = { projectViewModel.onNewProjectNameChange(it) },
        onCreateProjectClicked = { projectViewModel.toggleCreateNewProject() },
        onNavigate = { navController.navigate("InterpreterApp/$it") },
        modifier = modifier,
    )
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun ProjectsScreen(
    uiState: ProjectUiState,
    onDeleteProject: (String) -> Unit,
    onDeleteProjectClicked: (String) -> Unit,
    onCreatingProject: () -> Unit,
    onOpenProject: (String) -> Unit,
    onDismissAlert: () -> Unit,
    onNameChange: (String) -> Unit,
    onCreateProjectClicked: () -> Unit,
    onNavigate: (String) -> Unit,
    modifier: Modifier
) {
    val context = LocalContext.current

    when (val alert = uiState.alertState) {
        is AlertState.ConfirmDelete -> {
            AlertDialog(
                onDismissRequest = { onDismissAlert() },
                title = { Text(stringResource(R.string.confirm_delete)) },
                text = {
                    Text(
                        stringResource(
                            R.string.confirm_delete_project_desc,
                            alert.projectName
                        )
                    )
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            onDeleteProject(alert.projectName)
                            onDismissAlert()
                        }
                    ) {
                        Text(stringResource(R.string.delete))
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = { onDismissAlert() }
                    ) {
                        Text(stringResource(R.string.cancel))
                    }
                }
            )
        }

        is AlertState.Success -> {
            onNavigate(alert.name)
            onDismissAlert()
        }

        else -> {}
    }

    LaunchedEffect(key1 = uiState.alertState) {
        when (uiState.alertState) {
            AlertState.GenericError -> {
                Toast.makeText(context, R.string.generic_error, Toast.LENGTH_SHORT).show()
                onDismissAlert()
            }

            AlertState.NameEmpty -> {
                Toast.makeText(context, R.string.name_empty, Toast.LENGTH_SHORT).show()
                onDismissAlert()
            }

            else -> {}
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(top = 20.dp)
            .fillMaxSize()
    ) {
        CreateTextFieldWithNameButton(
            modifier = Modifier
                .padding(vertical = 10.dp)
        ) {
            onCreateProjectClicked()
            onNameChange("")
            onDismissAlert()
        }
        AnimatedVisibility(uiState.isCreatingNewProject) {
            Row(
                Modifier
                    .height(70.dp)
                    .fillMaxWidth(0.9f)
            ) {
                ProjectNameTextField(
                    text = uiState.newProjectName,
                    Modifier.weight(0.8f)
                ) { onNameChange(it) }
                CreateProjectButton(
                    Modifier
                        .weight(0.2f)
                        .height(70.dp)
                ) {
                    onCreatingProject()
                }
            }
        }
        AnimatedVisibility(visible = uiState.alertState is AlertState.ProjectExists) {
            Text(
                text = stringResource(R.string.project_exist),
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.errorContainer,
                modifier = Modifier
                    .align(alignment = Alignment.CenterHorizontally)
                    .padding(5.dp)
                    .testTag("duplicateError")
            )
        }

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier.padding(top = 20.dp)
        ) {
            items(
                items = uiState.projectsMap.keys.toList(), // Iteruj po kluczach
                key = { projectName -> projectName } // Użyj nazwy projektu jako stabilnego klucza
            ) { name ->
                val date = uiState.projectsMap[name] ?: "" // Pobierz datę z mapy
                ProjectButton(
                    name = name,
                    date = date,
                    onOpenProject = { onOpenProject(it) },
                    onDelete = { onDeleteProjectClicked(it) }
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun ProjectsPreview() {
    LogoInterpreterBetaTheme(darkTheme = false) {
        ProjectsScreen(
            uiState = ProjectUiState().copy(isCreatingNewProject = true),
            onDeleteProject = {},
            onDeleteProjectClicked = {},
            onCreatingProject = { true },
            onOpenProject = {},
            modifier = Modifier,
            onNameChange = {},
            onCreateProjectClicked = {},
            onDismissAlert = {},
            onNavigate = {}
        )
    }
}