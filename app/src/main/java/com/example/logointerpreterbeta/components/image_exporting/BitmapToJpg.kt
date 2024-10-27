package com.example.logointerpreterbeta.components.image_exporting

import android.content.Context
import android.graphics.Bitmap
import android.os.Environment
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

fun saveBitmapAsJpg(context: Context, bitmap: Bitmap, fileName: String): File? {
    // Tworzymy plik JPG
    val jpgFile = File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "$fileName.jpg")

    // Zapisujemy bitmapę jako JPG
    try {
        val outputStream = FileOutputStream(jpgFile)
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream) // 100 oznacza maksymalną jakość
        outputStream.flush()
        outputStream.close()
    } catch (e: IOException) {
        e.printStackTrace()
        return null
    }

    // Zwracamy plik JPG
    return jpgFile
}
