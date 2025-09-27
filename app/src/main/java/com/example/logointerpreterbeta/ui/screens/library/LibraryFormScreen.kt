package com.example.logointerpreterbeta.ui.screens.library

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.logointerpreterbeta.R
import com.example.logointerpreterbeta.ui.screens.library.components.FormButton
import com.example.logointerpreterbeta.ui.screens.library.components.FormTextField
import com.example.logointerpreterbeta.ui.theme.LogoInterpreterBetaTheme

@Composable
fun LibraryFormScreen(
    libraryViewModel: LibraryViewModel,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
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
            onValueChange = { libraryName = it },
            label = stringResource(R.string.library_name),
            placeholder = stringResource(R.string.enter_name)
        )
        Spacer(Modifier.height(5.dp))
        FormTextField(
            state = libraryDescription,
            onValueChange = { libraryDescription = it },
            label = stringResource(R.string.description_label),
            placeholder = stringResource(R.string.add_description_label)
        )
        Spacer(Modifier.height(5.dp))
        FormTextField(
            state = libraryAuthor,
            onValueChange = { libraryAuthor = it },
            label = stringResource(R.string.author),
            placeholder = stringResource(R.string.enter_author)
        )
        Spacer(Modifier.height(15.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            FormButton(
                text = stringResource(R.string.cancel),
                MaterialTheme.colorScheme.errorContainer
            ) {
                onBack()
            }
            FormButton(
                text = stringResource(R.string.confirm),
                color = MaterialTheme.colorScheme.primary
            ) {
                val creatingResult = libraryViewModel.createLibrary(
                    name = libraryName,
                    desc = libraryDescription,
                    author = libraryAuthor
                )
                makeToast(context, creatingResult)
                if (creatingResult == LibraryCodes.OK) {
                    onBack()
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewLibraryFormScreen() {
    LogoInterpreterBetaTheme {
        LibraryFormScreen(
            modifier = Modifier,
            libraryViewModel = hiltViewModel(),
            onBack = {}
        )
    }
}