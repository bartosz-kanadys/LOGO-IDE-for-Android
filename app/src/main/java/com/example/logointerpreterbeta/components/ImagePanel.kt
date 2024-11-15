package com.example.logointerpreterbeta.components

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CenterFocusStrong
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.LockOpen
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.ZoomIn
import androidx.compose.material.icons.filled.ZoomOut
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.logointerpreterbeta.MyImage
import com.example.logointerpreterbeta.MyImageHeight
import com.example.logointerpreterbeta.MyImageWidth
import com.example.logointerpreterbeta.MyLogoVisitor
import com.example.logointerpreterbeta.Turtle
import com.example.logointerpreterbeta.ui.theme.LogoInterpreterBetaTheme
import kotlin.math.roundToInt


val OffsetSaver = Saver<Offset, List<Float>>(
    save = { listOf(it.x, it.y) },
    restore = { Offset(it[0], it[1]) }
)

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ImagePanel() {
    var scale by rememberSaveable { mutableFloatStateOf(2f) }
    var offset by rememberSaveable(stateSaver = OffsetSaver) { mutableStateOf(Offset.Zero) }
    var isBlocked by rememberSaveable { mutableStateOf(false) }
    var isPickerVisable by rememberSaveable { mutableStateOf(false) }
    var isInfoVisable by rememberSaveable { mutableStateOf(false) }
    var isDarkTheme by rememberSaveable { mutableStateOf(false) }
    isDarkTheme = isSystemInDarkTheme()
    val emptyBitmap = Bitmap.createBitmap(MyImageWidth, MyImageHeight, Bitmap.Config.ARGB_4444)
    emptyBitmap.eraseColor(android.graphics.Color.TRANSPARENT)
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
            bitmap = MyLogoVisitor.image.asImageBitmap(),
            contentDescription = null,
            modifier = Modifier
                .offset { IntOffset(offset.x.roundToInt(), offset.y.roundToInt()) }
                .scale(scale)
        )
        Image(
            bitmap = MyLogoVisitor.turtleImage.asImageBitmap(),
            contentDescription = "Empty Bitmap",
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
                .padding(end = 3.dp, bottom = 10.dp)
        ) {
            ImageButton(Icons.Filled.Info) { isInfoVisable = !isInfoVisable }
            ImageButton(Icons.Filled.Palette) { isPickerVisable = !isPickerVisable }
            Spacer(modifier = Modifier.height(170.dp))
            ImageButton(Icons.Filled.ZoomIn) { scale += 0.2f }
            ImageButton(Icons.Filled.ZoomOut) { scale -= 0.2f }
            if (isBlocked) {
                ImageButton(Icons.Filled.Lock) { isBlocked = !isBlocked }
            } else {
                ImageButton(Icons.Filled.LockOpen) { isBlocked = !isBlocked }
            }
            ImageButton(Icons.Filled.CenterFocusStrong) { offset = Offset.Zero }
        }
        AnimatedVisibility(visible = isInfoVisable, modifier = Modifier.align(Alignment.TopEnd)) {
            TurtleInfo {
                isInfoVisable = !isInfoVisable
            }
        }

//        val logo = LogoInterpreter(LocalContext.current)
//        if (isDarkTheme) {
//            logo.start("st home")
//        } else {
//            logo.start("st home")
//        }
    }
}


@Composable
fun ImageButton(
    icon: ImageVector,
    tint: Color = MaterialTheme.colorScheme.onSurface,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    IconButton(
        onClick = { onClick() },
        colors = IconButtonDefaults.iconButtonColors(Color.Black.copy(0f)),
        modifier = modifier
            .width(40.dp)
            .height(40.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = tint,
            modifier = Modifier
                .width(50.dp)
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun ImagePanelPreview() {
    LogoInterpreterBetaTheme {
        ImagePanel()
    }
}