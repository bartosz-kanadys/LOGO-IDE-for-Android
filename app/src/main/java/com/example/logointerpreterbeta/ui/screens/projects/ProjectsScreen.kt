package com.example.logointerpreterbeta.ui.screens.projects

import android.annotation.SuppressLint
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.logointerpreterbeta.R
import com.example.logointerpreterbeta.ui.navigation.Interpreter
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
        onDeleteProjectFromList = { projectViewModel.deleteProjectFromList(it) },
        onCreatingProject = { projectViewModel.createNewProject(it) },
        onOpenProject = {
            projectViewModel.openProject(it)
            navController.navigate("InterpreterApp/$it")
        },
        modifier = modifier,
    )
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun ProjectsScreen(
    uiState: ProjectUiState,
    onDeleteProjectFromList: (String) -> Unit,
    onCreatingProject: (String) -> Boolean,
    onOpenProject: (String) -> Unit,
    modifier: Modifier
) {
    var newProjectName by rememberSaveable {
        mutableStateOf("")
    }
    var isTextFieldVisible by rememberSaveable {
        mutableStateOf(false)
    }
    var isErrorWhenCreatingProject by rememberSaveable {
        mutableStateOf(false)
    }
    var projectToDelete by rememberSaveable {
        mutableStateOf<String?>(null)
    }

    AnimatedVisibility(visible = projectToDelete != null) {
        AlertDialog(
            onDismissRequest = { projectToDelete = null },
            title = { Text(stringResource(R.string.confirm_delete)) },
            text = {
                Text(
                    stringResource(
                        R.string.confirm_delete_project_desc,
                        projectToDelete!!
                    )
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        onDeleteProjectFromList(projectToDelete!!)
                        projectToDelete = null
                    }
                ) {
                    Text(stringResource(R.string.delete))
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { projectToDelete = null } // Anuluj usunięcie
                ) {
                    Text(stringResource(R.string.cancel))
                }
            }
        )
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(top = 20.dp)
            .fillMaxSize()
    ) {
        CreateTextFieldWithNameButton {
            isTextFieldVisible = !isTextFieldVisible
            if (isErrorWhenCreatingProject) isErrorWhenCreatingProject = false
        }
        AnimatedVisibility(visible = isTextFieldVisible) {
            Row(
                Modifier
                    .height(60.dp)
                    .fillMaxWidth(0.9f)
            ) {
                ProjectNameTextField(
                    text = newProjectName,
                    Modifier.weight(0.8f)
                ) { newProjectName = it }
                CreateProjectButton(
                    Modifier
                        .weight(0.2f)
                        .height(60.dp)
                ) {
                    val success = onCreatingProject(newProjectName)
                    if (success) {
                        onOpenProject(newProjectName)
                    }
                    isErrorWhenCreatingProject = !success
                }
            }
        }
        AnimatedVisibility(visible = isErrorWhenCreatingProject) {
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
            items(uiState.projectsMap.entries.toList()) { (name, date) ->
                ProjectButton(
                    name = name,
                    date = date,
                    onOpenProject = { onOpenProject(it) },
                ) { projectToDelete = name }
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = false)
@Composable
fun ProjectsPreview() {
    LogoInterpreterBetaTheme(darkTheme = false) {
        ProjectsScreen(
            uiState = ProjectUiState(),
            onDeleteProjectFromList = {},
            onCreatingProject = { true },
            onOpenProject = {},
            modifier = Modifier
        )
    }
}