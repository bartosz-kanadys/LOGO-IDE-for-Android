package com.example.logointerpreterbeta

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.logointerpreterbeta.ui.theme.LogoInterpreterBetaTheme
import com.example.logointerpreterbeta.ui.theme.jetBrainsMono

class StartScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LogoInterpreterBetaTheme (
            ){
                Surface(
                    modifier = Modifier.fillMaxHeight()
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .padding(top = 40.dp)
                    ) {
                        Image(
                            painterResource(id = R.drawable.logo),
                            contentDescription = "logo",
                            contentScale = ContentScale.FillWidth,
                            modifier = Modifier
                                .size(180.dp)
                            //.padding(top = 40.dp)
                        )
                        Text(
                            text = "LOGO IDE",
                            fontSize = 34.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = jetBrainsMono,
                            modifier = Modifier.padding(bottom = 12.dp)
                        )
                        LazyColumn(
                            verticalArrangement = Arrangement.spacedBy(10.dp),
                            modifier = Modifier.fillMaxHeight()
                        ) {
                            item { MenuButton("Kontynuuj ostatni projekt") }
                            item { MenuButton("Nowy projekt") }
                            item { MenuButton("Otwórz projekt") }
                            item { MenuButton("Poradniki") }
                            item { MenuButton("Biblioteki") }
                            item { MenuButton("Ustawienia") }
                        }

                    }
                }
            }
        }
    }
}

@Composable
fun MenuButton(text: String, modifier: Modifier = Modifier, context: Context = LocalContext.current) {
    Button(
        onClick = {
            context.startActivity(Intent(context,MainAppActivity::class.java))
        },
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(Color(red = 182, green = 255, blue = 161)),
        modifier = Modifier
            .size(width = 280.dp, height = 60.dp)
            .shadow(5.dp, RoundedCornerShape(12.dp))
    ) {
        Text(
            text = text,
            color = Color.Black,
            fontSize = 19.sp,
            fontFamily = jetBrainsMono,
            textAlign = TextAlign.Center,
            modifier = modifier
        )
    }

}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun GreetingPreview2() {
    LogoInterpreterBetaTheme (
    ){
        Surface(
            modifier = Modifier.fillMaxHeight()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(top = 40.dp)
            ) {
                Image(
                    painterResource(id = R.drawable.logo),
                    contentDescription = "logo",
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .size(180.dp)
                    //.padding(top = 40.dp)
                )
                Text(
                    text = "LOGO IDE",
                    fontSize = 34.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 12.dp)
                )
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier.fillMaxHeight()
                ) {
                    item { MenuButton("Kontynuuj ostatni projekt") }
                    item { MenuButton("Nowy projekt") }
                    item { MenuButton("Otwórz projekt") }
                    item { MenuButton("Poradniki") }
                    item { MenuButton("Biblioteki") }
                    item { MenuButton("Ustawienia") }
                }

            }
        }

    }
}