package com.example.logointerpreterbeta.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Square
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.logointerpreterbeta.Turtle
import com.example.logointerpreterbeta.ui.theme.AppTypography

@Composable
fun TurtleInfo(onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable { onClick() }
            .width(200.dp)
            .background(
                MaterialTheme.colorScheme.secondary,
                shape = RoundedCornerShape(0.dp, 0.dp, 0.dp, 20.dp)
            )


    ) {
        Text(
            text = "Żółwik",
            style = AppTypography.bodySmall,
            color = MaterialTheme.colorScheme.onSecondary,
            modifier = Modifier.padding(5.dp)
        )
        Text(
            text = "X=${Turtle.Xposition - 1000}, Y=${(Turtle.Yposition - 1000) * -1}",
            style = AppTypography.bodySmall,
            color = MaterialTheme.colorScheme.onSecondary,
            modifier = Modifier.padding(5.dp)
        )
        Text(
            text = "Kąt = ${Turtle.direction}°",
            style = AppTypography.bodySmall,
            color = MaterialTheme.colorScheme.onSecondary,
            modifier = Modifier.padding(5.dp)
        )
        Text(
            text = "Ukryty = ${!Turtle.isShowed}",
            style = AppTypography.bodySmall,
            color = MaterialTheme.colorScheme.onSecondary,
            modifier = Modifier.padding(5.dp)
        )
        Text(
            text = "Opuszczony = ${Turtle.isDown}",
            style = AppTypography.bodySmall,
            color = MaterialTheme.colorScheme.onSecondary,
            modifier = Modifier.padding(5.dp)
        )
        Text(
            text = "Rozmiar = ${Turtle.penSize}",
            style = AppTypography.bodySmall,
            color = MaterialTheme.colorScheme.onSecondary,
            modifier = Modifier.padding(5.dp)
        )
        Row {
            Text(
                text = "Kolor",
                style = AppTypography.bodySmall,
                color = MaterialTheme.colorScheme.onSecondary,
                modifier = Modifier.padding(5.dp)
            )
            Icon(
                imageVector = Icons.Filled.Square,
                contentDescription = null,
                tint = Color(Turtle.penColor)
            )
        }
    }
}