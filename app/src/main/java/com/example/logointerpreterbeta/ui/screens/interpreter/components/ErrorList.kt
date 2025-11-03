package com.example.logointerpreterbeta.ui.screens.interpreter.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.logointerpreterbeta.R
import com.example.logointerpreterbeta.ui.theme.AppTypography


@Composable
fun ErrorsList(
    errors: String,
    isErrorListExpanded: Boolean,
    isErrorListVisible: Boolean,
    onClick: () -> Unit
) {
    val errorsList = if (errors.isNotEmpty()) {
        errors.removePrefix("[")
            .removeSuffix("]")
            .split(",")
            .toList()
    } else listOf(":)")

    AnimatedVisibility(isErrorListVisible) {

        Column {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .height(32.dp)
                    .clickable { onClick() }
                    .background(MaterialTheme.colorScheme.errorContainer)
                    .fillMaxWidth()
            ) {
                Text(
                    text = stringResource(R.string.errors_in_code, errorsList.size),
                    style = AppTypography.bodySmall,
                    modifier = Modifier
                        .align(Alignment.CenterVertically),
                    color = MaterialTheme.colorScheme.onErrorContainer
                )
                if (isErrorListExpanded) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_expand_less_24),

                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onErrorContainer,
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .width(30.dp)
                    )
                } else {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_expand_more_24),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onErrorContainer,
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .width(30.dp)
                    )
                }
            }
            AnimatedVisibility(isErrorListExpanded) {
                LazyColumn(
                    modifier = Modifier
                        .height(50.dp)
                        .background(MaterialTheme.colorScheme.error)
                ) {
                    items(errorsList) { error ->
                        Text(
                            text = error.trim(),
                            color = MaterialTheme.colorScheme.onError,
                            style = AppTypography.bodySmall,
                            fontSize = 10.sp,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(horizontal = 10.dp)
                        )
                    }
                }
            }
        }
    }
}