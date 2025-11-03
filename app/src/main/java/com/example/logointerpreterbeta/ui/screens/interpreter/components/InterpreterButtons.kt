package com.example.logointerpreterbeta.ui.screens.interpreter.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.logointerpreterbeta.R

@Composable
fun InterpreterButtons(
    isDebugging: Boolean,
    isStepInButtonVisible: Boolean,
    isStepOutButtonVisible: Boolean,
    isStepOverLoopButtonVisible: Boolean,
    onInterpretCode: () -> Unit,
    onEnableDebugging: () -> Unit,
    onDisableDebugging: () -> Unit,
    onNextStep: () -> Unit,
    onStepIn: () -> Unit,
    onStepOut: () -> Unit,
    onStepOverLoop: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 15.dp, end = 5.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (!isDebugging) {
                CodeEditorButton(painterResource(id = R.drawable.baseline_play_arrow_24)) { onInterpretCode() }
                CodeEditorButton(painterResource(id = R.drawable.outline_bug_report_24)) { onEnableDebugging() }
            } else {
                CodeEditorButton(painterResource(id = R.drawable.outline_step_24)) { onNextStep() }

                CodeEditorButton(
                    icon = painterResource(id = R.drawable.baseline_stop_24),
                    color =  MaterialTheme.colorScheme.errorContainer
                ) { onDisableDebugging() }

                if (isStepInButtonVisible) {
                    CodeEditorButton(painterResource(id = R.drawable.outline_step_into_24)) { onStepIn() }
                }
                if (isStepOutButtonVisible) {
                    CodeEditorButton(painterResource(id = R.drawable.outline_step_out_24)) { onStepOut() }
                }
                if (isStepOverLoopButtonVisible) {
                    CodeEditorButton(painterResource( id = R.drawable.outline_step_over_24)) {
                        onStepOverLoop()
                    }
                }
            }
        }
    }
}

@Composable
fun CodeEditorButton(
    icon: Painter,
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
            .padding(top = 10.dp)
            .size(size.dp)
    ) {
        Icon(
            painter = icon,
            contentDescription = null,
            modifier = Modifier
                .width(40.dp)
                .align(Alignment.CenterVertically)
        )
    }
}