package com.example.logointerpreterbeta

import android.graphics.Bitmap
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.logointerpreterbeta.ui.theme.LogoInterpreterBetaTheme

class MainAppActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LogoInterpreterBetaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    var text by remember { mutableStateOf("") }

    var img by remember {
        mutableStateOf(Bitmap.createBitmap(1000,1000,Bitmap.Config.ARGB_8888))
    }
    //var img: Bitmap = Bitmap.createBitmap(1000, 1000, Bitmap.Config.ARGB_8888)


    var logo = LogoInterpreter()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()



    ) {
        Text(
            text = "LOGO Interpreter",
            modifier = modifier
        )
        Image(
            bitmap = img.asImageBitmap(),
            contentDescription = null,
            modifier = modifier
                .border(1.dp, Color.Black)
        )
        TextField(
            value = text,
            onValueChange = { text = it},
            placeholder = { Text(text = "Napisz kod LOGO")},
            modifier = modifier
                .fillMaxWidth()
                .padding(15.dp)
                .height(300.dp)
                .border(1.dp, Color.Black)
        )
        Button(
            onClick = {
                logo.start(text)
                img = logo.bitmap!!
            }
        ) {
            Text(text = "Wykonaj")
        }
    }
    }



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    LogoInterpreterBetaTheme {
        Greeting("Android")
    }
}