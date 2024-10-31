package com.example.logointerpreterbeta.activities.layout

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.logointerpreterbeta.MyLogoVisitor
import com.example.logointerpreterbeta.activities.Libraries
import com.example.logointerpreterbeta.activities.Projects
import com.example.logointerpreterbeta.activities.Settings
import com.example.logointerpreterbeta.activities.Tutorials
import com.example.logointerpreterbeta.components.image_exporting.checkPermissions
import com.example.logointerpreterbeta.components.image_exporting.saveBitmapAsJpg
import com.example.logointerpreterbeta.components.image_exporting.saveBitmapAsPdf
import com.example.logointerpreterbeta.ui.theme.AppTypography
import java.util.Date

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
fun InterpreterTopBar(title: String, navController: NavHostController = rememberNavController()) {
    var dropdownExpanded by remember { mutableStateOf(false) }
    var exportMenuExpanded by remember { mutableStateOf(false) }
    var importMenuExpanded by remember { mutableStateOf(false) }

    val menuTextStyle = AppTypography.bodySmall // Ustawiona globalna czcionka




    val context = LocalContext.current
    TopAppBar(
        colors = TopAppBarColors(
            titleContentColor = MaterialTheme.colorScheme.onSurface,
            navigationIconContentColor = MaterialTheme.colorScheme.onSurface,
            actionIconContentColor = MaterialTheme.colorScheme.onSurface,
            containerColor = MaterialTheme.colorScheme.surface,
            scrolledContainerColor = MaterialTheme.colorScheme.secondary,
        ),
        title = {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = title,
                    maxLines = 1,
                    //modifier = Modifier.align(Alignment.Center),
                    overflow = TextOverflow.Ellipsis,
                    style = menuTextStyle
                )
            }
        },
        navigationIcon = {
            IconButton(onClick = { dropdownExpanded = true }) {

                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Menu",
                )
            }
            // Menu kontekstowe (Dropdown)
            DropdownMenu(
                expanded = dropdownExpanded,
                onDismissRequest = { dropdownExpanded = false },
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.primary)
                    .fillMaxWidth(0.45f)
            ) {
                DropdownMenuItem(
                    onClick = { navController.navigate(Projects) },
                    text = { Text("Otwórz projekt", style = menuTextStyle, color = MaterialTheme.colorScheme.onPrimary) }
                )
                DropdownMenuItem(
                    onClick = { navController.navigate(Tutorials)},
                    text = { Text("Poradniki", style = menuTextStyle, color = MaterialTheme.colorScheme.onPrimary) }
                )
                DropdownMenuItem(
                    onClick = { navController.navigate(Libraries)},
                    text = { Text("Biblioteki", style = menuTextStyle, color = MaterialTheme.colorScheme.onPrimary) }
                )
                DropdownMenuItem(
                    onClick = {navController.navigate(Settings)},
                    text = { Text("Ustawienia", style = menuTextStyle, color = MaterialTheme.colorScheme.onPrimary) }
                )
                DropdownMenuItem(
                    onClick = { exportMenuExpanded = !exportMenuExpanded },
                    text = { Text("Eksportuj rysunek", style = menuTextStyle, color = MaterialTheme.colorScheme.onPrimary) }
                )
                AnimatedVisibility(visible = exportMenuExpanded) {
                    Column(
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.secondary)
                            .fillMaxHeight()
                    ) {
                        DropdownMenuItem(
                            onClick = {
                                checkPermissions(context)
                                val jpgFile = saveBitmapAsJpg(context, MyLogoVisitor.image, "LogoImage" + Date())
                                Toast.makeText(context, if (jpgFile) "Zapisano JPG" else "Nie udało się zapisać JPG", Toast.LENGTH_SHORT).show()
                                dropdownExpanded = false
                            },
                            text = { Text("Eksportuj jako JPG", style = menuTextStyle, color = MaterialTheme.colorScheme.onSecondary) }
                        )

                        DropdownMenuItem(
                            onClick = {
                                checkPermissions(context)
                                val pdfFile = saveBitmapAsPdf(context, MyLogoVisitor.image, "MyBitmapImage")
                                Toast.makeText(context, if (pdfFile) "Zapisano PDF" else "Nie udało się zapisać PDF", Toast.LENGTH_SHORT).show()
                                dropdownExpanded = false
                            },
                            text = { Text("Eksportuj jako PDF", style = menuTextStyle, color = MaterialTheme.colorScheme.onSecondary) }
                        )
                    }
                }

                DropdownMenuItem(
                    onClick = { importMenuExpanded = !importMenuExpanded },
                    text = { Text("Wczytaj program", style = menuTextStyle, color = MaterialTheme.colorScheme.onPrimary) }
                )
                AnimatedVisibility(visible = importMenuExpanded) {
                    Column(
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.secondary)
                            .fillMaxHeight()
                    ) {
                        DropdownMenuItem(
                            onClick = {

                            },
                            text = { Text("Wczytaj z pliku", style = menuTextStyle, color = MaterialTheme.colorScheme.onSecondary) }
                        )

                        DropdownMenuItem(
                            onClick = {

                            },
                            text = { Text("Wczytaj z dysku", style = menuTextStyle, color = MaterialTheme.colorScheme.onSecondary) }
                        )
                    }
                }

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
