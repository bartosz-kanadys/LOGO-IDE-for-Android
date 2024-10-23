package com.example.logointerpreterbeta

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.logointerpreterbeta.errors.SyntaxError
import com.example.logointerpreterbeta.ui.theme.LogoInterpreterBetaTheme
import kotlin.math.roundToInt


class MainAppActivity : ComponentActivity() {
    @SuppressLint("MutableCollectionMutableState")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val logo = LogoInterpreter()

        setContent {
            LogoInterpreterBetaTheme {
//                val configuration = LocalConfiguration.current
//                val screenWidth = configuration.screenWidthDp.dp
//                val pxValue = with(LocalDensity.current) { screenWidth.toPx() }

                var codeState by rememberSaveable { mutableStateOf("") }
                var img by rememberSaveable {
                    mutableStateOf(Bitmap.createBitmap(2000, 2000, Bitmap.Config.ARGB_8888))
                }
                var errors by rememberSaveable {
                    mutableStateOf(SyntaxError.errors.toString())
                }
                LazyColumn {
                    item { ImagePanel(img = img) }
                    item { ErrorsList(errors = errors) }
                    item {
                        Box {
                            CodeEditor(codeState = codeState, onCodeChange = { newCode ->
                                codeState = newCode
                            }, modifier = Modifier)
                            Button(
                                shape = RectangleShape,
                                colors = ButtonDefaults.buttonColors(
                                    Color(
                                        red = 19,
                                        green = 128,
                                        blue = 16
                                    )
                                ),
                                onClick = {
                                    SyntaxError.errors.clear()
                                    Turtle.setAcctualPosition(1000F, 1000F)
                                    Turtle.direction = 0f
                                    try {
                                        logo.start(codeState)
                                        img = logo.bitmap

                                    } catch (e: Exception) {
                                        Log.e("ERROR", "Błąd wykonywania interpretera")
                                    } finally {
                                        errors = SyntaxError.errors.toString()
                                    }
                                },
                                modifier = Modifier
                                    .align(Alignment.BottomEnd)
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
}

val OffsetSaver = Saver<Offset, List<Float>>(
    save = { listOf(it.x, it.y) },
    restore = { Offset(it[0], it[1]) }
)

@Composable
fun ImagePanel(
    img: Bitmap,
) {
    var scale by rememberSaveable { mutableFloatStateOf(2f) }
    var offset by rememberSaveable(stateSaver = OffsetSaver) { mutableStateOf(Offset.Zero) }
    var isBlocked by rememberSaveable { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxHeight()
            .pointerInput(Unit) {
                detectTransformGestures { _, pan, zoom, _ ->
                    scale *= zoom
                    if (!isBlocked) {
                        offset = Offset(
                            x = offset.x + pan.x,
                            y = offset.y + pan.y
                        )
                    }
                }
            }
    ) {
        Image(
            bitmap = img.asImageBitmap(),
            contentDescription = null,
            // contentScale = ContentScale.Fit,
            modifier = Modifier
                .offset { IntOffset(offset.x.roundToInt(), offset.y.roundToInt()) }
                .scale(scale)
        )
        Column(
            modifier = Modifier
                .align(Alignment.BottomEnd)
        ) {
            ImageButton(s = "+") { scale += 0.2f }
            ImageButton(s = "-") { scale -= 0.2f }
            ImageButton(s = "b") {isBlocked = !isBlocked}
            ImageButton(s = "c") { offset = Offset.Zero }
        }
    }
}

@Composable
fun ErrorsList(errors: String) {
    val errorsList = if (errors.isNotEmpty()) {
        errors.removePrefix("[")
            .removeSuffix("]")
            .split(",")
            .toList()
    } else listOf(":)")
    LazyColumn(
        modifier = Modifier
            .height(50.dp)
            .fillMaxHeight()
    ) {
        items(errorsList) { error ->
            Text(
                text = error.trim(),
                color = Color.Red,
                fontSize = 10.sp,
                modifier = Modifier
                    .height(20.dp)
                    .background(Color.Gray)
                    .fillMaxWidth()
            )
        }
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
        )
    }
}

@Composable
fun ImageButton(s: String, onClick: () -> Unit) {
    Button(
        shape = RectangleShape,

        onClick = { onClick() },
        modifier = Modifier
            .width(50.dp)
            .height(40.dp)
            .padding(0.dp)

    ) {
        Text(
            text = s,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier

        )
    }
}
