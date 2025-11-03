package com.example.logointerpreterbeta.ui.navigation.topBars

import android.graphics.Bitmap
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.logointerpreterbeta.R
import com.example.logointerpreterbeta.ui.navigation.Libraries
import com.example.logointerpreterbeta.ui.navigation.Projects
import com.example.logointerpreterbeta.ui.navigation.Settings
import com.example.logointerpreterbeta.ui.navigation.TutorialScreen
import com.example.logointerpreterbeta.ui.screens.interpreter.InterpreterEvent
import com.example.logointerpreterbeta.ui.screens.interpreter.InterpreterViewModel
import com.example.logointerpreterbeta.ui.theme.AppTypography
import com.example.logointerpreterbeta.ui.theme.LogoInterpreterBetaTheme

//class InterpreterTopBar : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            TopBarWithMenu("Test")
//        }
//    }
//}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InterpreterTopBar(
    title: String,
    code: String,
    canvasBitmap: Bitmap,
    onEvent: (InterpreterEvent) -> Unit,
    topBarViewModel: TopBarViewModel,
    navController: NavHostController
) {
    var dropdownExpanded by remember { mutableStateOf(false) }
    var exportMenuExpanded by remember { mutableStateOf(false) }
    var saveFileExpanded by remember { mutableStateOf(false) }
    var openFileExpanded by remember { mutableStateOf(false) }
    val fileName by remember { mutableStateOf(TextFieldValue("")) }

    val context = LocalContext.current

    val uiState by topBarViewModel.uiState.collectAsStateWithLifecycle()

    uiState.showToast?.let {
        Toast.makeText(context, context.getString(it), Toast.LENGTH_SHORT).show()
        topBarViewModel.clearOneTimeEvents()
    }

    // Launcher to save file
    val saveFileLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.CreateDocument("text/plain")
    ) { uri ->
        uri?.let {
            topBarViewModel.onWriteFileRequested(context, uri, code, fileName.text)
        }
    }

    // Launcher to write file
    val openFileLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.OpenDocument()
    ) { uri ->
        uri?.let {
            val loadedCode = topBarViewModel.onLoadFileRequested(context, uri)
            onEvent(InterpreterEvent.OnCodeChange(loadedCode, 0))            //viewModel.colorCode()
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
                    style = AppTypography.bodySmall
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
            // Menu (Dropdown)
            DropdownMenu(
                expanded = dropdownExpanded,
                onDismissRequest = { dropdownExpanded = false },
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.primaryContainer)
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
                                topBarViewModel.onImageExportJpgRequested(context, canvasBitmap)
                                dropdownExpanded = false
                            },
                            text = { MenuElement(stringResource(R.string.as_jpg), icon = Icons.Filled.Image) }
                        )

                        DropdownMenuItem(
                            onClick = {
                                topBarViewModel.onImageExportPdfRequested(context, canvasBitmap)
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
                                topBarViewModel.shareImage(context, canvasBitmap)
                                dropdownExpanded = false
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
                               topBarViewModel.shareCode(code, context)
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
            IconButton(onClick = { navController.navigate(Settings) }) {
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

//@Preview(showBackground = true)
//@Composable
//fun InterpreterTopBarPreview2() {
//    LogoInterpreterBetaTheme(darkTheme = true) {
//        InterpreterTopBar(
//            "Test",
//            viewModel = hiltViewModel(),
//            navController = rememberNavController()
//        )
//    }
//}
