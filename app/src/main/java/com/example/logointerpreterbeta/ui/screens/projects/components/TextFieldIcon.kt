package com.example.logointerpreterbeta.ui.screens.projects.components


import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.example.logointerpreterbeta.R

@Composable
fun TextFieldIcon() {
    Icon(
        painter = painterResource(id = R.drawable.round_draw_24),
        contentDescription = null,
        tint = MaterialTheme.colorScheme.primary
    )
}