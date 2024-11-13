package com.example.logointerpreterbeta.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun Alert(
    isVisible: Boolean,
    title: String,
    content: String,
    confirmButtonAction: () -> Unit,
    dismissButtonAction: () -> Unit,
    textField: @Composable (() -> Unit)? = null,
    confirmButtonText: String = "Dalej"
) {
    AnimatedVisibility(isVisible) {
        AlertDialog(
            title = {
                Text(text = title)
            },
            text = {
                Column {
                    Text(
                        text = content,
                        Modifier.padding(bottom = 10.dp)
                    )
                    if (textField != null) {
                        textField()
                    }
                }
            },
            onDismissRequest = {

            },
            confirmButton = {
                TextButton(
                    onClick = {
                        confirmButtonAction()
                    }
                ) {
                    Text(confirmButtonText)
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        dismissButtonAction()
                    }
                ) {
                    Text("Wstecz")
                }
            }
        )
    }
}