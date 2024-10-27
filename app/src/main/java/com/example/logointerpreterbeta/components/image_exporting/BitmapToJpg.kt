package com.example.logointerpreterbeta.components.image_exporting

import android.Manifest
import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream

private const val REQUEST_CODE_WRITE_EXTERNAL_STORAGE = 1

fun saveBitmapAsJpg(context: Context, bitmap: Bitmap, fileName: String): Boolean {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
        // Jeśli wersja Androida jest starsza niż 10, sprawdź uprawnienia
        if (!checkAndRequestPermissions(context)) {
            Toast.makeText(context, "Brak uprawnień do zapisu w galerii", Toast.LENGTH_SHORT).show()
            return false
        }
    }

    val outputStream: OutputStream?
    val success: Boolean

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        // Zapis na Androidzie 10 i nowszym
        val contentValues = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, "$fileName.jpg")
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
            put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_PICTURES + "/MyAppImages")
            put(MediaStore.Images.Media.IS_PENDING, 1)
        }

        val uri = context.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
        outputStream = uri?.let { context.contentResolver.openOutputStream(it) }
        success = outputStream?.let { bitmap.compress(Bitmap.CompressFormat.JPEG, 100, it) } == true

        contentValues.clear()
        contentValues.put(MediaStore.Images.Media.IS_PENDING, 0)
        if (uri != null) {
            context.contentResolver.update(uri, contentValues, null, null)
        }
    } else {
        // Zapis na Androidzie 9 i starszych
        val imagesDir = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "MyAppImages")
        if (!imagesDir.exists()) imagesDir.mkdirs()

        val imageFile = File(imagesDir, "$fileName.jpg")
        outputStream = FileOutputStream(imageFile)
        success = bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
    }

    outputStream?.flush()
    outputStream?.close()
    return success
}

private fun checkAndRequestPermissions(context: Context): Boolean {
    return if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
        // Jeśli uprawnienie nie zostało przyznane, poproś o nie
        ActivityCompat.requestPermissions(
            context as Activity,
            arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
            REQUEST_CODE_WRITE_EXTERNAL_STORAGE
        )
        false
    } else {
        true
    }
}

// Obsługa wyniku żądania uprawnień (w `Activity`)
fun onRequestPermissionsResult(requestCode: Int, grantResults: IntArray): Boolean {
    return if (requestCode == REQUEST_CODE_WRITE_EXTERNAL_STORAGE) {
        grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED
    } else {
        false
    }
}
