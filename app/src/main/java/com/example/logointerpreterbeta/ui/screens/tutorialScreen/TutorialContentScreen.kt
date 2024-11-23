package com.example.logointerpreterbeta.ui.screens.tutorialScreen

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.logointerpreterbeta.LogoInterpreter
import com.example.logointerpreterbeta.readTutorialsFromRaw
import com.example.logointerpreterbeta.ui.components.codeEditor.CodeEditor
import com.example.logointerpreterbeta.ui.theme.LogoInterpreterBetaTheme

@Composable
fun TutorialContentScreen(tutorialName: String, modifier: Modifier) {
    val context = LocalContext.current
    val tutorial = readTutorialsFromRaw(context).find { it.name == tutorialName }
    val logo = LogoInterpreter(context)

    LazyColumn(
        modifier = modifier
            .padding(15.dp)
    ) {
        for (paragraph in tutorial!!.paragraphs) {
            val coloredCode = logo.colorizeText(paragraph.code)
            item { Text(text = paragraph.content + "\n", textAlign = TextAlign.Justify) }
            item {
                val linesCount = paragraph.code.lines().size
                CodeEditor(
                    codeState = TextFieldValue(coloredCode),
                    isSaveOnChange = false,
                    isEnabled = false,
                    isScrollable = false,
                    lines = linesCount,
                    fontSize = 15,                                    //fontSize + 5
                    modifier = Modifier.height((30 + (linesCount - 1) * 20).dp)
                )
                Spacer(Modifier.height(5.dp))
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                    Button(
                        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.inversePrimary),
                        onClick = {
                            val clipboard =
                                context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

                            val clip = ClipData.newPlainText(
                                "przykladowyKod",
                                paragraph.code
                            )
                            clipboard.setPrimaryClip(clip)
                            Toast.makeText(context, "Kod skopiowany do schowka", Toast.LENGTH_SHORT)
                                .show()
                        },
                    ) {
                        Text(text = "Kopiuj kod", color = MaterialTheme.colorScheme.onSurface)
                    }
                }

                Spacer(Modifier.height(15.dp))
            }
        }

    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun TutorialContentScreenPreview() {
    LogoInterpreterBetaTheme {
        TutorialContentScreen("Pętle", modifier = Modifier)
    }
}