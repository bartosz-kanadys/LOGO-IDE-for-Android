package com.example.logointerpreterbeta.ui.Screens.libraryScreens

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.logointerpreterbeta.Navigation.LibraryProcedureForm
import com.example.logointerpreterbeta.ui.theme.LogoInterpreterBetaTheme
import com.example.logointerpreterbeta.viewModels.LibraryViewModel

@Composable
fun LibraryProceduresScreen(libraryViewModel: LibraryViewModel, navController: NavController, modifier: Modifier) {
    val libraries = libraryViewModel.libraries.collectAsStateWithLifecycle()
    val actualLibrary = libraryViewModel.actualLibrary.collectAsStateWithLifecycle()

    val library = libraries.value.find { it.name == actualLibrary.value }
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(15.dp)
    ) {
        item{
            AddProcedureButton {
                navController.navigate(LibraryProcedureForm)
            }
        }
        if (library!!.procedures.isEmpty()) {
            item{
                Text(
                    text = "Nie ma jeszcze procedur w tej bibliotece",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(0.8f).padding(top = 10.dp)

                )
            }
        } else {
            items(library.procedures) { procedure ->

            }

        }
    }
}

@Composable
fun AddProcedureButton(onClick: () -> Unit) {
    Button(
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.inversePrimary),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(4.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = "Dodaj procedure",
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 18.sp
            )
        }

    }
}

@Preview
@Composable
fun PreviewLibrrayProceduresScreen() {
    LogoInterpreterBetaTheme {
        LibraryProceduresScreen(libraryViewModel = LibraryViewModel(LocalContext.current), navController = rememberNavController(), modifier = Modifier)
    }
}