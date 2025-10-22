package com.example.logointerpreterbeta.ui.screens.interpreter

import android.graphics.Bitmap
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.logointerpreterbeta.domain.drawing.DrawingDelegate
import com.example.logointerpreterbeta.domain.interpreter.LogoDebugger
import com.example.logointerpreterbeta.domain.models.DebuggerState
import com.example.logointerpreterbeta.domain.models.Project
import com.example.logointerpreterbeta.domain.models.ProjectFile
import com.example.logointerpreterbeta.domain.repository.SessionRepository
import com.example.logointerpreterbeta.domain.usecase.file.ReadFileUseCase
import com.example.logointerpreterbeta.domain.usecase.file.SaveFileUseCase
import com.example.logointerpreterbeta.domain.usecase.interpreter.InterpretCodeUseCase
import com.example.logointerpreterbeta.domain.usecase.interpreter.LoadInitialFileUseCase
import com.example.logointerpreterbeta.domain.usecase.interpreter.ReadConfigSettingsUseCase
import com.example.logointerpreterbeta.domain.usecase.shared.ObserveThemeUseCase
import com.example.logointerpreterbeta.domain.visitors.DebugStateListener
import com.example.logointerpreterbeta.ui.drawing.AndroidDrawingDelegate
import com.example.logointerpreterbeta.ui.drawing.UIDrawingDelegate
import com.example.logointerpreterbeta.ui.models.TurtleUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class InterpreterViewModel @Inject constructor(
    readConfigSettingsUseCase: ReadConfigSettingsUseCase,
    private val uiDelegate: UIDrawingDelegate,
    private val logoDebugger: LogoDebugger,
    private val sessionRepository: SessionRepository,
    private val observeThemeUseCase: ObserveThemeUseCase,
    private val interpretCodeUseCase: InterpretCodeUseCase,
    private val saveFileUseCase: SaveFileUseCase,
    private val readFilesUseCase: ReadFileUseCase,
    private val loadInitialFileUseCase: LoadInitialFileUseCase,
) : ViewModel(), DebugStateListener {

    companion object {
        private const val INIT_TURTLE_IMAGE_COMMAND = "st"
    }

    private data class CodeEditorState(
        val text: String = "\n\n\n\n\n\n\n\n\n\n\n",
        val cursorPosition: Int = 0
    )
    private val _codeEditorState = MutableStateFlow(CodeEditorState())

    val code: StateFlow<String> = _codeEditorState.map { it.text }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), _codeEditorState.value.text)

    val isDarkMode: StateFlow<Boolean> = observeThemeUseCase()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = false
        )

    private val _actualFileName = MutableStateFlow<String?>(null)
    val actualFileName: StateFlow<String?> = _actualFileName.asStateFlow()

    private val debugMutex = Mutex()

    private val _isErrorListExpanded = MutableStateFlow(false)
    var isErrorListExpanded = _isErrorListExpanded.asStateFlow()

    private val _debuggerState = MutableStateFlow(DebuggerState())
    val debuggerState: StateFlow<DebuggerState> = _debuggerState.asStateFlow()

    private val _img = uiDelegate.bitmapFlow
    val img: StateFlow<Bitmap> = _img

    private val _arrowImg = uiDelegate.turtleBitmapFlow
    val arrowImg: StateFlow<Bitmap> = _arrowImg

    private val _errors = MutableStateFlow<List<String>>(emptyList())
    val errors: StateFlow<List<String>> = _errors.asStateFlow()

    private val _turtleState = uiDelegate.turtleUi
    val turtleState: StateFlow<TurtleUI> = _turtleState

    var config by mutableStateOf(readConfigSettingsUseCase)

    private var isInitialCodeLoaded = false

    init {
        interpretCodeUseCase(INIT_TURTLE_IMAGE_COMMAND)
        logoDebugger.addDebugStateListener(this)

        viewModelScope.launch {
            isDarkMode.collect { isDark ->
                uiDelegate.updateTheme(isDark)
            }
        }
    }

    fun updateCode(code: String) {
        _codeEditorState.update { it.copy(text = code) }
    }

    //Observer method
    override fun onStateUpdate(newState: DebuggerState) {
        viewModelScope.launch(Dispatchers.Main) {
            _debuggerState.value = newState
        }
    }

    fun loadInitialCode(project: Project?) {
        if (isInitialCodeLoaded || project == null) return
        isInitialCodeLoaded = true

        viewModelScope.launch { // Nie ma potrzeby Dispatchers.IO, UseCase zarządza własnym kontekstem
            loadInitialFileUseCase(project)
                .onSuccess { (fileName, code) ->
                    onCodeChange(code, 0) // Zakładam, że onCodeChange jest na Main
                    _actualFileName.value = fileName
                }
                .onFailure {
                    // Brak plików, pusty edytor
                    onCodeChange("", 0)
                }
        }
    }

    fun onCodeChange(newText: String, newCursorPosition: Int) {
        // Aktualizuj stan tylko jeśli tekst faktycznie się zmienił
        // (Unikamy zbędnych emisji przy samym ruchu kursora)
        if (_codeEditorState.value.text != newText) {
            _codeEditorState.update {
                it.copy(text = newText, cursorPosition = newCursorPosition)
            }
        } else {
            _codeEditorState.update {
                it.copy(cursorPosition = newCursorPosition)
            }
        }
    }

    fun toggleErrorListVisibility() {
        _isErrorListExpanded.update { !_isErrorListExpanded.value  }
    }

    fun enableDebugging() {
        _debuggerState.update { it.copy(isDebugging = true) }
        logoDebugger.enableDebugging()
        debugCode()
    }

    fun clearErrors() {
        _errors.value = emptyList()
    }

    fun disableDebugging() = logoDebugger.disableDebugging()

    fun nextStep() = logoDebugger.nextStep()

    fun stepIn() = logoDebugger.stepIn()

    fun stepOut() = logoDebugger.stepOut()

    fun continueExecution() = logoDebugger.continueExecution()

    fun toggleBreakpoint(lineNumber: Int) = logoDebugger.toggleBreakpoint(lineNumber)

    fun clearBreakpoints() = logoDebugger.clearBreakpoints()

    fun interpretCode(text: String = code.value) {
        viewModelScope.launch {
            clearErrors()
            val result = interpretCodeUseCase(text)

            if (!result.success) {
                _errors.value = result.errors
            }
        }
    }

    private fun debugCode() {
        viewModelScope.launch(Dispatchers.Default) {
            debugMutex.withLock {
                clearErrors()

                val result = logoDebugger.debug(code.value)

                withContext(Dispatchers.Main) {
                    if (!result.success) {
                        _errors.value = result.errors
                    }
                }
            }
        }
    }

    fun saveFile(actualFileName: String, actualProjectName: String, content: String) {
        val result = saveFileUseCase(actualFileName, actualProjectName, content)

        result.onFailure {
            Log.e("InterpreterViewModel", "Error saving file", it)
        }
    }

    fun readFileFromRepository(fileName: String, projectName: String): Result<String> {
        return readFilesUseCase(fileName, projectName)
    }

    fun onTapFileAction(
        projectFile: ProjectFile,
        project: Project?,
    ) {
        if (project == null) {
            _errors.value = listOf("Project not selected.")
            return
        }

        clearBreakpoints()

        // 1. Uruchom operację I/O w tle
        viewModelScope.launch(Dispatchers.IO) {
            readFilesUseCase(projectFile.name, project.name)
                .onSuccess { codeFromFile ->
                    // 2. Zaktualizuj stan na wątku głównym
                    withContext(Dispatchers.Main) {
                        onCodeChange(codeFromFile, 0)
                        _actualFileName.value = projectFile.name
                    }
                    // 3. Zapis sesji (już jest w IO, więc jest OK)
                    sessionRepository.saveLastOpenedFile(project.name, projectFile.name)
                }
                .onFailure { exception ->
                    // 4. Błędy również na wątku głównym
                    withContext(Dispatchers.Main) {
                        _errors.value = listOf("Error reading file: ${exception.message}")
                    }
                    Log.e("InterpreterViewModel", "Failed to read file", exception)
                }
        }
    }
}