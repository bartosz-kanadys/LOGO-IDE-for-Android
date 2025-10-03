package com.example.logointerpreterbeta.ui.navigation.topBars

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.AddToDrive
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.FileOpen
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.PictureAsPdf
import androidx.compose.material.icons.filled.SaveAs
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.FileProvider
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.logointerpreterbeta.R
import com.example.logointerpreterbeta.framework.ImageExportManager
import com.example.logointerpreterbeta.ui.navigation.Libraries
import com.example.logointerpreterbeta.ui.navigation.Projects
import com.example.logointerpreterbeta.ui.navigation.Settings
import com.example.logointerpreterbeta.ui.navigation.TutorialScreen
import com.example.logointerpreterbeta.ui.theme.AppTypography
import com.example.logointerpreterbeta.ui.theme.LogoInterpreterBetaTheme
import com.example.logointerpreterbeta.ui.screens.interpreter.InterpreterViewModel
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.Date
import androidx.core.graphics.createBitmap
import java.io.File
import java.io.FileOutputStream

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
    var saveFileExpanded by remember { mutableStateOf(false) }
    var openFileExpanded by remember { mutableStateOf(false) }
    val fileName by remember { mutableStateOf(TextFieldValue("")) }

    val menuTextStyle = AppTypography.bodySmall // Ustawiona globalna czcionka

    val context = LocalContext.current
    val exportManager = ImageExportManager()

    // Launcher do wyboru pliku do zapisu
    val saveFileLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.CreateDocument("text/plain")
    ) { uri ->
        uri?.let {
            context.contentResolver.openOutputStream(uri)?.use { outputStream ->
                OutputStreamWriter(outputStream).use { writer ->
                    writer.write(viewModel.getCodeStateAsString())
                }
                Toast.makeText(context,
                    context.getString(R.string.file_saved_info, fileName.text), Toast.LENGTH_SHORT)
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

//            viewModel.onCodeChange(TextFieldValue(stringBuilder.toString()), null)  // Zaktualizuj stan kodu
            Toast.makeText(context,
                context.getString(R.string.file_readed_toast), Toast.LENGTH_SHORT).show()
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
                modifier = Modifier
                    .fillMaxSize()
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
                    contentDescription = stringResource(R.string.menu)
                )
            }
            // Menu kontekstowe (Dropdown)
            DropdownMenu(
                expanded = dropdownExpanded,
                onDismissRequest = { dropdownExpanded = false },
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.primaryContainer)
                //.fillMaxWidth(0.60f)
            ) {
                DropdownMenuItem(
                    onClick = { navController.navigate(Projects) },
                    text = { MenuElement(stringResource(R.string.projects), icon = Icons.Filled.Folder) }
                )
                DropdownMenuItem(
                    onClick = { navController.navigate(TutorialScreen) },
                    text = { MenuElement(stringResource(R.string.tutorials), icon = Icons.Filled.School) }
                )
                DropdownMenuItem(
                    onClick = { navController.navigate(Libraries) },
                    text = { MenuElement(stringResource(R.string.libraries), icon = Icons.AutoMirrored.Filled.MenuBook) }
                )
                DropdownMenuItem(
                    onClick = { navController.navigate(Settings) },
                    text = { MenuElement(stringResource(R.string.settings), icon = Icons.Filled.Settings) }
                )
                DropdownMenuItem(
                    onClick = { exportMenuExpanded = !exportMenuExpanded },
                    text = {
                        Row(
                            Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            MenuElement(stringResource(R.string.export_image), icon = Icons.Filled.Image)
                            if (exportMenuExpanded) {
                                Icon(
                                    imageVector = Icons.Filled.ExpandLess,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.surface,
                                    modifier = Modifier.padding(start = 10.dp)
                                )
                            } else {
                                Icon(
                                    imageVector = Icons.Filled.ExpandMore,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.surface,
                                    modifier = Modifier.padding(start = 10.dp)
                                )
                            }
                        }
                    }
                )
                AnimatedVisibility(visible = exportMenuExpanded) {
                    Column(
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.secondary)
                            .fillMaxHeight()
                    ) {
                        DropdownMenuItem(
                            onClick = {
                                exportManager.checkPermissions(context)
                                val jpgFile = exportManager.saveBitmapAsJpg(
                                    context,
                                    viewModel.img.value,
                                    "LogoImage" + Date()
                                )
                                Toast.makeText(
                                    context,
                                    if (jpgFile) context.getString(R.string.saved_jpg) else context.getString(
                                        R.string.not_able_to_save_jpg
                                    ),
                                    Toast.LENGTH_SHORT
                                ).show()
                                dropdownExpanded = false
                            },
                            text = { MenuElement(stringResource(R.string.as_jpg), icon = Icons.Filled.Image) }
                        )

                        DropdownMenuItem(
                            onClick = {
                                exportManager.checkPermissions(context)
                                val pdfFile =
                                    exportManager.saveBitmapAsPdf(context, viewModel.img.value, "MyBitmapImage")
                                Toast.makeText(
                                    context,
                                    if (pdfFile) context.getString(R.string.saved_pdf) else context.getString(
                                        R.string.not_able_to_save_pdf
                                    ),
                                    Toast.LENGTH_SHORT
                                ).show()
                                dropdownExpanded = false
                            },
                            text = {
                                MenuElement(
                                    stringResource(R.string.save_pdf),
                                    icon = Icons.Filled.PictureAsPdf
                                )
                            }
                        )
                        DropdownMenuItem(
                            onClick = {
                                try {
                                    // Krok 1: Zapisanie bitmapy do pliku
                                    val file = File(context.cacheDir, "shared_image.png")
                                    FileOutputStream(file).use { fos ->
                                        viewModel.img.value.compress(
                                            Bitmap.CompressFormat.PNG,
                                            100,
                                            fos
                                        )
                                    }

                                    // Krok 2: Uzyskanie URI pliku za pomocą FileProvider
                                    val uri = FileProvider.getUriForFile(
                                        context,
                                        "${context.packageName}.fileprovider",
                                        file
                                    )

                                    // Krok 3: Tworzenie Intentu do udostępnienia
                                    val shareIntent = Intent().apply {
                                        action = Intent.ACTION_SEND
                                        type = "image/png"
                                        putExtra(Intent.EXTRA_STREAM, uri)
                                        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION) // Umożliwia odczyt pliku innym aplikacjom
                                    }

                                    // Uruchamianie Intentu
                                    context.startActivity(
                                        Intent.createChooser(
                                            shareIntent,
                                            "Share image via"
                                        )
                                    )
                                } catch (e: Exception) {
                                    e.printStackTrace()
                                    Toast.makeText(
                                        context,
                                        context.getString(R.string.failed_to_share_image, e.message),
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            },
                            text = {
                                MenuElement(
                                    stringResource(R.string.share),
                                    icon = Icons.Filled.Share
                                )
                            }
                        )
                    }
                }

                DropdownMenuItem(
                    onClick = { saveFileExpanded = !saveFileExpanded },
                    text = {
                        Row(
                            Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            MenuElement(stringResource(R.string.save_program), icon = Icons.Filled.SaveAs)
                            if (saveFileExpanded) {
                                Icon(
                                    imageVector = Icons.Filled.ExpandLess,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.surface,
                                    modifier = Modifier.padding(start = 10.dp)
                                )
                            } else {
                                Icon(
                                    imageVector = Icons.Filled.ExpandMore,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.surface,
                                    modifier = Modifier.padding(start = 10.dp)
                                )
                            }
                        }
                    }
                )
                AnimatedVisibility(visible = saveFileExpanded) {
                    Column(
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.secondary)
                            .fillMaxHeight()
                    ) {
                        DropdownMenuItem(
                            onClick = {
                                saveFileLauncher.launch(fileName.text)
                            },
                            text = { MenuElement(stringResource(R.string.save_to_file), icon = Icons.Filled.FileOpen) }
                        )

                        DropdownMenuItem(
                            onClick = {
                                val shareIntent = Intent().apply {
                                    action = Intent.ACTION_SEND
                                    type = "text/plain" // Typ MIME dla tekstu
                                    putExtra(
                                        Intent.EXTRA_TEXT,
                                        viewModel.getCodeStateAsString()
                                    ) // Tekst do udostępnienia
                                }

                                // Utwórz chooser i uruchom Intent
                                context.startActivity(
                                    Intent.createChooser(
                                        shareIntent,
                                        "Share via"
                                    )
                                )

                            },
                            text = {
                                MenuElement(
                                    stringResource(R.string.share),
                                    icon = Icons.Filled.Share
                                )
                            }
                        )
                    }
                }
                DropdownMenuItem(
                    onClick = { openFileExpanded = !openFileExpanded },
                    text = {
                        Row(
                            Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            MenuElement(stringResource(R.string.open_program), icon = Icons.Filled.Download)
                            if (openFileExpanded) {
                                Icon(
                                    imageVector = Icons.Filled.ExpandLess,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.surface,
                                    modifier = Modifier.padding(start = 10.dp)
                                )
                            } else {
                                Icon(
                                    imageVector = Icons.Filled.ExpandMore,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.surface,
                                    modifier = Modifier.padding(start = 10.dp)
                                )
                            }
                        }
                    }
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
                            text = {
                                MenuElement(
                                    stringResource(R.string.read_from_file),
                                    icon = Icons.Filled.UploadFile
                                )
                            }
                        )

                        DropdownMenuItem(
                            onClick = {

                            },
                            text = {
                                MenuElement(
                                    stringResource(R.string.read_from_disc),
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
        },
        modifier = Modifier.height(80.dp)
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
        InterpreterTopBar(
            "Test",
            viewModel = hiltViewModel(),
            navController = rememberNavController()
        )
    }
}
