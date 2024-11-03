package com.example.logointerpreterbeta.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.logointerpreterbeta.activities.layout.TopBarWithMenu
import com.example.logointerpreterbeta.ui.theme.AppTypography
import com.example.logointerpreterbeta.ui.theme.LogoInterpreterBetaTheme


class ProjectsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LogoInterpreterBetaTheme {
                Scaffold(
                    topBar = {
                        TopBarWithMenu("Projekty")
                    }
                ) { innerPadding ->
                    ProjectsApp(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun ProjectsApp(modifier: Modifier = Modifier) {
    Surface(
        modifier = Modifier.fillMaxHeight()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(top = 40.dp)
                .then(modifier)
        ) {
            Text(
                text = "Projects",
                fontSize = 34.sp,
                fontWeight = FontWeight.Bold,
                style = AppTypography.bodySmall,
                modifier = Modifier.padding(bottom = 12.dp)
            )

        }
    }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun ProjectsPreview() {
    LogoInterpreterBetaTheme {
        Scaffold(
            topBar = {
                TopBarWithMenu("Projekty")
            }
        ) { innerPadding ->
            ProjectsApp(Modifier.padding(innerPadding))
        }

    }
}