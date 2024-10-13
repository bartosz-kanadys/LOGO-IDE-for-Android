package com.example.logointerpreterbeta

import android.graphics.Bitmap
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.logointerpreterbeta.ui.theme.LogoInterpreterBetaTheme


class MainAppActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val logo = LogoInterpreter()

        setContent {
            LogoInterpreterBetaTheme {
                val configuration = LocalConfiguration.current
                val screenWidth = configuration.screenWidthDp.dp
                val pxValue = with(LocalDensity.current) {screenWidth.toPx() }

                var codeState by remember { mutableStateOf("") }
                var img by remember {
                    mutableStateOf(Bitmap.createBitmap(pxValue.toInt(),1000,Bitmap.Config.ARGB_8888))
                }
                Column {
                    GeneratedImage(img = img, modifier = Modifier)
                    Box {
                        CodeEditor(codeState = codeState, onCodeChange = { newCode ->
                            codeState = newCode
                        }, modifier = Modifier)
                        Button(
                            shape = RectangleShape,
                            colors = ButtonDefaults.buttonColors(Color(red = 19, green = 128, blue = 16)),
                            onClick = {
                                Turtle.setAcctualPosition(500F,500F)
                                Turtle.direction = 0
                                logo.start(codeState)
                                img = logo.bitmap!!
                            },
                            modifier = Modifier.align(Alignment.BottomEnd)
                                .padding(end = 5.dp, bottom = 1.dp)

                        ) {
                            Text(text = "Wykonaj")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun GeneratedImage(img: Bitmap, modifier: Modifier) {
    Column {
        Image(
            bitmap = img.asImageBitmap(),
            contentDescription = null,
            modifier = modifier
        )
    }
}

@Composable
fun CodeEditor(codeState: String, onCodeChange: (String) -> Unit, modifier: Modifier) {
    Box {
        TextField(
            value = codeState,
            onValueChange = { newValue -> onCodeChange(newValue) },
            placeholder = { Text(text = "Napisz kod LOGO") },
            modifier = modifier
                .fillMaxWidth()
                .height(300.dp)
                //.border(1.dp, Color.Black)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Preview() {
    LogoInterpreterBetaTheme {
    }
}