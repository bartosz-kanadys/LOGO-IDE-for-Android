package com.example.logointerpreterbeta.ui.screens.interpreter

import android.graphics.Bitmap
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.logointerpreterbeta.domain.interpreter.LogoDebugger
import com.example.logointerpreterbeta.domain.interpreter.LogoTextColorizer
import com.example.logointerpreterbeta.domain.models.DebuggerState
import com.example.logointerpreterbeta.domain.models.Project
import com.example.logointerpreterbeta.domain.models.ProjectFile
import com.example.logointerpreterbeta.domain.usecase.interpreter.InterpretCodeUseCase
import com.example.logointerpreterbeta.domain.usecase.interpreter.ReadConfigSettingsUseCase
import com.example.logointerpreterbeta.domain.usecase.file.ReadFileUseCase
import com.example.logointerpreterbeta.domain.usecase.file.SaveFileUseCase
import com.example.logointerpreterbeta.domain.usecase.interpreter.ThemeModeCheckUseCase
import com.example.logointerpreterbeta.domain.visitors.DebugStateListener
import com.example.logointerpreterbeta.ui.drawing.UIDrawingDelegate
import com.example.logointerpreterbeta.ui.models.TurtleUI
import com.example.logointerpreterbeta.ui.screens.interpreter.components.codeEditor.textFunctions.textDiffrence
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class InterpreterViewModel @Inject constructor(
    readConfigSettingsUseCase: ReadConfigSettingsUseCase,
    drawingDelegate: UIDrawingDelegate,
    private val logoDebugger: LogoDebugger,
    private val themeModeCheckUseCase: ThemeModeCheckUseCase,
    private val interpretCodeUseCase: InterpretCodeUseCase,
    private val saveFileUseCase: SaveFileUseCase,
    private val readFilesUseCase: ReadFileUseCase,
) : ViewModel(), DebugStateListener {

    companion object {
        private const val INIT_TURTLE_IMAGE_COMMAND = "st"
    }

    private val debugMutex = Mutex()

    private var codeState by mutableStateOf(TextFieldValue("\n\n\n\n\n\n\n\n\n\n\n"))
    private var cursorPosition by mutableIntStateOf(0)

    private val _isErrorListExpanded = MutableStateFlow(false)
    var isErrorListExpanded = _isErrorListExpanded.asStateFlow()

    private val _debuggerState = MutableStateFlow(DebuggerState())
    val debuggerState: StateFlow<DebuggerState> = _debuggerState.asStateFlow()

    private val _img = drawingDelegate.bitmapFlow
    val img: StateFlow<Bitmap> = _img

    private val _arrowImg = drawingDelegate.turtleBitmapFlow
    val arrowImg: StateFlow<Bitmap> = _arrowImg

    private val _errors = MutableStateFlow<List<String>>(emptyList())
    val errors: StateFlow<List<String>> = _errors.asStateFlow()

    private val _turtleState = drawingDelegate.turtleUi
    val turtleState: StateFlow<TurtleUI> = _turtleState

    var config by mutableStateOf(readConfigSettingsUseCase)

    init {
        interpretCodeUseCase(INIT_TURTLE_IMAGE_COMMAND)
        logoDebugger.addDebugStateListener(this)
    }

    //Observer method
    override fun onStateUpdate(newState: DebuggerState) {
        viewModelScope.launch(Dispatchers.Main) {
            _debuggerState.value = newState
        }
    }

    fun updateCodeState(newCode: String) {
        codeState = TextFieldValue(newCode)
    }

    fun getCodeStateAsString(): String = codeState.text

    fun getCodeStateAsTextFieldValue(): TextFieldValue = codeState

    fun onCodeChange(newCode: TextFieldValue) {
        val isDarkMode = themeModeCheckUseCase()
        cursorPosition = newCode.selection.start
        codeState = newCode.copy(
            annotatedString = textDiffrence(
                codeState.annotatedString,
                newCode.text,
            ) { text ->
                LogoTextColorizer.colorizeText(text, isDarkMode)
            }
        )
    }

    fun toggleErrorListVisibility() {
        _isErrorListExpanded.update { !_isErrorListExpanded.value  }
    }

    fun colorCode() {
        val isDarkMode = themeModeCheckUseCase()

        codeState = codeState.copy(
            annotatedString = LogoTextColorizer.colorizeText(codeState.text, isDarkMode)
        )
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

    fun interpretCode(text: String = codeState.text) {
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

                val result = logoDebugger.debug(codeState.text)

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
        updateActualFileName: (String) -> Unit,
    ) {
        if (project == null) {
            _errors.value = listOf("Project not selected.")
            return
        }

        clearBreakpoints()
        readFilesUseCase(projectFile.name, project.name)
            .onSuccess { codeFromFile ->
                updateCodeState(codeFromFile)
                colorCode()
                updateActualFileName(projectFile.name)
            }
            .onFailure { exception ->
                _errors.value = listOf("Error reading file: ${exception.message}")
                Log.e("InterpreterViewModel", "Failed to read file", exception)
            }
    }
}