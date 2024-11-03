package com.example.logointerpreterbeta.activities

import android.content.Context
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
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.logointerpreterbeta.activities.layout.InterpreterTopBar
import com.example.logointerpreterbeta.activities.layout.TopBarWithMenu
import com.example.logointerpreterbeta.ui.theme.LogoInterpreterBetaTheme
import com.example.logointerpreterbeta.viewModels.InterpreterViewModel
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: InterpreterViewModel =
                viewModel(factory = InterpreterViewModelFactory(this))

            LogoInterpreterBetaTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = StartScreen
                ) {
                    composable<StartScreen> {
                        StartScreenApp(navController)
                    }
                    composable<Interpreter> {
                        Scaffold(
                            topBar = {
                                InterpreterTopBar("plik.txt", viewModel, navController)
                            }
                        ) { innerPadding ->
                            Column(Modifier.padding(innerPadding)) {
                                InterpreterApp(viewModel)
                            }
                        }
                    }
                    composable<Projects> {
                        Layout({ modifier ->
                            ProjectsApp(modifier = modifier)
                        }, "Projekty", navController)
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
                            LibraryApp(modifier = modifier)
                        }, "Biblioteki", navController)
                    }
                }
            }
        }
    }
}

// Własna fabryka ViewModel do przekazania kontekstu
class InterpreterViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(InterpreterViewModel::class.java)) {
            return InterpreterViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
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