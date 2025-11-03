package com.example.logointerpreterbeta.ui.screens.interpreter.components

import androidx.annotation.StringRes
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.logointerpreterbeta.R
import com.example.logointerpreterbeta.ui.models.TurtleUI
import com.example.logointerpreterbeta.ui.theme.AppTypography

@Composable
fun TurtleInfo(
    turtleState: TurtleUI,
    onClick: () -> Unit
) {
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
            text = stringResource(R.string.turtle),
            style = AppTypography.bodySmall,
            color = MaterialTheme.colorScheme.onSecondary,
            modifier = Modifier.padding(5.dp)
        )
        Text(
            text = "X=${turtleState.xPosition}, Y=${(turtleState.yPosition) }",
            style = AppTypography.bodySmall,
            color = MaterialTheme.colorScheme.onSecondary,
            modifier = Modifier.padding(5.dp)
        )
        TurtleInfoText(R.string.turtle_info_angle, turtleState.direction.toString())
        TurtleInfoText(R.string.turtle_info_visible, turtleState.isVisible.toString())
        TurtleInfoText(R.string.turtle_info_pen_down, turtleState.isPenDown.toString())
        TurtleInfoText(R.string.turtle_info_size, turtleState.penState.size.toString())
        Row {
            Text(
                text = stringResource(R.string.turtle_info_color),
                style = AppTypography.bodySmall,
                color = MaterialTheme.colorScheme.onSecondary,
                modifier = Modifier.padding(5.dp)
            )
            Icon(
                imageVector = Icons.Filled.Square,
                contentDescription = null,
                tint = Color(turtleState.penState.color)
            )
        }
    }
}

@Composable
fun TurtleInfoText(
    @StringRes text: Int,
    formatArgs: String
) {
    Text(
        text = stringResource(text, formatArgs),
        style = AppTypography.bodySmall,
        color = MaterialTheme.colorScheme.onSecondary,
        modifier = Modifier.padding(5.dp)
    )
}