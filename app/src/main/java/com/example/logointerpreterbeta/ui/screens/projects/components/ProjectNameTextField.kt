package com.example.logointerpreterbeta.ui.screens.projects.components

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.logointerpreterbeta.R

@Composable
fun ProjectNameTextField(
    text: String,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = text,
        onValueChange = { newValue -> onValueChange(newValue) },
        leadingIcon = { TextFieldIcon() },
        label = { Text(text = stringResource(R.string.project_name)) },
        placeholder = { Text(text = stringResource(R.string.enter_name)) },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.secondary
        ),
        modifier = modifier
            .fillMaxWidth(0.9f)
            .fillMaxHeight()
            .testTag("EnterName"),
        shape = RoundedCornerShape(8.dp)
    )
}