package com.example.logointerpreterbeta.ui.screens.library.components

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun FormButton(text: String, color: Color, onClick: () -> Unit) {
    Button(
        colors = ButtonDefaults.buttonColors(color),
        onClick = { onClick() }
    ) {
        Text(text = text)
    }
}