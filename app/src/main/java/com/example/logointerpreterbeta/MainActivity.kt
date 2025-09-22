package com.example.logointerpreterbeta

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.logointerpreterbeta.domain.repository.ConfigRepository
import com.example.logointerpreterbeta.ui.navigation.AppNavHost
import com.example.logointerpreterbeta.ui.screens.projects.ProjectViewModel
import dagger.hilt.android.AndroidEntryPoint
import jakarta.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    @Inject lateinit var configRepository: ConfigRepository
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val projectViewModel: ProjectViewModel = hiltViewModel()
            projectViewModel.loadLastProjectFromJSON()

            AppNavHost(projectViewModel = projectViewModel, configRepository = configRepository)
        }
    }
}