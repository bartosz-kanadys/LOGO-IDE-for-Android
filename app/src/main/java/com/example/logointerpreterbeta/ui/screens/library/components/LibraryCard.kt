package com.example.logointerpreterbeta.ui.screens.library.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.logointerpreterbeta.R
import com.example.logointerpreterbeta.ui.theme.AppTypography

@Composable
fun LibraryCard(
    libraryName: String,
    libraryDescription: String,
    procedureCount: Int,
    author: String,
    onClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.inversePrimary,
            contentColor = MaterialTheme.colorScheme.onSurface
        ),
        modifier = Modifier
            .height(160.dp)
            .clickable { onClick() }
    ) {
        Box(
            modifier = Modifier
                .padding(10.dp)
        ) {
            Column(
                Modifier.fillMaxSize()
            ) {
                Text(
                    text = libraryName,
                    textAlign = TextAlign.Center,
                    fontSize = 30.sp,
                    style = AppTypography.bodySmall,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = libraryDescription,
                    style = AppTypography.bodySmall,
                    fontSize = 10.sp,
                    modifier = Modifier.height(55.dp)
                )
                Spacer(Modifier.height(5.dp))
                Row(
                    modifier = Modifier.height(14.dp)
                ) {
                    Icon(painter = painterResource(R.drawable.round_bookmarks_24), contentDescription = null)
                    Text(
                        text = procedureCount.toString(),
                        style = AppTypography.bodySmall,
                        modifier = Modifier.padding(start = 5.dp)
                    )
                }
                Spacer(Modifier.height(5.dp))
                Row(
                    modifier = Modifier
                        .height(14.dp)
                ) {
                    Icon(imageVector = Icons.Filled.AccountCircle, contentDescription = null)
                    Text(
                        text = author,
                        style = AppTypography.bodySmall,
                        modifier = Modifier.padding(start = 5.dp)
                    )
                }
            }
            TextButton(
                onClick = {
                    onDeleteClick()
                },
                contentPadding = PaddingValues(0.dp),
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .size(25.dp)
                    .padding(0.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier
                        .size(20.dp)
                )
            }
        }
    }
}