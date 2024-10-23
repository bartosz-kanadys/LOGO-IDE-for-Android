package com.example.logointerpreterbeta.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.logointerpreterbeta.activities.layout.TopBarWithMenu
import com.example.logointerpreterbeta.ui.theme.LogoInterpreterBetaTheme
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
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
                        StartScreenApp(Modifier,navController);
                    }
                    composable<Interpreter> {
                        InterpreterApp()
                    }
                    composable<Projects> {
                        Layout({
                            modifier -> ProjectsApp(modifier = modifier)
                       } ,"Projekty")
                    }
                    composable<Settings> {
                        Layout({
                                modifier -> ProjectsApp(modifier = modifier)
                        } ,"Ustawienia")
                    }
                    composable<Tutorials> {
                        Layout({
                                modifier -> ProjectsApp(modifier = modifier)
                        } ,"Poradniki")
                    }
                    composable<Libraries> {
                        Layout({
                                modifier -> ProjectsApp(modifier = modifier)
                        } ,"Biblioteki")
                    }
                }
            }
        }
    }
}
@Composable
fun Layout(content: @Composable (Modifier) -> Unit, title: String) {
    Scaffold(
        topBar = {
            TopBarWithMenu(title)
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
//Button(onClick = {
//    navController.navigate(ScreenB(
//        name = null,
//        age = 25
//    ))
//})