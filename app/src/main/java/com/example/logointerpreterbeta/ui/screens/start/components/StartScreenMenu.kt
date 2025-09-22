package com.example.logointerpreterbeta.ui.screens.start.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.logointerpreterbeta.R
import com.example.logointerpreterbeta.ui.screens.start.Screen

@Composable
fun StartScreenMenu(
    onNavigate: (Screen) -> Unit,
    onContinueProjectClicked: () -> Unit,
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.fillMaxHeight()
    ) {
        item {
            MenuButton(stringResource(R.string.continue_last_project)) {
                onContinueProjectClicked()
            }
        }
        item { MenuButton(stringResource(R.string.projects)) { onNavigate(Screen.Projects) } }
        item { MenuButton(stringResource(R.string.tutorials)) { onNavigate(Screen.Tutorials) } }
        item { MenuButton(stringResource(R.string.libraries)) { onNavigate(Screen.Libraries) } }
        item { MenuButton(stringResource(R.string.settings)) { onNavigate(Screen.Settings) } }
    }
}