package com.example.logointerpreterbeta.ui.screens.start

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.logointerpreterbeta.R
import com.example.logointerpreterbeta.ui.navigation.Interpreter
import com.example.logointerpreterbeta.ui.navigation.Libraries
import com.example.logointerpreterbeta.ui.navigation.Projects
import com.example.logointerpreterbeta.ui.navigation.Settings
import com.example.logointerpreterbeta.ui.navigation.TutorialScreen
import com.example.logointerpreterbeta.ui.screens.start.components.AppLogo
import com.example.logointerpreterbeta.ui.screens.start.components.StartScreenMenu
import com.example.logointerpreterbeta.ui.theme.LogoInterpreterBetaTheme

@Composable
fun StartScreenRoot(
    navController: NavHostController = rememberNavController(),
    viewModel: StartScreenViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    uiState.navigateTo?.let { screen ->
        when (screen) {
            Screen.Interpreter -> navController.navigate(Interpreter)
            Screen.Projects -> navController.navigate(Projects)
            Screen.Tutorials -> navController.navigate(TutorialScreen)
            Screen.Libraries -> navController.navigate(Libraries)
            Screen.Settings -> navController.navigate(Settings)
        }
        viewModel.onNavigateConsumed()
    }

    StartScreen(
        showAlertDialog = uiState.showAlert,
        navigateTo = { viewModel.onNavigate(it) },
        onDialogDismiss = { viewModel.onDialogDismiss() },
        onContinueProjectClicked = { viewModel.onContinueProjectClicked() }
    )
}

@Composable
fun StartScreen(
    showAlertDialog: Boolean,
    navigateTo: (Screen) -> Unit,
    onDialogDismiss: () -> Unit,
    onContinueProjectClicked: () -> Unit
) {
    AnimatedVisibility(showAlertDialog) {
        AlertDialog(
            title = { Text(text = stringResource(R.string.problem)) },
            text = { Text(text = stringResource(R.string.dialog_zero_project)) },
            onDismissRequest = { onDialogDismiss() },
            confirmButton = {
                TextButton(
                    onClick = { onDialogDismiss() }
                ) {
                    Text(stringResource(R.string.ok))
                }
            }
        )
    }

    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    Surface(
        color = MaterialTheme.colorScheme.surface,
        modifier = Modifier.fillMaxHeight()
    ) {
        if (isLandscape) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(top = 40.dp)
            ) {
                AppLogo(Modifier.fillMaxWidth(0.5f))
                StartScreenMenu(
                    onNavigate = { navigateTo(it) },
                    onContinueProjectClicked = { navigateTo(Screen.Interpreter) }
                )
            }
        } else {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(top = 40.dp)
            ) {
                AppLogo()
                StartScreenMenu(
                    onNavigate = { navigateTo(it) },
                    onContinueProjectClicked = { onContinueProjectClicked() }
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = false, uiMode = 1)
@Composable
fun GreetingPreview2() {
    LogoInterpreterBetaTheme {
        StartScreen(
            showAlertDialog = false,
            navigateTo = {},
            onDialogDismiss = {},
            onContinueProjectClicked = {}
        )
    }
}