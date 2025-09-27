package com.example.logointerpreterbeta.ui.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.logointerpreterbeta.R
import com.example.logointerpreterbeta.domain.models.Config
import com.example.logointerpreterbeta.domain.repository.ConfigRepository
import com.example.logointerpreterbeta.ui.navigation.topBars.InterpreterTopBar
import com.example.logointerpreterbeta.ui.navigation.topBars.TopBarWithMenu
import com.example.logointerpreterbeta.ui.screens.interpreter.InterpreterApp
import com.example.logointerpreterbeta.ui.screens.interpreter.InterpreterViewModel
import com.example.logointerpreterbeta.ui.screens.library.LibraryFormScreen
import com.example.logointerpreterbeta.ui.screens.library.LibraryProceduresScreenRoot
import com.example.logointerpreterbeta.ui.screens.library.LibraryScreenRoot
import com.example.logointerpreterbeta.ui.screens.library.LibraryViewModel
import com.example.logointerpreterbeta.ui.screens.library.LibraryAddProcedureForm
import com.example.logointerpreterbeta.ui.screens.projects.ProjectScreenRoot
import com.example.logointerpreterbeta.ui.screens.projects.ProjectViewModel
import com.example.logointerpreterbeta.ui.screens.settings.SettingsScreenRoot
import com.example.logointerpreterbeta.ui.screens.settings.SettingsViewModel
import com.example.logointerpreterbeta.ui.screens.start.StartScreenRoot
import com.example.logointerpreterbeta.ui.screens.start.StartScreenViewModel
import com.example.logointerpreterbeta.ui.screens.tutorial.TutorialContentScreen
import com.example.logointerpreterbeta.ui.screens.tutorial.TutorialScreen
import com.example.logointerpreterbeta.ui.theme.LogoInterpreterBetaTheme
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.serialization.Serializable

@OptIn(DelicateCoroutinesApi::class)
@SuppressLint("NewApi", "StateFlowValueCalledInComposition", "CoroutineCreationDuringComposition")
@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: Any = StartScreen,
    projectViewModel: ProjectViewModel = hiltViewModel(),
    configRepository: ConfigRepository
) {
    val interpreterViewModel: InterpreterViewModel = hiltViewModel()
    val libraryViewModel: LibraryViewModel = hiltViewModel()
    val settingsViewModel: SettingsViewModel = hiltViewModel()
    val startScreenViewModel: StartScreenViewModel = hiltViewModel()

    val config by configRepository.readSettings().collectAsState(
        initial = Config()
    )

    LogoInterpreterBetaTheme(
                darkTheme = when(config.currentTheme){
                    "System" -> isSystemInDarkTheme()
                    "Light" -> false
                    "Dark" -> true
                    else -> isSystemInDarkTheme()
                }
    ) {
        NavHost(
            navController = navController,
            startDestination = startDestination
        ) {
            composable<StartScreen> {
                StartScreenRoot(navController, viewModel = startScreenViewModel)
            }
            composable<Interpreter> {
                Scaffold(
                    topBar = {
                        InterpreterTopBar(
                            config.lastModifiedProject,
                            interpreterViewModel,
                            navController
                        )
                    }
                ) { innerPadding ->
                    Column(Modifier.padding(innerPadding)) {
                        InterpreterApp(
                            interpreterViewModel,
                            projectViewModel,
                            navController,
                            configRepository
                        )
                    }
                }
            }
            composable<Projects> {
                Scaffold(
                    topBar = {
                        TopBarWithMenu(stringResource(R.string.projects), navController)
                    },
                    modifier = Modifier.padding(0.dp)
                ) { innerPadding ->
                    ProjectScreenRoot(
                        projectViewModel,
                        Modifier.padding(innerPadding),
                        navController = navController
                    )
                }

            }
            composable<Settings> {
                Layout({ modifier ->
                    SettingsScreenRoot(
                        modifier = modifier,
                        viewModel = settingsViewModel
                    )
                }, stringResource(R.string.settings), navController)
            }
            composable<Libraries> {
                Layout({ modifier ->
                    LibraryScreenRoot(
                        navController = navController,
                        libraryViewModel = libraryViewModel,
                        modifier = modifier
                    )
                }, stringResource(R.string.libraries), navController)
            }
            composable<LibraryForm> {
                Layout({ modifier ->
                    LibraryFormScreen(
                        modifier = modifier,
                        libraryViewModel = libraryViewModel,
                        onBack = { navController.popBackStack() }
                    )
                }, stringResource(R.string.add_library), navController)
            }
            composable<LibraryProcedures> {
                Layout({ modifier ->
                    LibraryProceduresScreenRoot(
                        libraryViewModel = libraryViewModel,
                        onNavigate = { navController.navigate(LibraryProcedureForm)},
                        modifier = modifier
                    )
                }, libraryViewModel.uiState.value.actualLibrary!!, navController)
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
                    stringResource(
                        R.string.add_procedure_to_,
                        libraryViewModel.uiState.value.actualLibrary!!
                    ),
                    navController
                )
            }
            composable<TutorialScreen> {
                Layout({ modifier ->
                    TutorialScreen(modifier = modifier, navController)
                }, stringResource(R.string.tutorials), navController)
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
                        TutorialContentScreen(tutorialName, modifier = modifier)
                    },
                    title = tutorialName!!,
                    navController = navController
                )
            }
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
