package com.example.logointerpreterbeta.ui.screens.interpreter

import android.content.Context
import android.graphics.Bitmap
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.logointerpreterbeta.data.repository.FileRepositoryImpl
import com.example.logointerpreterbeta.domain.interpreter.LogoDebugger
import com.example.logointerpreterbeta.domain.interpreter.LogoInterpreter
import com.example.logointerpreterbeta.domain.interpreter.LogoTextColorizer
import com.example.logointerpreterbeta.domain.models.DebuggerState
import com.example.logointerpreterbeta.domain.models.Project
import com.example.logointerpreterbeta.domain.models.ProjectFile
import com.example.logointerpreterbeta.domain.repository.ConfigRepository
import com.example.logointerpreterbeta.domain.repository.FileRepository
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
import com.example.logointerpreterbeta.domain.repository.ThemeRepository
import com.example.logointerpreterbeta.domain.visitors.DebugStateListener
import com.example.logointerpreterbeta.ui.screens.projects.ProjectViewModel

@HiltViewModel
class InterpreterViewModel @Inject constructor(
    private val logo: LogoInterpreter,
    private val logoDebugger: LogoDebugger,
    drawingDelegate: UIDrawingDelegate,
    configRepository: ConfigRepository,
    private val themeRepository: ThemeRepository,
    private val fileRepository: FileRepository
) : ViewModel(), DebugStateListener {

    val INIT_TURTLE_IMAGE_COMMAND = "st"
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

    var config by mutableStateOf(configRepository.readSettings())

    init {
        logo.interpret(INIT_TURTLE_IMAGE_COMMAND)
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
        val isDarkMode = themeRepository.isDarkTheme()
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
        val isDarkMode = themeRepository.isDarkTheme()

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

            val result = logo.interpret(text)

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

    fun saveFile(context: Context, actualFileName: String, actualProjectName: String, content: String) {
        fileRepository.writeFileContent(
            context,
            actualFileName,
            actualProjectName,
            content
        )
    }

    fun onTapFileAction(
        context: Context,
        projectFile: ProjectFile,
        project: Project?,
        updateActualFileName: (String) -> Unit,
    ) {
        clearBreakpoints()
        updateCodeState(
            fileRepository.readFileContent(
                context,
                projectFile.name,
                project!!.name
            )!!
        )
        colorCode()
        updateActualFileName(projectFile.name)
    }

    fun readFileFromRepository(context: Context, actualFile: String, projectName: String): String? {
        return fileRepository.readFileContent(context, actualFile, projectName)

    }
}