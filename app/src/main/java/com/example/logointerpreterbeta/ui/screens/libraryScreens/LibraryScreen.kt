package com.example.logointerpreterbeta.ui.screens.libraryScreens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Bookmarks
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.logointerpreterbeta.navigation.LibraryForm
import com.example.logointerpreterbeta.navigation.LibraryProcedures
import com.example.logointerpreterbeta.ui.theme.AppTypography
import com.example.logointerpreterbeta.ui.theme.LogoInterpreterBetaTheme
import com.example.logointerpreterbeta.viewModels.LibraryViewModel

@Composable
fun LibraryScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    libraryViewModel: LibraryViewModel
) {
    val libraries by libraryViewModel.libraries.collectAsStateWithLifecycle()
    var libraryToDelete by rememberSaveable { mutableStateOf<String?>(null) }

    AnimatedVisibility(libraryToDelete != null) {
        AlertDialog(
            onDismissRequest = { libraryToDelete = null },
            title = { Text("Potwierdzenie usunięcia") },
            text = { Text("Czy na pewno chcesz usunąć biblioteke '${libraryToDelete}'?") },
            confirmButton = {
                TextButton(
                    onClick = {
                        libraryViewModel.deleteLibrary(libraryToDelete!!)
                        libraryToDelete = null
                    }
                ) {
                    Text("Usuń")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { libraryToDelete = null } // Anuluj usunięcie
                ) {
                    Text("Anuluj")
                }
            }
        )
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(2), // 2 elementy na wiersz
        contentPadding = PaddingValues(10.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
    ) {
        item {
            AddNewLibraryButton { navController.navigate(LibraryForm) }
        }
        items(libraries) { library ->
            LibraryCard(
                libraryName = library.name,
                libraryDescription = library.description,
                procedureCount = library.procedures.size,
                author = library.author,
                onClick = {
                    libraryViewModel.updateActualLibrary(library.name)
                    navController.navigate(LibraryProcedures)
                },
                onDeleteClick = { libraryToDelete = library.name }
            )
        }
    }
}

@Composable
fun LibraryCard(
    libraryName: String,
    libraryDescription: String,
    procedureCount: Int,
    author: String,
    onClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.inversePrimary,
            contentColor = MaterialTheme.colorScheme.onSurface
        ),
        modifier = Modifier
            .height(150.dp)
            .clickable { onClick() }
    ) {
        Box(
            modifier = Modifier
                .padding(10.dp)
        ) {
            Column(
                Modifier.fillMaxSize()
            ) {
                Text(
                    text = libraryName,
                    textAlign = TextAlign.Center,
                    fontSize = 30.sp,
                    style = AppTypography.bodySmall,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = libraryDescription,
                    style = AppTypography.bodySmall,
                    fontSize = 10.sp,
                    modifier = Modifier.height(50.dp)
                )
                Spacer(Modifier.height(5.dp))
                Row(
                    modifier = Modifier.height(14.dp)
                ) {
                    Icon(imageVector = Icons.Filled.Bookmarks, contentDescription = null)

                    Text(
                        text = procedureCount.toString(),
                        style = AppTypography.bodySmall,
                        modifier = Modifier.padding(start = 5.dp)
                    )
                }
                Spacer(Modifier.height(5.dp))
                Row(
                    modifier = Modifier
                        .height(14.dp)

                ) {
                    Icon(imageVector = Icons.Filled.AccountCircle, contentDescription = null)

                    Text(
                        text = author,
                        style = AppTypography.bodySmall,
                        modifier = Modifier.padding(start = 5.dp)
                    )
                }
            }
            TextButton(
                onClick = {
                    onDeleteClick()
                },
                contentPadding = PaddingValues(0.dp),
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .size(25.dp)
                    .padding(0.dp)

            ) {
                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier
                        .size(20.dp)
                )
            }

        }
    }
}

@Composable
fun AddNewLibraryButton(onAddLibraryClick: () -> Unit) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.inversePrimary,
            contentColor = MaterialTheme.colorScheme.onSurface
        ),
        modifier = Modifier
            .height(150.dp)
            .fillMaxSize()
            .clickable { onAddLibraryClick() }
    ) {
        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
            )
        }
    }
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewLibraryScreen() {
    LogoInterpreterBetaTheme {
        LibraryScreen(
            modifier = Modifier,
            libraryViewModel = hiltViewModel(),
            navController = rememberNavController()
        )
    }
}
