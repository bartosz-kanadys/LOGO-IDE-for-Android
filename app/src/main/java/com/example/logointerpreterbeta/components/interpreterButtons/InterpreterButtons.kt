package com.example.logointerpreterbeta.components.interpreterButtons


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.logointerpreterbeta.viewModels.InterpreterViewModel


@Composable
fun InterpreterButtons(viewModel: InterpreterViewModel){
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 15.dp, end = 5.dp)
        ) {
            Button(
                shape = CircleShape,
                contentPadding = PaddingValues(0.dp),
                colors = ButtonDefaults.buttonColors(
                    MaterialTheme.colorScheme.primaryContainer
                ),
                onClick = { viewModel.interpretCode() },
                modifier = Modifier
                    .width(45.dp)
                    .height(45.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.PlayArrow,
                    contentDescription = null,
                    modifier = Modifier
                        .width(40.dp)
                        .align(Alignment.CenterVertically)
                )
            }

            // Nowy przycisk pod pierwszym
            Button(
                shape = CircleShape,
                contentPadding = PaddingValues(0.dp),
                colors = ButtonDefaults.buttonColors(
                    MaterialTheme.colorScheme.secondaryContainer
                ),
                onClick = { viewModel.enableDebugging() },
                modifier = Modifier
                    .padding(top = 10.dp) // Dodanie odstępu od pierwszego przycisku
                    .width(45.dp)
                    .height(45.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Call,
                    contentDescription = null,
                    modifier = Modifier
                        .width(40.dp)
                        .align(Alignment.CenterVertically)
                )
            }
            Button(
                shape = CircleShape,
                contentPadding = PaddingValues(0.dp),
                colors = ButtonDefaults.buttonColors(
                    MaterialTheme.colorScheme.secondaryContainer
                ),
                onClick = { viewModel.nextStep() },
                modifier = Modifier
                    .padding(top = 10.dp) // Dodanie odstępu od pierwszego przycisku
                    .width(45.dp)
                    .height(45.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = null,
                    modifier = Modifier
                        .width(40.dp)
                        .align(Alignment.CenterVertically)
                )
            }
        }
    }
}