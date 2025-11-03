package com.example.logointerpreterbeta.ui.screens.tutorial

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
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.logointerpreterbeta.R
import com.example.logointerpreterbeta.ui.utils.LogoTextColorizer
import com.example.logointerpreterbeta.domain.models.readTutorialsFromRaw
import com.example.logointerpreterbeta.ui.screens.interpreter.components.codeEditor.DisplayOnlyCodeEditor
import com.example.logointerpreterbeta.ui.theme.LogoInterpreterBetaTheme

@Composable
fun TutorialContentScreen(tutorialName: String, modifier: Modifier) {
    val context = LocalContext.current
    val tutorial = remember(context, tutorialName) {
        readTutorialsFromRaw(context).find { it.name == tutorialName }
    }

    if (tutorial == null) {
        Text(
            text = stringResource(R.string.tutorial_not_found),
            style = MaterialTheme.typography.bodyMedium,
            modifier = modifier.padding(15.dp)
        )
    } else {
        LazyColumn(
            modifier = modifier
                .padding(15.dp)
        ) {
            for (paragraph in tutorial.paragraphs) {
                val coloredCode = LogoTextColorizer.colorizeText(paragraph.code, false)
                item {
                    Text(text = paragraph.content + "\n", textAlign = TextAlign.Justify)
                    DisplayOnlyCodeEditor(
                        codeState = TextFieldValue(coloredCode),
                    )
                    Spacer(Modifier.height(5.dp))
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                        Button(
                            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.inversePrimary),
                            onClick = {
                                copyToClipboard(context, context.getString(R.string.example_code), paragraph.code)
                            },
                        ) {
                            Text(text = stringResource(R.string.copy_code), color = MaterialTheme.colorScheme.onSurface)
                        }
                    }
                    Spacer(Modifier.height(15.dp))
                }
            }
        }
    }
}

fun copyToClipboard(context: Context, label: String, text: String) {
    val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    clipboard.setPrimaryClip(ClipData.newPlainText(label, text))
    Toast.makeText(context, context.getString(R.string.copied_to_clipboard), Toast.LENGTH_SHORT).show()
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun TutorialContentScreenPreview() {
    LogoInterpreterBetaTheme {
        TutorialContentScreen("Loops", modifier = Modifier)
    }
}