package com.example.logointerpreterbeta.activities.layout

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.logointerpreterbeta.MyLogoVisitor
import com.example.logointerpreterbeta.activities.InterpreterViewModel
import com.example.logointerpreterbeta.activities.Libraries
import com.example.logointerpreterbeta.activities.Projects
import com.example.logointerpreterbeta.activities.Settings
import com.example.logointerpreterbeta.activities.Tutorials
import com.example.logointerpreterbeta.components.image_exporting.checkPermissions
import com.example.logointerpreterbeta.components.image_exporting.saveBitmapAsJpg
import com.example.logointerpreterbeta.components.image_exporting.saveBitmapAsPdf
import com.example.logointerpreterbeta.ui.theme.jetBrainsMono
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
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
fun InterpreterTopBar(
    title: String,
    viewModel: InterpreterViewModel,
    navController: NavHostController = rememberNavController()
) {
    var dropdownExpanded by remember { mutableStateOf(false) }
    var exportMenuExpanded by remember { mutableStateOf(false) }
    var saevFileExpanded by remember { mutableStateOf(false) }
    var openFileExpanded by remember { mutableStateOf(false) }
    val fileName by remember { mutableStateOf(TextFieldValue("")) }

    val menuTextStyle = TextStyle(
        fontFamily = jetBrainsMono, // Ustawiona globalna czcionka
        fontSize = 14.sp,

        )
    val context = LocalContext.current

    // Launcher do wyboru pliku do zapisu
    val saveFileLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.CreateDocument("text/plain")
    ) { uri ->
        uri?.let {
            context.contentResolver.openOutputStream(uri)?.use { outputStream ->
                OutputStreamWriter(outputStream).use { writer ->
                    writer.write(viewModel.codeState.text)
                }
                Toast.makeText(context, "Zapisano plik: ${fileName.text} ", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    // Launcher do wyboru pliku do wczytania
    val openFileLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.OpenDocument()
    ) { uri ->
        uri?.let {
            val inputStream = context.contentResolver.openInputStream(uri)
            val reader = BufferedReader(InputStreamReader(inputStream))
            val stringBuilder = StringBuilder()
            reader.use {
                it.forEachLine { line ->
                    stringBuilder.appendLine(line)
                }
            }

            viewModel.onCodeChange(TextFieldValue(stringBuilder.toString()))  // Zaktualizuj stan kodu
            Toast.makeText(context, "Wczytano plik 👍", Toast.LENGTH_SHORT).show()
        }
    }


    val modifier = Modifier
        .background(Color(0xFF4CAF50))
        .fillMaxHeight()

    TopAppBar(
        modifier = Modifier,
        title = {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = menuTextStyle
                )
            }
        },
        navigationIcon = {
            IconButton(onClick = { dropdownExpanded = true }) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Menu"
                )
            }
            // Menu kontekstowe (Dropdown)
            DropdownMenu(
                expanded = dropdownExpanded,
                onDismissRequest = { dropdownExpanded = false },
                modifier = Modifier
                    .background(Color(0xFFC8E6C9))
                    .fillMaxWidth(0.45f)
            ) {
                DropdownMenuItem(
                    onClick = { navController.navigate(Projects) },
                    text = { Text("Otwórz projekt", style = menuTextStyle) }
                )
                DropdownMenuItem(
                    onClick = { navController.navigate(Tutorials) },
                    text = { Text("Poradniki", style = menuTextStyle) }
                )
                DropdownMenuItem(
                    onClick = { navController.navigate(Libraries) },
                    text = { Text("Biblioteki", style = menuTextStyle) }
                )
                DropdownMenuItem(
                    onClick = { navController.navigate(Settings) },
                    text = { Text("Ustawienia", style = menuTextStyle) }
                )
                DropdownMenuItem(
                    onClick = { exportMenuExpanded = !exportMenuExpanded },
                    text = { Text("Eksportuj rysunek", style = menuTextStyle) }
                )
                AnimatedVisibility(visible = exportMenuExpanded) {
                    Column(modifier = modifier) {
                        DropdownMenuItem(
                            onClick = {
                                checkPermissions(context)
                                val jpgFile = saveBitmapAsJpg(
                                    context,
                                    MyLogoVisitor.image,
                                    "LogoImage" + Date()
                                )
                                Toast.makeText(
                                    context,
                                    if (jpgFile) "Zapisano JPG 🤓" else "Nie udało się zapisać JPG 🤷‍♂️",
                                    Toast.LENGTH_SHORT
                                ).show()
                                dropdownExpanded = false
                            },
                            text = { Text("Eksportuj jako JPG", style = menuTextStyle) }
                        )
                        DropdownMenuItem(
                            onClick = {
                                checkPermissions(context)
                                val pdfFile =
                                    saveBitmapAsPdf(context, MyLogoVisitor.image, "MyBitmapImage")
                                Toast.makeText(
                                    context,
                                    if (pdfFile) "Zapisano PDF 👌" else "Nie udało się zapisać PDF 😯",
                                    Toast.LENGTH_SHORT
                                ).show()
                                dropdownExpanded = false
                            },
                            text = { Text("Eksportuj jako PDF", style = menuTextStyle) }
                        )
                    }
                }
                DropdownMenuItem(
                    onClick = { saevFileExpanded = !saevFileExpanded },
                    text = { Text("Zapisz program", style = menuTextStyle) }
                )
                AnimatedVisibility(visible = saevFileExpanded) {
                    Column(modifier = modifier) {
                        DropdownMenuItem(
                            onClick = {
                                saveFileLauncher.launch(fileName.text)
                            },
                            text = { Text("Zapisz do pliku", style = menuTextStyle) }
                        )

                        DropdownMenuItem(
                            onClick = {

                            },
                            text = { Text("Zapisz na dysku", style = menuTextStyle) }
                        )
                    }
                }
                DropdownMenuItem(
                    onClick = { openFileExpanded = !openFileExpanded },
                    text = { Text("Wczytaj program", style = menuTextStyle) }
                )
                AnimatedVisibility(visible = openFileExpanded) {
                    Column(modifier = modifier) {
                        DropdownMenuItem(
                            onClick = {
                                openFileLauncher.launch(
                                    arrayOf(
                                        "text/plain",
                                        "application/octet-stream"
                                    )
                                )
                            },
                            text = { Text("Wczytaj plik", style = menuTextStyle) }
                        )

                        DropdownMenuItem(
                            onClick = {

                            },
                            text = { Text("Wczytaj z dysku", style = menuTextStyle) }
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
    // InterpreterTopBar("Test")
}
