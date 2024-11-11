package com.example.logointerpreterbeta.ui.Screens.libraryScreens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.logointerpreterbeta.ui.theme.LogoInterpreterBetaTheme
import com.example.logointerpreterbeta.viewModels.LibraryViewModel

@Composable
fun LibraryFormScreen(libraryViewModel: LibraryViewModel, modifier: Modifier = Modifier, navController: NavController) {
    var libraryName by rememberSaveable { mutableStateOf("") }
    var libraryDescription by rememberSaveable { mutableStateOf("") }
    var libraryAuthor by rememberSaveable { mutableStateOf("") }
    val context = LocalContext.current
    Column(
        modifier = modifier
            .padding(15.dp)
    ) {
        FormTextField(
            state = libraryName,
            onValueChange = {libraryName = it},
            label = "Nazwa Biblioteki",
            placeholder = "Podaj nazwę"
        )
        Spacer(Modifier.height(5.dp))
        FormTextField(
            state = libraryDescription,
            onValueChange = {libraryDescription = it},
            label = "Opis",
            placeholder = "Dodaj opis"
        )
        Spacer(Modifier.height(5.dp))
        FormTextField(
            state = libraryAuthor,
            onValueChange = {libraryAuthor = it},
            label = "Autor",
            placeholder = "Podaj Autora"
        )
        Spacer(Modifier.height(15.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            FormButton(text = "Anuluj", MaterialTheme.colorScheme.errorContainer) { navController.popBackStack() }
            FormButton(text = "Zatwierdź", MaterialTheme.colorScheme.primary) {
                if (libraryViewModel.createLibrary(context,libraryName,libraryDescription, libraryAuthor )) {
                    navController.popBackStack()
                }
            }
        }

    }
}

@Composable
fun FormTextField(
    state: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String
) {
    OutlinedTextField(
        value = state,
        onValueChange = {newValue -> onValueChange(newValue)},
        label = { Text(text = label) },
        placeholder = { Text(text = placeholder) },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.secondary
        ),
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp)
    )
}

@Composable
fun FormButton(text: String, color: Color, onClick: () -> Unit) {
    Button(
        colors = ButtonDefaults.buttonColors(color),
        onClick = {onClick()}
    ) {
        Text(text = text)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewLibraryFormScreen() {
    LogoInterpreterBetaTheme {
        LibraryFormScreen(modifier = Modifier, libraryViewModel = LibraryViewModel(LocalContext.current), navController = rememberNavController())
    }
}