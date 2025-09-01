package com.example.logointerpreterbeta.ui.screens.interpreter.components


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.BugReport
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.logointerpreterbeta.ui.screens.interpreter.InterpreterViewModel
import com.example.logointerpreterbeta.domain.visitors.DebuggerVisitor


@Composable
fun InterpreterButtons(
    viewModel: InterpreterViewModel,
    isDebugging: Boolean,
    isStepInButtonVisible: Boolean,
    isStepOutButtonVisible: Boolean,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 15.dp, end = 5.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (!isDebugging) {
                CodeEditorButton(icon = Icons.Filled.PlayArrow) { viewModel.interpretCode() }
                CodeEditorButton(Icons.Filled.BugReport, size = 30) { viewModel.enableDebugging() }
            } else {
                CodeEditorButton(icon = Icons.Filled.PlayArrow) { viewModel.continueExecution() }
                CodeEditorButton(
                    Icons.Filled.Stop,
                    MaterialTheme.colorScheme.errorContainer
                ) { viewModel.disableDebugging() }

                CodeEditorButton(Icons.Filled.ArrowForward) { viewModel.nextStep() }
                if (isStepInButtonVisible) {
                    CodeEditorButton(Icons.Filled.ArrowDownward) { viewModel.stepIn() }
                }
                if (isStepOutButtonVisible) {
                    CodeEditorButton(Icons.Filled.ArrowUpward) { viewModel.stepOut() }
                }
            }
        }
    }
}

@Composable
fun CodeEditorButton(
    icon: ImageVector,
    color: Color = MaterialTheme.colorScheme.secondaryContainer,
    size: Int = 45,
    onClick: () -> Unit

) {
    Button(
        shape = CircleShape,
        contentPadding = PaddingValues(0.dp),
        colors = ButtonDefaults.buttonColors(color),
        onClick = { onClick() },
        modifier = Modifier
            .padding(top = 10.dp) // Dodanie odstępu od pierwszego przycisku
            .size(size.dp)
    ) {
        Icon(
            imageVector = icon, // Icons.Outlined.Build,
            contentDescription = null,
            modifier = Modifier
                .width(40.dp)
                .align(Alignment.CenterVertically)
        )
    }
}