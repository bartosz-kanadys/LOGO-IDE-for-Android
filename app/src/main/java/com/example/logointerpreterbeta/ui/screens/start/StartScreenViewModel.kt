package com.example.logointerpreterbeta.ui.screens.start

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.logointerpreterbeta.domain.repository.ConfigRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@HiltViewModel
class StartScreenViewModel @Inject constructor(
    private val configRepository: ConfigRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(StartScreenUiState())
    val uiState: StateFlow<StartScreenUiState> = _uiState

    fun onContinueProjectClicked() {
        viewModelScope.launch {
            val lastProject = configRepository.readLastProject().first()
            if (lastProject.isNullOrEmpty()) {
                _uiState.value = _uiState.value.copy(showAlert = true)
            } else {
                _uiState.value = _uiState.value.copy(navigateTo = Screen.Interpreter)
            }
        }
    }

    fun onDialogDismiss() {
        _uiState.value = _uiState.value.copy(showAlert = false)
    }

    fun onNavigateConsumed() {
        _uiState.value = _uiState.value.copy(navigateTo = null)
    }

    fun onNavigate(screen: Screen) {
        _uiState.value = _uiState.value.copy(navigateTo = screen)
    }
}

data class StartScreenUiState(
    val showAlert: Boolean = false,
    val navigateTo: Screen? = null
)

sealed class Screen {
    object Interpreter : Screen()
    object Projects : Screen()
    object Tutorials : Screen()
    object Libraries : Screen()
    object Settings : Screen()
}
