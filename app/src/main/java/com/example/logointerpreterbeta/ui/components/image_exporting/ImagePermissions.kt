package com.example.logointerpreterbeta.ui.components.image_exporting

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

// Sprawdzenie i żądanie uprawnień
fun checkPermissions(context: Context) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        val readMediaImagesPermission = Manifest.permission.READ_MEDIA_IMAGES

        if (ContextCompat.checkSelfPermission(context, readMediaImagesPermission)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                context as Activity,
                arrayOf(readMediaImagesPermission),
                2137
            )
        }
    }
    // Obsługa dla starszych wersji Androida, jeśli jest potrzebna
}
