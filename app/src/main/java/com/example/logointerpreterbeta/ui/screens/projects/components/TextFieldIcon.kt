package com.example.logointerpreterbeta.ui.screens.projects.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Draw
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun TextFieldIcon() {
    Icon(
        imageVector = Icons.Filled.Draw,
        contentDescription = null,
        tint = MaterialTheme.colorScheme.primary
    )
}