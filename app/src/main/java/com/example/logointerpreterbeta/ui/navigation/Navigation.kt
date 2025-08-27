package com.example.logointerpreterbeta.ui.navigation

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.logointerpreterbeta.ui.navigation.topBars.InterpreterTopBar
import com.example.logointerpreterbeta.ui.navigation.topBars.TopBarWithMenu
import com.example.logointerpreterbeta.data.repository.ConfigRepositoryImpl
import com.example.logointerpreterbeta.ui.screens.interpreter.InterpreterApp
import com.example.logointerpreterbeta.ui.screens.projects.ProjectsApp
import com.example.logointerpreterbeta.ui.screens.settings.SettingsApp
import com.example.logointerpreterbeta.ui.screens.start.StartScreenApp
import com.example.logointerpreterbeta.ui.screens.library.components.LibraryAddProcedureForm
import com.example.logointerpreterbeta.ui.screens.library.LibraryFormScreen
import com.example.logointerpreterbeta.ui.screens.library.LibraryProceduresScreen
import com.example.logointerpreterbeta.ui.screens.library.LibraryScreen
import com.example.logointerpreterbeta.ui.screens.tutorial.TutorialContentScreen
import com.example.logointerpreterbeta.ui.screens.tutorial.TutorialScreen
import com.example.logointerpreterbeta.ui.theme.LogoInterpreterBetaTheme
import com.example.logointerpreterbeta.ui.screens.interpreter.InterpreterViewModel
import com.example.logointerpreterbeta.ui.screens.library.LibraryViewModel
import com.example.logointerpreterbeta.ui.screens.projects.ProjectViewModel
import com.example.logointerpreterbeta.ui.screens.settings.SettingsViewModel
import com.example.logointerpreterbeta.ui.theme.ThemeMode
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.Serializable

@SuppressLint("NewApi", "StateFlowValueCalledInComposition")
@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: Any = StartScreen,
    projectViewModel: ProjectViewModel = hiltViewModel(),
    settingsViewModel: SettingsViewModel = hiltViewModel()
) {
    val interpreterViewModel: InterpreterViewModel = hiltViewModel()
    val libraryViewModel: LibraryViewModel = hiltViewModel()
    //  val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable<StartScreen> {
                StartScreenApp(navController, projectViewModel)

        }
        composable<Interpreter> {
            Scaffold(
                topBar = {
                    InterpreterTopBar(
                        projectViewModel.actualProjectName.value,
                        interpreterViewModel,
                        navController
                    )
                }
            ) { innerPadding ->
                Column(Modifier.padding(innerPadding)) {
                    InterpreterApp(
                        interpreterViewModel,
                        projectViewModel,
                        navController
                    )
                }
            }
        }
        composable<Projects> {
                Scaffold(
                    topBar = {
                        TopBarWithMenu("Projekty", navController)
                    },
                    modifier = Modifier.padding(0.dp)
                ) { innerPadding ->
                    ProjectsApp(
                        projectViewModel,
                        Modifier.padding(innerPadding),
                        navController = navController
                    )
                }

        }
        composable<Settings> {
            Layout({ modifier ->
                SettingsApp(modifier = modifier, settingsViewModel = settingsViewModel)
            }, "Ustawienia", navController)
        }
        composable<Libraries> {
            Layout({ modifier ->
                LibraryScreen(
                    modifier = modifier,
                    libraryViewModel = libraryViewModel,
                    navController = navController
                )
            }, "Biblioteki", navController)
        }
        composable<LibraryForm> {
            Layout({ modifier ->
                LibraryFormScreen(
                    modifier = modifier,
                    libraryViewModel = libraryViewModel,
                    navController = navController
                )
            }, "Dodaj biblioteke", navController)
        }
        composable<LibraryProcedures> {
            Layout({ modifier ->
                LibraryProceduresScreen(
                    libraryViewModel = libraryViewModel,
                    navController = navController,
                    modifier = modifier
                )
            }, libraryViewModel.actualLibrary.value!!, navController)
        }
        composable<LibraryProcedureForm> {
            Layout(
                { modifier ->
                    LibraryAddProcedureForm(
                        libraryViewModel = libraryViewModel,
                        interpreterViewModel = interpreterViewModel,
                        navController = navController,
                        modifier = modifier
                    )
                },
                "Dodaj procedure do ${libraryViewModel.actualLibrary.value!!}",
                navController
            )
        }
        composable<TutorialScreen> {
            Layout({ modifier ->
                TutorialScreen(modifier = modifier, navController)
            }, "Poradniki", navController)
        }
        composable(
            route = "TutorialContentScreen/{tutorialName}",
            arguments = listOf(navArgument("tutorialName"){
                type = NavType.StringType}
            )
        ) { backStackEntry ->
            val tutorialName = backStackEntry.arguments?.getString("tutorialName")
            Layout(
                content = { modifier ->
                    TutorialContentScreen(tutorialName!!, modifier = modifier)
                },
                title = tutorialName!!,
                navController = navController
            )
        }
    }
}

@Composable
fun Layout(
    content: @Composable (Modifier) -> Unit,
    title: String,
    navController: NavHostController = rememberNavController()
) {
    Scaffold(
        topBar = {
            TopBarWithMenu(title, navController)
        }
    ) { innerPadding ->
        content(Modifier.padding(innerPadding))
    }
}

@Serializable
object StartScreen

@Serializable
object Interpreter

@Serializable
object Projects

@Serializable
object Settings

@Serializable
object Libraries

@Serializable
object LibraryForm

@Serializable
object LibraryProcedures

@Serializable
object LibraryProcedureForm

@Serializable
object TutorialScreen
