package com.example.logointerpreterbeta.activities

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
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.logointerpreterbeta.LogoInterpreter
import com.example.logointerpreterbeta.MyImageHeight
import com.example.logointerpreterbeta.MyImageWidth
import com.example.logointerpreterbeta.R
import com.example.logointerpreterbeta.Turtle
import com.example.logointerpreterbeta.errors.SyntaxError
import com.example.logointerpreterbeta.ui.theme.LogoInterpreterBetaTheme
import kotlin.math.roundToInt


class MainAppActivity : ComponentActivity() {
    @SuppressLint("MutableCollectionMutableState")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            LogoInterpreterBetaTheme {
                InterpreterApp()
            }
        }
    }
}

@Composable
fun InterpreterApp() {
    val logo = LogoInterpreter(LocalContext.current)
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
    Turtle.setAcctualPosition(MyImageWidth.toFloat() / 2, MyImageHeight.toFloat() / 2)
    Turtle.direction = 0f
    logo.start("st")
    img = logo.bitmap
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
                        Turtle.setAcctualPosition(
                            MyImageWidth.toFloat() / 2,
                            MyImageHeight.toFloat() / 2
                        )
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
            ImageButton(R.drawable.plus) { scale += 0.2f }
            ImageButton(R.drawable.minus) { scale -= 0.2f }
            if (isBlocked) {
                ImageButton(R.drawable.locked) { isBlocked = !isBlocked }
            } else {
                ImageButton(R.drawable.unlocked) { isBlocked = !isBlocked }
            }
            ImageButton(R.drawable.center) { offset = Offset.Zero }
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
            .height(20.dp)
        //.fillMaxHeight()
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
fun ImageButton(icon: Int, onClick: () -> Unit) {
    Button(
        colors = ButtonDefaults.buttonColors(Color.Black.copy(0f)),
        shape = RectangleShape,
        contentPadding = PaddingValues(0.dp),
        onClick = { onClick() },
        modifier = Modifier
            .width(40.dp)
            .height(40.dp)
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier
                .width(50.dp)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Preview() {
    LogoInterpreterBetaTheme {
        InterpreterApp()
    }
}