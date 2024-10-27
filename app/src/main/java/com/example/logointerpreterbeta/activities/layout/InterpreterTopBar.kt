package com.example.logointerpreterbeta.activities.layout

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.logointerpreterbeta.MyLogoVisitor
import com.example.logointerpreterbeta.activities.Libraries
import com.example.logointerpreterbeta.activities.Projects
import com.example.logointerpreterbeta.activities.Settings
import com.example.logointerpreterbeta.activities.StartScreen
import com.example.logointerpreterbeta.activities.Tutorials
import com.example.logointerpreterbeta.components.image_exporting.saveBitmapAsJpg
import com.example.logointerpreterbeta.components.image_exporting.saveBitmapAsPdf

class InterpreterTopBar : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TopBarWithMenu("Test")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InterpreterTopBar(Title: String,navController: NavHostController = rememberNavController()) {
    var expanded by remember { mutableStateOf(false) }
    val context = LocalContext.current
    TopAppBar(
        title = {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = Title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        },
        navigationIcon = {
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
                    onClick = { navController.navigate(Tutorials)},
                    text = { Text("Poradniki") }
                )
                DropdownMenuItem(
                    onClick = { navController.navigate(Libraries)},
                    text = { Text("Biblioteki") }
                )
                DropdownMenuItem(
                    onClick = {navController.navigate(Settings)},
                    text = { Text("Ustawienia") }
                )
                DropdownMenuItem(
                    onClick = {
                        val jpgFile = saveBitmapAsJpg(context, MyLogoVisitor.image, "MyBitmapImage")
                        if (jpgFile != null) {
                            // Udało się zapisać plik JPG
                            Toast.makeText(context, "Zapisano JPG: ${jpgFile.path}", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(context, "Nie udało się zapisać JPG", Toast.LENGTH_SHORT).show()
                        }
                    },
                    text = { Text("Wyeksportuj rysunek jako obraz") }
                )
                DropdownMenuItem(
                    onClick = {
                        val pdfFile = saveBitmapAsPdf(context, MyLogoVisitor.image, "MyBitmapImage")
                        if (pdfFile != null) {
                            // Udało się zapisać plik PDF
                            Toast.makeText(context, "Zapisano PDF: ${pdfFile.path}", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(context, "Nie udało się zapisać PDF", Toast.LENGTH_SHORT).show()
                        }

                    },
                    text = { Text("Wyeksportuj rysunek jako plik pdf") }
                )
            }
        },
        actions = {
            // Ikona menu kontekstowego
            IconButton(onClick = { navController.navigate(Settings) }) { //To tak tymczasowo bo teraz mi się nie chce
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "Settings"
                )
            }

        }
    )
}

@Preview(showBackground = true)
@Composable
fun InterpreterTopBarPreview() {
    InterpreterTopBar("Test")
}
