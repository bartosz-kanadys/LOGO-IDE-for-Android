package com.example.logointerpreterbeta.activities

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColor
import com.example.logointerpreterbeta.R
import com.example.logointerpreterbeta.Turtle
import com.example.logointerpreterbeta.components.CodeEditor.CodeEditor
import com.example.logointerpreterbeta.ui.theme.LogoInterpreterBetaTheme
import com.example.logointerpreterbeta.ui.theme.jetBrainsMono
import com.github.skydoves.colorpicker.compose.AlphaSlider
import com.github.skydoves.colorpicker.compose.AlphaTile
import com.github.skydoves.colorpicker.compose.BrightnessSlider
import com.github.skydoves.colorpicker.compose.HsvColorPicker
import com.github.skydoves.colorpicker.compose.rememberColorPickerController
import kotlin.math.roundToInt

class MainAppActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LogoInterpreterBetaTheme {
                //InterpreterApp(viewModel = viewModel)
            }
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun InterpreterApp(
    viewModel: InterpreterViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    modifier: Modifier = Modifier
) {
    LazyColumn {
        item { ImagePanel(img = viewModel.img) }
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
                    colors = ButtonDefaults.buttonColors(Color(0xFF4CAF50)),
                    onClick = { viewModel.interpretCode() },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(top = 5.dp, end = 5.dp)
                        .width(45.dp)
                        .height(45.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.play_icon),
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


val OffsetSaver = Saver<Offset, List<Float>>(
    save = { listOf(it.x, it.y) },
    restore = { Offset(it[0], it[1]) }
)

fun prepareErrorList(errorList: MutableList<String>): MutableMap<Int, String> {
    val errorMap = mutableMapOf<Int, String>()
    if (errorList.isNotEmpty()) {
        for (error in errorList) {
            val lineRegex = """line (\d+):""".toRegex()
            val matchResult = lineRegex.find(error)
            if (matchResult != null) {
                val lineNumber = matchResult.groupValues[1].toInt()
                errorMap[lineNumber] = error
            } else {
                errorMap[-1] = "Invalid format in error message: $error"
            }
        }
    } else {
        errorMap[-1] = "Succes"
    }
    return errorMap
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ImagePanel(
    img: Bitmap,
) {
    var scale by rememberSaveable { mutableFloatStateOf(2f) }
    var offset by rememberSaveable(stateSaver = OffsetSaver) { mutableStateOf(Offset.Zero) }
    var isBlocked by rememberSaveable { mutableStateOf(false) }
    var isPickerVisable by rememberSaveable { mutableStateOf(false) }

    Box(
        modifier = Modifier
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
            modifier = Modifier
                .offset { IntOffset(offset.x.roundToInt(), offset.y.roundToInt()) }
                .scale(scale)
        )
        AnimatedVisibility(visible = isPickerVisable, modifier = Modifier.align(Alignment.Center)) {
            ColorPicker(
                initialColor = Turtle.penColor,
                onSelectClick = { isPickerVisable = !isPickerVisable },
                context = LocalContext.current
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                // .height(390.dp)
                .align(Alignment.BottomEnd)
                .padding(end = 3.dp)
        ) {
            ImageButton(R.drawable.color, modifier = Modifier.width(30.dp)) {
                isPickerVisable = !isPickerVisable
            }
            Spacer(modifier = Modifier.height(170.dp))
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
fun ErrorsList(
    errors: String,
    isErrorListExpanded: Boolean,
    isErrorListVisable: Boolean,
    onClick: () -> Unit
) {
    val errorsList = if (errors.isNotEmpty()) {
        errors.removePrefix("[")
            .removeSuffix("]")
            .split(",")
            .toList()
    } else listOf(":)")

    AnimatedVisibility(isErrorListVisable) {

        Column {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .height(32.dp)
                    .clickable { onClick() }
                    .background(Color(0xFFC8E6C9))
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Wykryto ${errorsList.size} błędy/ów z kodem!",
                    fontFamily = jetBrainsMono,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                )
                Icon(
                    painter = painterResource(if (isErrorListExpanded) R.drawable.up else R.drawable.down),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .width(30.dp)
                )
            }
            AnimatedVisibility(isErrorListExpanded) {
                LazyColumn(
                    modifier = Modifier
                        .height(50.dp)
                        .background(Color(0xFFC8E6C9))
                ) {
                    items(errorsList) { error ->
                        Text(
                            text = error.trim(),
                            color = Color.Red,
                            fontFamily = jetBrainsMono,
                            fontSize = 10.sp,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(horizontal = 10.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ImageButton(icon: Int, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Button(
        colors = ButtonDefaults.buttonColors(Color.Black.copy(0f)),
        shape = RectangleShape,
        contentPadding = PaddingValues(0.dp),
        onClick = { onClick() },
        modifier = modifier
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

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("ServiceCast")
@Composable
fun ColorPicker(initialColor: Int, onSelectClick: () -> Unit, context: Context) {
    val controller = rememberColorPickerController()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth(0.5f)
            .padding(8.dp)
            .clip(shape = RoundedCornerShape(10.dp))
            .background(Color(0xFFC8E6C9))

    ) {
        AlphaTile(
            controller = controller,
            modifier = Modifier
                .height(20.dp)
                .padding(top = 5.dp, start = 10.dp, end = 10.dp)
                .width(200.dp)
                .clip(shape = RoundedCornerShape(10.dp))
        )
        HsvColorPicker(
            initialColor = Color(initialColor),
            controller = controller,
            modifier = Modifier
                .size(160.dp)
                .padding(horizontal = 8.dp)

        )
        AlphaSlider(
            controller = controller,
            modifier = Modifier
                .height(15.dp)
                .padding(horizontal = 10.dp)
                .width(200.dp)
                .clip(shape = RoundedCornerShape(8.dp))
        )
        Spacer(modifier = Modifier.height(5.dp))
        BrightnessSlider(
            controller = controller,
            modifier = Modifier
                .height(15.dp)
                .padding(horizontal = 10.dp)
                .width(200.dp)
                .clip(shape = RoundedCornerShape(8.dp))
        )
        Spacer(modifier = Modifier.height(10.dp))

        PickerButton(
            text = "Wybierz", onClick = {
                onSelectClick()
                Turtle.penColor = controller.selectedColor.value.toArgb()
                Log.i("ff", Turtle.penColor.toString())
            })

        PickerButton(
            text = "Kopiuj",
            onClick = {
                val clipboard =
                    context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val color = controller.selectedColor.value.toArgb().toColor()

                val clip = ClipData.newPlainText(
                    "label",
                    "setpc [ ${(color.red() * 255).toInt()} ${(color.green() * 255).toInt()} ${(color.blue() * 255).toInt()} ]"
                )
                clipboard.setPrimaryClip(clip)
                Toast.makeText(context, "Tekst skopiowany do schowka", Toast.LENGTH_SHORT).show()
            }
        )
    }
}

@Composable
fun PickerButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = { onClick() },
        contentPadding = PaddingValues(0.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)),
        modifier = Modifier
            .padding(bottom = 5.dp, start = 10.dp, end = 10.dp)
            .height(25.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = text,
            fontFamily = jetBrainsMono,
            fontSize = 10.sp,
            color = Color.White
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Preview() {
    LogoInterpreterBetaTheme {
        //InterpreterApp()
    }
}