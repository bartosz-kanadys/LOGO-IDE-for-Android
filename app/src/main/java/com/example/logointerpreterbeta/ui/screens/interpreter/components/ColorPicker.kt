package com.example.logointerpreterbeta.ui.screens.interpreter.components

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColor
import com.example.logointerpreterbeta.R
import com.example.logointerpreterbeta.ui.theme.AppTypography
import com.github.skydoves.colorpicker.compose.AlphaSlider
import com.github.skydoves.colorpicker.compose.AlphaTile
import com.github.skydoves.colorpicker.compose.BrightnessSlider
import com.github.skydoves.colorpicker.compose.HsvColorPicker
import com.github.skydoves.colorpicker.compose.rememberColorPickerController


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
            .background(MaterialTheme.colorScheme.inversePrimary)
    ) {
        AlphaTile(
            controller = controller,
            modifier = Modifier
                .height(20.dp)
                .padding(top = 10.dp, start = 10.dp, end = 10.dp)
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

//        PickerButton(
//            text = "Wybierz", onClick = {
//                onSelectClick(controller.selectedColor.value)
//                //TurtleUI.penColor = controller.selectedColor.value.toArgb()
//              //  Log.i("ff", TurtleUI.penColor.toString())
//            })

        PickerButton(
            text = stringResource(R.string.copy),
            onClick = {
                onSelectClick()
                val clipboard =
                    context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val color = controller.selectedColor.value.toArgb().toColor()

                val clip = ClipData.newPlainText(
                    "label",
                    "setpc [ ${(color.red() * 255).toInt()} ${(color.green() * 255).toInt()} ${(color.blue() * 255).toInt()} ]"
                )
                clipboard.setPrimaryClip(clip)
                Toast.makeText(context, R.string.copied_to_clipboard, Toast.LENGTH_SHORT).show()
            }
        )
    }
}

@Composable
fun PickerButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = { onClick() },
        contentPadding = PaddingValues(0.dp),
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
        modifier = Modifier
            .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
            .height(25.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = text,
            style = AppTypography.bodySmall,
            fontSize = 10.sp,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}
