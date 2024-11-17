package com.example.logointerpreterbeta.ui.screens.tutorialScreen

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.logointerpreterbeta.ui.theme.LogoInterpreterBetaTheme

@Composable
fun TutorialContentScreen(content: List<String>, modifier: Modifier) {
    LazyColumn(
        modifier = modifier
            .padding(20.dp)
    ) {
        for (paragraph in content) {
            item { Text(text = paragraph+"\n", textAlign = TextAlign.Justify) }
        }

    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun TutorialContentScreenPreview() {
    LogoInterpreterBetaTheme {
        val introduce = "W języku Logo podstawowe instrukcje takie jak FD, BK, RT, i LT pozwalają na precyzyjne sterowanie ruchem żółwia na ekranie. Możesz je używać w różnych kombinacjach, aby rysować różne kształty, takie jak kwadraty, trójkąty, czy bardziej złożone figury. Programowanie w Logo jest doskonałym wprowadzeniem do logicznego myślenia, a także zabawą, ponieważ możesz obserwować bezpośrednio efekty swoich działań na ekranie."
        val p1 = "Instrukcja FD (skrót od 'forward') pozwala na przesunięcie żółwia o określoną liczbę kroków do przodu w kierunku, w którym obecnie jest zwrócony."
        val p2 = "Instrukcja BK (skrót od 'back') działa odwrotnie do FD, czyli przesuwa żółwia do tyłu o określoną liczbę kroków."
        val p3 = "Instrukcja RT (skrót od 'right turn') powoduje, że żółw obraca się o określoną liczbę stopni w prawo (zgodnie z ruchem wskazówek zegara)."
        val p4 = "Instrukcja LT (skrót od 'left turn') działa odwrotnie do RT – żółw obraca się o określoną liczbę stopni w lewo (przeciwnie do ruchu wskazówek zegara)."

        val content = listOf(introduce, p1, p2, p3, p4)
        TutorialContentScreen(content, modifier = Modifier)
    }
}