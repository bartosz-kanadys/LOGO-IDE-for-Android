package com.example.logointerpreterbeta.ui.screens.interpreter.components

import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.logointerpreterbeta.R


@Composable
fun Alert(
    isVisible: Boolean,
    title: String,
    content: String,
    confirmButtonAction: () -> Unit,
    dismissButtonAction: () -> Unit,
    textField: @Composable (() -> Unit)? = null,
    @StringRes confirmButtonText: Int
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
                    Text(stringResource(confirmButtonText))
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        dismissButtonAction()
                    }
                ) {
                    Text(stringResource(R.string.back))
                }
            }
        )
    }
}