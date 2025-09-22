package com.example.logointerpreterbeta.ui.screens.start.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.logointerpreterbeta.R
import com.example.logointerpreterbeta.ui.theme.AppTypography

@Composable
fun AppLogo(modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = stringResource(R.string.logo_description),
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .size(180.dp)
        )
        Text(
            text = stringResource(R.string.app_name_short),
            fontSize = 34.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface,
            style = AppTypography.bodySmall,
            modifier = Modifier.padding(bottom = 12.dp)
        )
    }

}