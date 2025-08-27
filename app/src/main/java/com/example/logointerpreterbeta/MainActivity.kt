package com.example.logointerpreterbeta

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.logointerpreterbeta.data.repository.ConfigRepositoryImpl
import com.example.logointerpreterbeta.ui.navigation.AppNavHost
import com.example.logointerpreterbeta.ui.screens.projects.ProjectViewModel
import com.example.logointerpreterbeta.ui.screens.settings.SettingsViewModel
import com.example.logointerpreterbeta.ui.theme.LogoInterpreterBetaTheme
import com.example.logointerpreterbeta.ui.theme.ThemeMode
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val projectViewModel: ProjectViewModel = hiltViewModel()
            val settingsViewModel: SettingsViewModel = hiltViewModel()
            val context = LocalContext.current
            val configRepository = ConfigRepositoryImpl(context)
            configRepository.createConfigFile(context)
            projectViewModel.loadLastProjectFromJSON()
            settingsViewModel.loadSettingsFromJson()
            LogoInterpreterBetaTheme(
                darkTheme = when(SettingsViewModel.currentTheme){
                    ThemeMode.SYSTEM_THEME -> isSystemInDarkTheme()
                    ThemeMode.LIGHT_THEME -> false
                    ThemeMode.DARK_THEME -> true
                }
            ) {
                AppNavHost(projectViewModel = projectViewModel, settingsViewModel = settingsViewModel)
            }
        }
    }
}