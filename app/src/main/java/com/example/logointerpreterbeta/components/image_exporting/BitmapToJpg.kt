package com.example.logointerpreterbeta.components.image_exporting

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.os.Environment
import android.provider.MediaStore
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream

fun saveBitmapAsJpg(context: Context, bitmap: Bitmap, fileName: String): Boolean {
    val contentValues = ContentValues().apply {
        put(MediaStore.Images.Media.DISPLAY_NAME, "$fileName.jpg") // Nazwa pliku
        put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg") // Typ MIME pliku
        put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_PICTURES + "/MyAppImages") // Ścieżka do katalogu
        put(MediaStore.Images.Media.IS_PENDING, 1) // Status w trakcie zapisu
    }

    // Tworzymy URI w MediaStore, aby zapisać plik
    val uri = context.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
    uri?.let {
        var outputStream: OutputStream? = null
        try {
            outputStream = context.contentResolver.openOutputStream(it)
            if (outputStream != null) {
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
            } // Zapisujemy bitmapę jako JPEG
            outputStream?.flush()
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        } finally {
            outputStream?.close()
        }

        // Aktualizujemy status na zakończony zapis
        contentValues.clear()
        contentValues.put(MediaStore.Images.Media.IS_PENDING, 0)
        context.contentResolver.update(uri, contentValues, null, null)
        return true
    }

    return false
}