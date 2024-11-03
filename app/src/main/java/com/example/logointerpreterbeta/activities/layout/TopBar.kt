package com.example.logointerpreterbeta.activities.layout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.logointerpreterbeta.activities.Libraries
import com.example.logointerpreterbeta.activities.Projects
import com.example.logointerpreterbeta.activities.Settings
import com.example.logointerpreterbeta.activities.StartScreen
import com.example.logointerpreterbeta.activities.Tutorials

class TopBar : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TopBarWithMenu("Test")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarWithMenu(title: String, navController: NavHostController = rememberNavController()) {
    var expanded by remember { mutableStateOf(false) }

    TopAppBar(
        title = {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        },
        navigationIcon = {
            // Ikona menu kontekstowego
            IconButton(onClick = { navController.navigate(StartScreen) }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Settings"
                )
            }
        },
        actions = {


            IconButton(onClick = { expanded = true }) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Menu"
                )
            }
            // Menu kontekstowe (Dropdown)
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                DropdownMenuItem(
                    onClick = { navController.navigate(Projects) },
                    text = { Text("Otwórz projekt") }
                )
                DropdownMenuItem(
                    onClick = { navController.navigate(Tutorials) },
                    text = { Text("Poradniki") }
                )
                DropdownMenuItem(
                    onClick = { navController.navigate(Libraries) },
                    text = { Text("Biblioteki") }
                )
                DropdownMenuItem(
                    onClick = { navController.navigate(Settings) },
                    text = { Text("Ustawienia") }
                )
            }


        }
    )
}

@Preview(showBackground = true)
@Composable
fun TopBarWithMenuPreview() {
    TopBarWithMenu("Test")
}
