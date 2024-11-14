package com.example.logointerpreterbeta.Navigation

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.logointerpreterbeta.Navigation.topBars.InterpreterTopBar
import com.example.logointerpreterbeta.Navigation.topBars.TopBarWithMenu
import com.example.logointerpreterbeta.ui.Screens.InterpreterApp
import com.example.logointerpreterbeta.ui.Screens.ProjectsApp
import com.example.logointerpreterbeta.ui.Screens.SettingsApp
import com.example.logointerpreterbeta.ui.Screens.StartScreenApp
import com.example.logointerpreterbeta.ui.Screens.TutorialsApp
import com.example.logointerpreterbeta.ui.Screens.libraryScreens.LibraryAddProcedureForm
import com.example.logointerpreterbeta.ui.Screens.libraryScreens.LibraryFormScreen
import com.example.logointerpreterbeta.ui.Screens.libraryScreens.LibraryProceduresScreen
import com.example.logointerpreterbeta.ui.Screens.libraryScreens.LibraryScreen
import com.example.logointerpreterbeta.ui.theme.LogoInterpreterBetaTheme
import com.example.logointerpreterbeta.viewModels.InterpreterViewModel
import com.example.logointerpreterbeta.viewModels.LibraryViewModel
import com.example.logointerpreterbeta.viewModels.ProjectViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.Serializable

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("StateFlowValueCalledInComposition")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val interpreterViewModel: InterpreterViewModel = hiltViewModel()
            val libraryViewModel: LibraryViewModel = hiltViewModel()
            val projectViewModel: ProjectViewModel = hiltViewModel()

            LogoInterpreterBetaTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = StartScreen
                ) {
                    composable<StartScreen> {
                        LogoInterpreterBetaTheme {
                            StartScreenApp(navController, projectViewModel)
                        }
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
                        LogoInterpreterBetaTheme {
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
                    }
                    composable<Settings> {
                        Layout({ modifier ->
                            SettingsApp(modifier = modifier)
                        }, "Ustawienia", navController)
                    }
                    composable<Tutorials> {
                        Layout({ modifier ->
                            TutorialsApp(modifier = modifier)
                        }, "Poradniki", navController)
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
                }
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
object Tutorials

@Serializable
object Libraries

@Serializable
object LibraryForm

@Serializable
object LibraryProcedures

@Serializable
object LibraryProcedureForm