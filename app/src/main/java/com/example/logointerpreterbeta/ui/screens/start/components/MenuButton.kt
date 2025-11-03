package com.example.logointerpreterbeta.ui.screens.start.components

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.logointerpreterbeta.ui.theme.AppTypography

@Composable
fun MenuButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.inversePrimary),
        modifier = Modifier
            .size(width = 280.dp, height = 60.dp)
            .shadow(5.dp, RoundedCornerShape(12.dp))
    ) {
        Text(
            text = text,
            style = AppTypography.bodyLarge.copy(
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center
            ),
            modifier = modifier
        )
    }
}