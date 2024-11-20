package com.example.logointerpreterbeta.components.interpreterButtons


import androidx.annotation.Dimension
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.outlined.Build
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
import com.example.logointerpreterbeta.viewModels.InterpreterViewModel


@Composable
fun InterpreterButtons(viewModel: InterpreterViewModel){
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 15.dp, end = 5.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if(!viewModel.isDebugging) {
                CodeEditorButton(icon = Icons.Filled.PlayArrow) { viewModel.interpretCode() }
                CodeEditorButton(Icons.Outlined.Build, size = 30) { viewModel.enableDebugging() }
            }
            else {
                CodeEditorButton(icon = Icons.Filled.PlayArrow) { viewModel.continueExecution() }
                CodeEditorButton(
                    Icons.Filled.AccountBox,
                    MaterialTheme.colorScheme.errorContainer
                ) { viewModel.disableDebugging() }

                CodeEditorButton(Icons.AutoMirrored.Filled.ArrowForward) { viewModel.nextStep() }
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