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
import androidx.compose.material.icons.filled.Menu
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
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
                    text = { MenuElement(stringResource(R.string.projects), icon = painterResource(id = R.drawable.baseline_folder_24)) }
                )
                DropdownMenuItem(
                    onClick = { navController.navigate(TutorialScreen) },
                    text = { MenuElement(stringResource(R.string.tutorials), icon = painterResource(id = R.drawable.round_school_24)) }
                )
                DropdownMenuItem(
                    onClick = { navController.navigate(Libraries) },
                    text = { MenuElement(stringResource(R.string.libraries), icon = painterResource(id = R.drawable.round_menu_book_24)) }
                )
                DropdownMenuItem(
                    onClick = { navController.navigate(Settings) },
                    text = { MenuElement(stringResource(R.string.settings), icon = painterResource(id = R.drawable.baseline_settings_24)) }
                )
                DropdownMenuItem(
                    onClick = { exportMenuExpanded = !exportMenuExpanded },
                    text = {
                        Row(
                            Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            MenuElement(stringResource(R.string.export_image), icon = painterResource(id = R.drawable.baseline_image_24))
                            if (exportMenuExpanded) {
                                Icon(
                                    painter = painterResource(id = R.drawable.baseline_expand_less_24),
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.surface,
                                    modifier = Modifier.padding(start = 10.dp)
                                )
                            } else {
                                Icon(
                                    painter = painterResource(id = R.drawable.baseline_expand_more_24),
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
                            text = { MenuElement(stringResource(R.string.as_jpg), icon = painterResource(id = R.drawable.baseline_image_24)) }
                        )

                        DropdownMenuItem(
                            onClick = {
                                topBarViewModel.onImageExportPdfRequested(context, canvasBitmap)
                                dropdownExpanded = false
                            },
                            text = {
                                MenuElement(
                                    stringResource(R.string.save_pdf),
                                    icon = painterResource(id = R.drawable.round_picture_as_pdf_24)
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
                                    icon = painterResource(id = R.drawable.baseline_share_24)
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
                            MenuElement(stringResource(R.string.save_program), icon = painterResource(id = R.drawable.baseline_save_as_24))
                            if (saveFileExpanded) {
                                Icon(
                                    painter = painterResource(id = R.drawable.baseline_expand_less_24),
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.surface,
                                    modifier = Modifier.padding(start = 10.dp)
                                )
                            } else {
                                Icon(
                                    painter = painterResource(id = R.drawable.baseline_expand_more_24),
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
                            text = { MenuElement(stringResource(R.string.save_to_file), icon = painterResource(id = R.drawable.baseline_file_open_24)) }
                        )

                        DropdownMenuItem(
                            onClick = {
                               topBarViewModel.shareCode(code, context)
                            },
                            text = {
                                MenuElement(
                                    stringResource(R.string.share),
                                    icon = painterResource(id = R.drawable.baseline_share_24)
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
                            MenuElement(stringResource(R.string.open_program), icon = painterResource(id = R.drawable.baseline_download_24))
                            if (openFileExpanded) {
                                Icon(
                                    painter = painterResource(id = R.drawable.baseline_expand_less_24),
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.surface,
                                    modifier = Modifier.padding(start = 10.dp)
                                )
                            } else {
                                Icon(
                                    painter = painterResource(id = R.drawable.baseline_expand_more_24),
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
                                    icon = painterResource(id = R.drawable.baseline_upload_file_24)
                                )
                            }
                        )

                        DropdownMenuItem(
                            onClick = {

                            },
                            text = {
                                MenuElement(
                                    stringResource(R.string.read_from_disc),
                                    icon = painterResource(id = R.drawable.add_to_drive_24)
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
                    painter = painterResource(id = R.drawable.baseline_settings_24),
                    contentDescription = "Settings"
                )
            }
        },
        modifier = Modifier.height(80.dp)
    )
}

@Composable
fun MenuElement(text: String, icon: Painter) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            painter = icon,
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
        MenuElement(text = "test", icon = painterResource(id = R.drawable.baseline_save_as_24))
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
