package com.example.logointerpreterbeta.ui.screens.start

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.logointerpreterbeta.domain.repository.ConfigRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class StartScreenUiState(
    val showAlert: Boolean = false,
    val navigateTo: Screen? = null
)

@HiltViewModel
class StartScreenViewModel @Inject constructor(
    private val configRepository: ConfigRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(StartScreenUiState())
    val uiState: StateFlow<StartScreenUiState> = _uiState

    fun onContinueProjectClicked(navController: NavController) {
        viewModelScope.launch {
            val lastProject = configRepository.readLastProject().first()
            if (lastProject.isNullOrEmpty()) {
                _uiState.update { it.copy(showAlert = true) }
            } else {
                _uiState.update { it.copy(navigateTo = Screen.Interpreter) }
                navController.navigate("InterpreterApp/$lastProject")
            }
        }
    }

    fun onDialogDismiss() {
        _uiState.update { it.copy(showAlert = false) }
    }

    fun onNavigateConsumed() {
        _uiState.update { it.copy(navigateTo = null) }
    }

    fun onNavigate(screen: Screen) {
        _uiState.update { it.copy(navigateTo = screen) }
    }
}

sealed class Screen {
    object Interpreter : Screen()
    object Projects : Screen()
    object Tutorials : Screen()
    object Libraries : Screen()
    object Settings : Screen()
}
