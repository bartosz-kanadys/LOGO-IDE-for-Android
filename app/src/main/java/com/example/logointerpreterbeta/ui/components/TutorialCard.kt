package com.example.logointerpreterbeta.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.logointerpreterbeta.R
import com.example.logointerpreterbeta.ui.theme.LogoInterpreterBetaTheme


@Composable
fun TutorialCard(title: String, description: String, onClick: () -> Unit) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .border(
                2.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(12.dp)
            )
            .clickable { onClick() }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(20.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.turtle_tutorial),
                contentDescription = null,
                modifier = Modifier
                    .height(130.dp)
                    .fillMaxWidth()

            )
            Spacer(Modifier.height(15.dp))
            Box(
                contentAlignment = Alignment.Center
            ) {
                HorizontalDivider(thickness = 4.dp, color = MaterialTheme.colorScheme.primary)
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.surface)
                        .padding(horizontal = 20.dp)
                )

            }
            Text(
                text = description,
                textAlign = TextAlign.Justify,
                fontSize = 14.sp
            )
            Spacer(Modifier.height(10.dp))
//            HorizontalDivider(thickness = 4.dp, color = MaterialTheme.colorScheme.primary)
        }

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TutorialCardPreview() {
    LogoInterpreterBetaTheme {
        TutorialCard("ddddd", "ddddddddddddddddddddddd", {})
    }
}