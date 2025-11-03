package com.example.logointerpreterbeta.ui.screens.interpreter

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.logointerpreterbeta.domain.interpreter.LogoDebugger
import com.example.logointerpreterbeta.domain.models.Project
import com.example.logointerpreterbeta.domain.models.ProjectFile
import com.example.logointerpreterbeta.domain.usecase.file.ReadFileUseCase
import com.example.logointerpreterbeta.domain.usecase.file.SaveFileUseCase
import com.example.logointerpreterbeta.domain.usecase.interpreter.InterpretCodeUseCase
import com.example.logointerpreterbeta.domain.usecase.interpreter.LoadInitialFileUseCase
import com.example.logointerpreterbeta.domain.usecase.interpreter.ReadConfigSettingsUseCase
import com.example.logointerpreterbeta.domain.usecase.interpreter.SaveLastOpenedFileUseCase
import com.example.logointerpreterbeta.domain.usecase.shared.ObserveThemeUseCase
import com.example.logointerpreterbeta.ui.drawing.UIDrawingDelegate
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext

@HiltViewModel
class InterpreterViewModel @Inject constructor(
    readConfigSettingsUseCase: ReadConfigSettingsUseCase,
    private val uiDelegate: UIDrawingDelegate,
    private val logoDebugger: LogoDebugger,
    private val saveLastOpenedFileUseCase: SaveLastOpenedFileUseCase,
    private val observeThemeUseCase: ObserveThemeUseCase,
    private val interpretCodeUseCase: InterpretCodeUseCase,
    private val saveFileUseCase: SaveFileUseCase,
    private val readFilesUseCase: ReadFileUseCase,
    private val loadInitialFileUseCase: LoadInitialFileUseCase,
) : ViewModel() {

    companion object {
        private const val INIT_TURTLE_IMAGE_COMMAND = "ST"
        private const val RESET_TURTLE_POSITION_COMMAND = "SETXY 0 0"
        private val INITIAL_CODE_EDITOR_STATE =
            CodeEditorState(text = "\n\n\n\n\n\n\n\n\n\n\n", cursorPosition = 0)
    }

    private val _uiState = MutableStateFlow(
        InterpreterUiState(
            canvasBitmap = uiDelegate.bitmapFlow.value,
            turtleBitmap = uiDelegate.turtleBitmapFlow.value,
        )
    )
    val uiState: StateFlow<InterpreterUiState> = _uiState.asStateFlow()

    private val debugMutex = Mutex()
    private var isInitialCodeLoaded = false

    init {
        interpretCodeUseCase(INIT_TURTLE_IMAGE_COMMAND)

        viewModelScope.launch {
            logoDebugger.debuggerState.collect { newState ->
                _uiState.update { it.copy(debuggerState = newState) }
            }
        }

        viewModelScope.launch {
            readConfigSettingsUseCase().collect { settings ->
                _uiState.update { it.copy(configSettings = settings) }

            }
        }

        viewModelScope.launch {
            uiDelegate.turtleUi.collect { turtleState ->
                _uiState.update { it.copy(turtleState = turtleState) }
            }
        }

        viewModelScope.launch {
            uiDelegate.bitmapFlow.collect { bitmap ->
                _uiState.update { it.copy(canvasBitmap = bitmap) }
            }
        }
        viewModelScope.launch {
            uiDelegate.turtleBitmapFlow.collect { bitmap ->
                _uiState.update { it.copy(turtleBitmap = bitmap) }
            }
        }

        // theme change observer
        viewModelScope.launch {
            observeThemeUseCase().collect { isDark ->
                uiDelegate.updateTheme(isDark)
                _uiState.update { it.copy(isDarkMode = isDark) }
            }
        }
    }

    fun onEvent(event: InterpreterEvent) {
        when (event) {
            // editor events
            is InterpreterEvent.OnCodeChange -> handleCodeChange(
                event.newText,
                event.newCursorPosition
            )

            // interpreter events
            InterpreterEvent.InterpretCode -> handleInterpretCode()
            InterpreterEvent.ClearErrors -> _uiState.update { it.copy(errors = emptyList()) }

            // debugger events
            InterpreterEvent.EnableDebugging -> handleEnableDebugging()
            InterpreterEvent.DisableDebugging -> logoDebugger.disableDebugging()
            InterpreterEvent.StepOverLoop -> logoDebugger.stepOverLoop()
            InterpreterEvent.NextStep -> logoDebugger.nextStep()
            InterpreterEvent.StepIn -> logoDebugger.stepIn()
            InterpreterEvent.StepOut -> logoDebugger.stepOut()
            InterpreterEvent.ContinueExecution -> logoDebugger.continueExecution()
            is InterpreterEvent.ToggleBreakpoint -> logoDebugger.toggleBreakpoint(event.lineNumber)
            InterpreterEvent.ClearBreakpoints -> logoDebugger.clearBreakpoints()

            // ui events
            InterpreterEvent.ToggleErrorListVisibility -> _uiState.update {
                it.copy(
                    isErrorListExpanded = !it.isErrorListExpanded
                )
            }

            // file events
            is InterpreterEvent.LoadInitialCode -> handleLoadInitialCode(event.project)
            is InterpreterEvent.SaveFile -> handleSaveFile(event.projectName)
            is InterpreterEvent.FileTapped -> handleTapFileAction(event.file, event.project)
        }
    }

    private fun handleCodeChange(newText: String, newCursorPosition: Int) {
        _uiState.update {
            it.copy(
                codeEditorState = it.codeEditorState.copy(
                    text = newText,
                    cursorPosition = newCursorPosition
                )
            )
        }
    }

    private fun handleLoadInitialCode(project: Project?) {
        if (isInitialCodeLoaded || project == null) return
        isInitialCodeLoaded = true

        viewModelScope.launch {
            loadInitialFileUseCase(project)
                .onSuccess { (fileName, code) ->
                    _uiState.update {
                        it.copy(
                            codeEditorState = it.codeEditorState.copy(
                                text = code,
                                cursorPosition = 0
                            ),
                            currentFileName = fileName
                        )
                    }
                }
                .onFailure {
                    // no file, empty editor
                    _uiState.update {
                        it.copy(
                            codeEditorState = INITIAL_CODE_EDITOR_STATE,
                            currentFileName = null
                        )
                    }
                }
        }
    }

    private fun handleInterpretCode(text: String = _uiState.value.codeEditorState.text) {
        viewModelScope.launch {
            _uiState.update { it.copy(errors = emptyList()) } // Clear errors first
            val result = interpretCodeUseCase(text)

            if (!result.success) {
                _uiState.update { it.copy(errors = result.errors) }
            }
        }
    }

    private fun handleEnableDebugging() {
        _uiState.update { it.copy(debuggerState = it.debuggerState.copy(isDebugging = true)) }
        logoDebugger.enableDebugging()
        debugCode()
    }

    private fun debugCode() {
        viewModelScope.launch(Dispatchers.Default) {
            debugMutex.withLock {
                _uiState.update { it.copy(errors = emptyList()) }
                val result = logoDebugger.debug(_uiState.value.codeEditorState.text)

                withContext(Dispatchers.Main) {
                    if (!result.success) {
                        _uiState.update { it.copy(errors = result.errors) }
                    }
                }
            }
        }
    }

    private fun handleSaveFile(actualProjectName: String) {
        val state = _uiState.value
        val actualFileName =
            state.currentFileName ?: return // don't save if no file is currently open
        val content = state.codeEditorState.text

        viewModelScope.launch(Dispatchers.IO) {
            val result = saveFileUseCase(actualFileName, actualProjectName, content)
            result.onFailure {
                Log.e("InterpreterViewModel", "Error saving file", it)
            }
        }
    }

    private fun handleTapFileAction(projectFile: ProjectFile, project: Project?) {
        if (project == null) {
            _uiState.update { it.copy(errors = listOf("Project not selected.")) }
            return
        }
        if (projectFile.name == _uiState.value.currentFileName) {
            return
        }

        logoDebugger.clearBreakpoints()

        viewModelScope.launch(Dispatchers.IO) {
            readFilesUseCase(projectFile.name, project.name)
                .onSuccess { codeFromFile ->
                    withContext(Dispatchers.Main) {
                        _uiState.update {
                            it.copy(
                                codeEditorState = it.codeEditorState.copy(
                                    text = codeFromFile,
                                    cursorPosition = 0
                                ),
                                currentFileName = projectFile.name
                            )
                        }
                    }
                    interpretCodeUseCase(RESET_TURTLE_POSITION_COMMAND)
                    saveLastOpenedFileUseCase(project.name, projectFile.name)
                }
                .onFailure { exception ->
                    withContext(Dispatchers.Main) {
                        _uiState.update { it.copy(errors = listOf("Error reading file: ${exception.message}")) }
                    }
                    Log.e("InterpreterViewModel", "Failed to read file", exception)
                }
        }
    }
}