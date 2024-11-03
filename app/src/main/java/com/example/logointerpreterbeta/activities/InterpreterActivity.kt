package com.example.logointerpreterbeta.activities

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.annotation.RequiresApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.logointerpreterbeta.LogoInterpreter
import com.example.logointerpreterbeta.components.ErrorsList
import com.example.logointerpreterbeta.components.ImagePanel
import com.example.logointerpreterbeta.components.codeEditor.CodeEditor
import com.example.logointerpreterbeta.ui.theme.LogoInterpreterBetaTheme
import com.example.logointerpreterbeta.viewModels.InterpreterViewModel

class InterpreterActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContent {
//            LogoInterpreterBetaTheme {
//                //InterpreterApp(viewModel = viewModel)
//            }
//        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun InterpreterApp(
    viewModel: InterpreterViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier
) {
    val isDark = isSystemInDarkTheme()
    var isDarkTheme by rememberSaveable { mutableStateOf(isDark) }

    LaunchedEffect(isDarkTheme) {
        viewModel.interpretCode()
        viewModel.interpretCode()
    }

    LazyColumn {
        item { ImagePanel() }
        item {
            ErrorsList(
                errors = viewModel.errors,
                isErrorListVisable = viewModel.isErrorListVisable,
                isErrorListExpanded = viewModel.isErrorListExpanded,
                onClick = { viewModel.toggleErrorListVisibility() }
            )
        }
        item {
            Box {
                CodeEditor(
                    codeState = viewModel.codeState,
                    onCodeChange = viewModel::onCodeChange,
                    errors = viewModel.errors,
                    modifier = Modifier
                )
                Button(
                    shape = CircleShape,
                    contentPadding = PaddingValues(0.dp),
                    colors = ButtonDefaults.buttonColors(
                        MaterialTheme.colorScheme.primaryContainer
                    ),
                    onClick = { viewModel.interpretCode() },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(top = 15.dp, end = 5.dp)
                        .width(45.dp)
                        .height(45.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.PlayArrow,
                        contentDescription = null,
                        modifier = Modifier
                            .width(40.dp)
                            .align(Alignment.CenterVertically)
                    )
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Preview() {
    LogoInterpreterBetaTheme(darkTheme = false) {
        InterpreterApp(viewModel = InterpreterViewModel(context = LocalContext.current))
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Preview2() {
    LogoInterpreterBetaTheme(darkTheme = true) {
        InterpreterApp()
    }
}