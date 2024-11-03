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
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.AddToDrive
import androidx.compose.material.icons.filled.CreateNewFolder
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.FileOpen
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.PictureAsPdf
import androidx.compose.material.icons.filled.SaveAs
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.UploadFile
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
import com.example.logointerpreterbeta.ui.theme.LogoInterpreterBetaTheme
import com.example.logointerpreterbeta.viewModels.InterpreterViewModel
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

    val menuTextStyle = AppTypography.bodySmall // Ustawiona globalna czcionka

    val context = LocalContext.current

    // Launcher do wyboru pliku do zapisu
    val saveFileLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.CreateDocument("text/plain")
    ) { uri ->
        uri?.let {
            context.contentResolver.openOutputStream(uri)?.use { outputStream ->
                OutputStreamWriter(outputStream).use { writer ->
                    writer.write(viewModel.codeState)
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

            viewModel.codeState = stringBuilder.toString() // Zaktualizuj stan kodu
            Toast.makeText(context, "Wczytano plik 👍", Toast.LENGTH_SHORT).show()
        }
    }

    TopAppBar(
        colors = TopAppBarColors(
            titleContentColor = MaterialTheme.colorScheme.inverseSurface,
            navigationIconContentColor = MaterialTheme.colorScheme.inverseSurface,
            actionIconContentColor = MaterialTheme.colorScheme.inverseSurface,
            containerColor = MaterialTheme.colorScheme.inversePrimary,
            scrolledContainerColor = MaterialTheme.colorScheme.inversePrimary,
        ),
        title = {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    fontSize = 20.sp,
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
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .fillMaxWidth(0.45f)
            ) {
                DropdownMenuItem(
                    onClick = { navController.navigate(Projects) },
                    text = { MenuElement("Otwórz projekt", icon = Icons.Filled.CreateNewFolder) }
                )
                DropdownMenuItem(
                    onClick = { navController.navigate(Tutorials) },
                    text = { MenuElement("Poradniki", icon = Icons.Filled.School) }
                )
                DropdownMenuItem(
                    onClick = { navController.navigate(Libraries) },
                    text = { MenuElement("Biblioteki", icon = Icons.AutoMirrored.Filled.MenuBook) }
                )
                DropdownMenuItem(
                    onClick = { navController.navigate(Settings) },
                    text = { MenuElement("Ustawienia", icon = Icons.Filled.Settings) }
                )
                DropdownMenuItem(
                    onClick = { exportMenuExpanded = !exportMenuExpanded },
                    text = { MenuElement("Exportuj rysunek", icon = Icons.Filled.Image) }
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
                            text = { MenuElement("Jako JPG", icon = Icons.Filled.Image) }
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
                            text = {
                                MenuElement(
                                    "Zapisz PDF",
                                    icon = Icons.Filled.PictureAsPdf
                                )
                            }
                        )
                    }
                }

                DropdownMenuItem(
                    onClick = { saevFileExpanded = !saevFileExpanded },
                    text = { MenuElement("Zapisz program", icon = Icons.Filled.SaveAs) }
                )
                AnimatedVisibility(visible = saevFileExpanded) {
                    Column(
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.secondary)
                            .fillMaxHeight()
                    ) {
                        DropdownMenuItem(
                            onClick = {
                                saveFileLauncher.launch(fileName.text)
                            },
                            text = { MenuElement("Zapisz do pliku", icon = Icons.Filled.FileOpen) }
                        )

                        DropdownMenuItem(
                            onClick = {

                            },
                            text = {
                                MenuElement(
                                    "Zapisz na dysku",
                                    icon = Icons.Filled.AddToDrive
                                )
                            }
                        )
                    }
                }
                DropdownMenuItem(
                    onClick = { openFileExpanded = !openFileExpanded },
                    text = { MenuElement("Wczytaj program", icon = Icons.Filled.Download) }
                )
                AnimatedVisibility(visible = openFileExpanded) {
                    Column(
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.secondary)
                            .fillMaxHeight()
                    ) {
                        DropdownMenuItem(
                            onClick = {
                                openFileLauncher.launch(
                                    arrayOf(
                                        "text/plain",
                                        "application/octet-stream"
                                    )
                                )
                            },
                            text = { MenuElement("Zapisz program", icon = Icons.Filled.UploadFile) }
                        )

                        DropdownMenuItem(
                            onClick = {

                            },
                            text = {
                                MenuElement(
                                    "Wczytaj z dysku",
                                    icon = Icons.Filled.AddToDrive
                                )
                            }
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

@Composable
fun MenuElement(text: String, icon: ImageVector) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSecondaryContainer,
            modifier = Modifier.padding(end = 5.dp)
        )
        Text(
            text, style = AppTypography.bodySmall,
            color = MaterialTheme.colorScheme.onSecondaryContainer
        )
    }
}

@Preview(showBackground = true)
@Composable
fun InterpreterTopBarPreview() {
    LogoInterpreterBetaTheme {
        MenuElement(text = "test", icon = Icons.Filled.SaveAs)
    }

}

@Preview(showBackground = true)
@Composable
fun InterpreterTopBarPreview2() {
    LogoInterpreterBetaTheme(darkTheme = true) {
        //  InterpreterTopBar("Test")
    }
}
