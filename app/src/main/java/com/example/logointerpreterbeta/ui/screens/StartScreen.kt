package com.example.logointerpreterbeta.ui.screens

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.logointerpreterbeta.navigation.Interpreter
import com.example.logointerpreterbeta.navigation.Libraries
import com.example.logointerpreterbeta.navigation.Projects
import com.example.logointerpreterbeta.navigation.Settings
import com.example.logointerpreterbeta.navigation.TutorialScreen
import com.example.logointerpreterbeta.R
import com.example.logointerpreterbeta.repository.ConfigRepository
import com.example.logointerpreterbeta.ui.theme.AppTypography
import com.example.logointerpreterbeta.ui.theme.LogoInterpreterBetaTheme
import com.example.logointerpreterbeta.viewModels.ProjectViewModel

@Composable
fun StartScreenApp(
    navController: NavHostController = rememberNavController(),
    projectViewModel: ProjectViewModel
) {
    val context = LocalContext.current
    val configRepository = ConfigRepository(context)

    var isAlertVisable by rememberSaveable { mutableStateOf(false) }

    AnimatedVisibility(isAlertVisable) {
        AlertDialog(
            title = { Text(text = "Problem") },
            text = { Text(text = "Nie masz jeszcze żadnego projektu lub został on usunięty!") },
            onDismissRequest = { /*TODO*/ },
            confirmButton = {
                TextButton(
                    onClick = { isAlertVisable = false }
                ) {
                    Text("OK")
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
                    projectViewModel = projectViewModel,
                    configRepository = configRepository,
                    navController = navController
                ) {
                    isAlertVisable = true
                }
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
                    projectViewModel = projectViewModel,
                    configRepository = configRepository,
                    navController = navController
                ) {
                    isAlertVisable = true
                }
            }
        }
    }
}

@Composable
fun MenuButton(text: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.inversePrimary),
        modifier = Modifier
            .size(width = 280.dp, height = 60.dp)
            .shadow(5.dp, RoundedCornerShape(12.dp))
    ) {
        Text(
            text = text,
            style = AppTypography.bodyLarge.copy(
                color = MaterialTheme.colorScheme.onSurface, // możesz nadpisać kolor lub inne właściwości
                textAlign = TextAlign.Center // dostosowanie wyrównania tekstu
            ),
            modifier = modifier
        )
    }

}

@Composable
fun StartScreenMenu(
    projectViewModel: ProjectViewModel,
    navController: NavHostController,
    configRepository: ConfigRepository,
    onEmptyProjectAction: () -> Unit
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.fillMaxHeight()
    ) {
        item {
            MenuButton("Kontynuuj ostatni projekt",
                {
                    if (configRepository.readLastProjectJSON() == "") {
                        onEmptyProjectAction()
                    } else {
                        projectViewModel.updateProject()
                        navController.navigate(Interpreter)
                    }
                })
        }
        item { MenuButton("Projekty", { navController.navigate(Projects) }) }
        item { MenuButton("Poradniki", { navController.navigate(TutorialScreen) }) }
        item { MenuButton("Biblioteki", { navController.navigate(Libraries) }) }
        item { MenuButton("Ustawienia", { navController.navigate(Settings) }) }
    }
}

@Composable
fun AppLogo(modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Image(
            painterResource(id = R.drawable.logo),
            contentDescription = "logo",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .size(180.dp)
            //.padding(top = 40.dp)
        )
        Text(
            text = "LOGO IDE",
            fontSize = 34.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface,
            style = AppTypography.bodySmall,
            modifier = Modifier.padding(bottom = 12.dp)
        )
    }

}

@Preview(showBackground = true, showSystemUi = false, uiMode = 1)
@Composable
fun GreetingPreview2() {
    LogoInterpreterBetaTheme {
        StartScreenApp(
            navController = rememberNavController(), projectViewModel = hiltViewModel()
        )
    }
}