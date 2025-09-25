package com.example.logointerpreterbeta.ui.screens.projects.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProjectButton(
    name: String,
    date: String,
    onOpenProject: (String) -> Unit,
    onDelete: () -> Unit
) {
    OutlinedButton(
        onClick = {
            onOpenProject(name)
        },
        shape = RoundedCornerShape(8.dp),
        contentPadding = PaddingValues(0.dp),
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .height(80.dp)
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(start = 15.dp, end = 7.dp)
                .align(Alignment.CenterVertically)
        ) {
            Column(modifier = Modifier.weight(0.8f)) {
                Text(text = name, textAlign = TextAlign.Left, fontSize = 20.sp)
                HorizontalDivider(
                    thickness = 1.dp,
                    color = MaterialTheme.colorScheme.inversePrimary,
                )
                Text(text = date, textAlign = TextAlign.Right, fontSize = 10.sp)
            }
            TextButton(
                onClick = { onDelete() },
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Icon(imageVector = Icons.Filled.DeleteForever, contentDescription = null)
            }
        }
    }
}