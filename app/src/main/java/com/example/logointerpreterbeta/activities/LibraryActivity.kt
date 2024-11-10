package com.example.logointerpreterbeta.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

class LibraryActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
//            LogoInterpreterBetaTheme {
//                Scaffold(
//                    topBar = {
//                        TopBarWithMenu("Biblioteki")
//                    }
//                ) { innerPadding ->
//                    LibraryScreen(modifier = Modifier.padding(innerPadding))
//                }
//            }
        }
    }
}



@Preview(showBackground = true, showSystemUi = false)
@Composable
fun LibraryPreview() {
//    LogoInterpreterBetaTheme {
//        Scaffold(
//            topBar = {
//                TopBarWithMenu("Biblioteki")
//            }
//        ) { innerPadding ->
//            LibraryScreen(Modifier.padding(innerPadding))
//        }
//
//    }
}