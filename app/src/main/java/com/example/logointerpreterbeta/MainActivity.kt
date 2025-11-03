package com.example.logointerpreterbeta

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import com.example.logointerpreterbeta.ui.drawing.AndroidDrawingDelegate
import com.example.logointerpreterbeta.ui.navigation.AppNavHost
import dagger.hilt.android.AndroidEntryPoint
import jakarta.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    @Inject
    lateinit var drawingDelegate: AndroidDrawingDelegate

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppNavHost(drawingDelegate = drawingDelegate)
        }
    }
}