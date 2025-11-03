package com.example.logointerpreterbeta.ui.screens.projects.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp

@Composable
fun CreateProjectButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        onClick = { onClick() },
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
            .fillMaxSize()
            .padding(start = 5.dp, top = 5.5.dp)
            .testTag("AddProjectButton")
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onPrimary
        )
    }
}