package com.example.logointerpreterbeta

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.logointerpreterbeta.ui.theme.LogoInterpreterBetaTheme

class StartScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LogoInterpreterBetaTheme {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Image(
                        painterResource(id = R.drawable.logo),
                        contentDescription = "logo",
                        contentScale = ContentScale.FillWidth,
                        modifier = Modifier.size(250.dp)
                    )
                    Text(
                        text = stringResource(R.string.app_name),
                        fontSize = 34.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 12.dp)
                    )

                    MenuButton("Kontynuuj ostatni projekt")
                    MenuButton("Nowy projekt")
                    MenuButton("Otwórz projekt")
                    MenuButton("Poradniki")
                    MenuButton("Biblioteki")
                    MenuButton("Ustawienia")
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
            modifier = modifier
        )
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview2() {
    LogoInterpreterBetaTheme {
        Column(
            Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Image(
                painterResource(id = R.drawable.logo),
                contentDescription = "logo",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .size(250.dp)
                    .padding(top = 20.dp)
            )
            Text(
                text = "LOGO IDE",
                fontSize = 34.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            MenuButton("Kontynuuj ostatni projekt")
            MenuButton("Nowy projekt")
            MenuButton("Otwórz projekt")
            MenuButton("Poradniki")
            MenuButton("Biblioteki")
            MenuButton("Ustawienia")
        }

    }
}