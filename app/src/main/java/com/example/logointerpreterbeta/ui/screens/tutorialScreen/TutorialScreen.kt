package com.example.logointerpreterbeta.ui.screens.tutorialScreen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.logointerpreterbeta.Navigation.TutorialScreen
import com.example.logointerpreterbeta.readTutorialsFromRaw
import com.example.logointerpreterbeta.ui.components.TutorialCard
import com.example.logointerpreterbeta.ui.theme.LogoInterpreterBetaTheme

@Composable
fun TutorialScreen(modifier: Modifier) {
    val tutorials = readTutorialsFromRaw(LocalContext.current)
    LazyColumn(modifier = modifier) {
        items(tutorials) { tutorial ->
            TutorialCard(tutorial.name, tutorial.description)
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun TutorialScreenPreview() {
    LogoInterpreterBetaTheme {
        TutorialScreen(Modifier)
    }
}