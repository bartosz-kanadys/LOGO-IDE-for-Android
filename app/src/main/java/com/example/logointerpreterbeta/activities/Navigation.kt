package com.example.logointerpreterbeta.activities

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.logointerpreterbeta.activities.layout.InterpreterTopBar
import com.example.logointerpreterbeta.activities.layout.TopBarWithMenu
import com.example.logointerpreterbeta.ui.theme.LogoInterpreterBetaTheme
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
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
                                InterpreterTopBar("plik.txt",navController)
                            }
                        ) { innerPadding ->
                            InterpreterApp(Modifier.padding(innerPadding))
                        }

                    }
                    composable<Projects> {
                        Layout({
                            modifier -> ProjectsApp(modifier = modifier)
                       } ,"Projekty",navController)
                    }
                    composable<Settings> {
                        Layout({
                                modifier -> SettingsApp(modifier = modifier)
                        } ,"Ustawienia",navController)
                    }
                    composable<Tutorials> {
                        Layout({
                                modifier -> TutorialsApp(modifier = modifier)
                        } ,"Poradniki",navController)
                    }
                    composable<Libraries> {
                        Layout({
                                modifier -> LibraryApp(modifier = modifier)
                        } ,"Biblioteki",navController)
                    }
                }
            }
        }
    }
}
@Composable
fun Layout(content: @Composable (Modifier) -> Unit, title: String, navController: NavHostController = rememberNavController()) {
    Scaffold(
        topBar = {
            TopBarWithMenu(title,navController)
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
//@Serializable
//data class ScreenB(
//    val name: String?,
//    val age: Int
//)
