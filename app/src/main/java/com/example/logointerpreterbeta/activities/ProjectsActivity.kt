package com.example.logointerpreterbeta.activities

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material.icons.filled.Draw
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.logointerpreterbeta.Projects.createProject
import com.example.logointerpreterbeta.Projects.deleteProject
import com.example.logointerpreterbeta.Projects.getProjectFoldersMap
import com.example.logointerpreterbeta.Projects.updateLastModifiedProjectJSON
import com.example.logointerpreterbeta.ui.theme.LogoInterpreterBetaTheme
import com.example.logointerpreterbeta.viewModels.InterpreterViewModel


//class ProjectsActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContent {
//
////            LogoInterpreterBetaTheme {
////                Scaffold(
////                    topBar = {
////                        TopBarWithMenu("Projekty")
////                    },
////                    modifier = Modifier.padding(0.dp)
////                ) { innerPadding ->
////                    ProjectsApp(viewModel, Modifier.padding(innerPadding), navController = rememberNavController())
////                }
////            }
//        }
//    }
//}

@SuppressLint("UnrememberedMutableState")
@Composable
fun ProjectsApp(viewModel: InterpreterViewModel,modifier: Modifier = Modifier, navController: NavController) {
    val context = LocalContext.current
    var projectName by rememberSaveable {
        mutableStateOf("")
    }
    var isTextFieldVisable by rememberSaveable {
        mutableStateOf(false)
    }
    var isErrorWhenCreatingProject by rememberSaveable {
        mutableStateOf(false)
    }
    var projects by rememberSaveable {
        mutableStateOf(getProjectFoldersMap(context))
    }
    var projectToDelete by rememberSaveable {
        mutableStateOf<String?>(null)
    }

    AnimatedVisibility(visible = projectToDelete != null) {
        AlertDialog(
            onDismissRequest = { projectToDelete = null },
            title = { Text("Potwierdzenie usunięcia") },
            text = { Text("Czy na pewno chcesz usunąć projekt '${projectToDelete}'?") },
            confirmButton = {
                TextButton(
                    onClick = {
                        if (viewModel.acctualProjectName == projectToDelete) {
                            viewModel.acctualProjectName = ""
                            viewModel.acctualFileName = null
                        }
                        deleteProject(projectToDelete!!, context)
                        projects = getProjectFoldersMap(context)
                        updateLastModifiedProjectJSON(context,"")

                        projectToDelete = null
                    }
                ) {
                    Text("Usuń")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { projectToDelete = null } // Anuluj usunięcie
                ) {
                    Text("Anuluj")
                }
            }
        )
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(top = 20.dp)
            .fillMaxSize()
            .then(modifier)
    ) {
        CreateTextFieldWithNameButton {
            isTextFieldVisable = !isTextFieldVisable
            if (isErrorWhenCreatingProject) isErrorWhenCreatingProject = false
        }
        AnimatedVisibility(visible = isTextFieldVisable) {
            Row (
                Modifier
                    .height(60.dp)
                    .fillMaxWidth(0.9f)){
                ProjectNameTextField(text = projectName, Modifier.weight(0.8f)) { projectName = it }
                CreateProjectButton(Modifier.weight(0.2f)) {
                    isErrorWhenCreatingProject = !createProject(projectName,context)
                    viewModel.upadteAcctualProject(context,projectName)
                    navController.navigate(Interpreter)
                }
            }
        }
        AnimatedVisibility(visible = isErrorWhenCreatingProject) {
            Text(
                text = "Projekt o podanej nazwie już istnieje!",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.errorContainer,
                modifier = Modifier
                    .align(alignment = Alignment.CenterHorizontally)
                    .padding(5.dp)
            )
        }

        LazyColumn (Modifier.padding(top = 20.dp)){
            items(projects.entries.toList()) { (name, date) ->
                ProjectButton(name = name, date = date, viewModel, navController) { projectToDelete = name }
                Spacer(modifier = Modifier.height(20.dp))
            }
        }


    }
}

@Composable
private fun TextFieldIcon() {
    Icon(imageVector = Icons.Filled.Draw, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
}

@Composable
private fun CreateTextFieldWithNameButton(onClick: () -> Unit) {
    Button(
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.inversePrimary),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth(0.9f)
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
                text = "Nowy projekt",
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 18.sp
            )
        }

    }
}

@Composable
private fun ProjectButton(name: String, date: String, viewModel: InterpreterViewModel, navController: NavController, onDelete: () -> Unit) {
    LocalContext.current
    OutlinedButton(
        onClick = {
            viewModel.acctualProjectName = name
            navController.navigate(Interpreter)
         },
        shape = RoundedCornerShape(8.dp),
        contentPadding = PaddingValues(0.dp),
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .height(80.dp)
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(start = 15.dp, end = 7.dp)
                .align(Alignment.CenterVertically)
        ) {
            Column(modifier = Modifier.weight(0.8f)) {
                Text(text = name, textAlign = TextAlign.Left, fontSize = 20.sp)
                HorizontalDivider(
                    thickness = 1.dp,
                    color = MaterialTheme.colorScheme.inversePrimary,
                )
                Text(text = date, textAlign = TextAlign.Right, fontSize = 10.sp)
            }
            TextButton(
                onClick = { onDelete() },
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Icon(imageVector = Icons.Filled.DeleteForever, contentDescription = null)
            }


        }

    }
}

@Composable
private fun ProjectNameTextField(text: String, modifier: Modifier = Modifier, onValueChange: (String) -> Unit ) {
    OutlinedTextField(
        value = text,
        onValueChange = {newValue -> onValueChange(newValue)},
        leadingIcon = { TextFieldIcon() },
        label = { Text(text = "Nazwa projeku")},
        placeholder = { Text(text = "Podaj nazwę")},
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.secondary
        ),
        modifier = modifier
            .fillMaxWidth(0.9f)
            .fillMaxHeight(),
        shape = RoundedCornerShape(8.dp)
    )
}

@Composable
private fun CreateProjectButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        onClick = { onClick() },
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
            .fillMaxSize()
            .padding(start = 5.dp, top = 5.5.dp)

    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onPrimary
        )
    }
}



@Preview(showBackground = true, showSystemUi = false)
@Composable
fun ProjectsPreview() {
    LogoInterpreterBetaTheme(darkTheme = false) {

    ProjectsApp(viewModel = InterpreterViewModel(LocalContext.current), navController = rememberNavController())
    }
}