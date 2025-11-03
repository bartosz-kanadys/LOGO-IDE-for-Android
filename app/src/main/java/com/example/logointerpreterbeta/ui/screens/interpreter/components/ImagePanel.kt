package com.example.logointerpreterbeta.ui.screens.interpreter.components

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.DrawableRes
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.logointerpreterbeta.R
import com.example.logointerpreterbeta.ui.models.TurtleUI
import kotlin.math.roundToInt

val OffsetSaver = Saver<Offset, List<Float>>(
    save = { listOf(it.x, it.y) },
    restore = { Offset(it[0], it[1]) }
)

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ImagePanel(
    turtleState: TurtleUI,
    image: ImageBitmap,
    arrowImage: ImageBitmap,
) {
    var scale by rememberSaveable { mutableFloatStateOf(2f) }
    var offset by rememberSaveable(stateSaver = OffsetSaver) { mutableStateOf(Offset.Zero) }
    var isBlocked by rememberSaveable { mutableStateOf(false) }
    var isPickerVisible by rememberSaveable { mutableStateOf(false) }
    var isInfoVisible by rememberSaveable { mutableStateOf(false) }
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .pointerInput(Unit) {
                    detectTransformGestures { _, pan, zoom, _ ->
                        if (scale in 0.19999985..11.8) {
                            scale *= zoom
                        }
                        if (!isBlocked) {
                            offset = Offset(
                                x = offset.x + pan.x,
                                y = offset.y + pan.y
                            )
                        }
                    }
                }
                .fillMaxSize()
        ) {
            Image(
                bitmap = image,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .offset { IntOffset(offset.x.roundToInt(), offset.y.roundToInt()) }
                    .scale(scale)
            )
            Image(
                bitmap = arrowImage,
                contentDescription = "Empty Bitmap",
                modifier = Modifier
                    .fillMaxSize()
                    .offset { IntOffset(offset.x.roundToInt(), offset.y.roundToInt()) }
                    .scale(scale)
            )
        }
            AnimatedVisibility(
                visible = isPickerVisible,
                modifier = Modifier.align(Alignment.Center)
            ) {
                ColorPicker(
                    initialColor = turtleState.penState.color,
                    onSelectClick = {
                        isPickerVisible = !isPickerVisible
                    },
                    context = LocalContext.current
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    // .height(390.dp)
                    .align(Alignment.BottomEnd)
                    .padding(end = 3.dp, bottom = 40.dp)
            ) {
                ImageButton(R.drawable.baseline_info_24) { isInfoVisible = !isInfoVisible }
                ImageButton(R.drawable.baseline_palette_24) { isPickerVisible = !isPickerVisible }
                Spacer(modifier = Modifier.weight(1f))
                ImageButton(R.drawable.outline_zoom_in_24) { if (scale + 0.2f in 0.19999985..11.8) scale += 0.2f }
                ImageButton(R.drawable.outline_zoom_out_24) { if (scale - 0.2f in 0.19999985..11.8) scale -= 0.2f }
                if (isBlocked) {
                    ImageButton(R.drawable.outline_lock_24) { isBlocked = !isBlocked }
                } else {
                    ImageButton(R.drawable.outline_lock_open_right_24) { isBlocked = !isBlocked }
                }
                ImageButton(R.drawable.baseline_center_focus_strong_24) { offset = Offset.Zero }
            }
            AnimatedVisibility(
                visible = isInfoVisible,
                modifier = Modifier.align(Alignment.TopEnd)
            ) {
                TurtleInfo(
                    turtleState = turtleState,
                ) {
                    isInfoVisible = !isInfoVisible
                }
            }
        }

}


@Composable
fun ImageButton(
    @DrawableRes icon: Int,
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
            painter = painterResource(icon),
            contentDescription = null,
            tint = tint,
            modifier = Modifier
                .width(50.dp),
        )
    }
}

//@RequiresApi(Build.VERSION_CODES.O)
//@Preview
//@Composable
//fun ImagePanelPreview() {
//    LogoInterpreterBetaTheme {
//        ImagePanel()
//    }
//}