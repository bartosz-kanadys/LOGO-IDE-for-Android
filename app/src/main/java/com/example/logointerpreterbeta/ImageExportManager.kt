package com.example.logointerpreterbeta

import android.Manifest
import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.pdf.PdfDocument
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream

class ImageExportManager {
    private val REQUEST_CODE_WRITE_EXTERNAL_STORAGE = 1

    fun saveBitmapAsJpg(context: Context, bitmap: Bitmap, fileName: String): Boolean {
        if (!checkAndRequestPermissions(context)) {
            Toast.makeText(context, "Brak uprawnień do zapisu w galerii", Toast.LENGTH_SHORT).show()
            return false
        }
        val outputStream: OutputStream?
        val success: Boolean
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            // Zapis na Androidzie 10 i nowszym
            val contentValues = ContentValues().apply {
                put(MediaStore.Images.Media.DISPLAY_NAME, "$fileName.jpg")
                put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
                put(
                    MediaStore.Images.Media.RELATIVE_PATH,
                    Environment.DIRECTORY_PICTURES + "/MyAppImages"
                )
                put(MediaStore.Images.Media.IS_PENDING, 1)
            }
            val uri = context.contentResolver.insert(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                contentValues
            )
            outputStream = uri?.let { context.contentResolver.openOutputStream(it) }
            success = outputStream?.let { bitmap.compress(Bitmap.CompressFormat.JPEG, 100, it) } == true
            contentValues.clear()
            contentValues.put(MediaStore.Images.Media.IS_PENDING, 0)
            if (uri != null) {
                context.contentResolver.update(uri, contentValues, null, null)
            }
        } else { // Zapis na Androidzie 9 i starszych
            val imagesDir = File(
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                "MyAppImages"
            )
            if (!imagesDir.exists()) imagesDir.mkdirs()
            val imageFile = File(imagesDir, "$fileName.jpg")
            outputStream = FileOutputStream(imageFile)
            success = bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
        }
        outputStream?.flush()
        outputStream?.close()
        return success
    }

    fun saveBitmapAsPdf(context: Context, bitmap: Bitmap, fileName: String): Boolean {
        val pdfDocument = PdfDocument()
        // Tworzymy stronę PDF o rozmiarze bitmapy
        val pageInfo = PdfDocument.PageInfo.Builder(bitmap.width, bitmap.height, 1).create()
        val page = pdfDocument.startPage(pageInfo)
        // Rysujemy bitmapę na stronie PDF
        val canvas = page.canvas
        canvas.drawBitmap(bitmap, 0f, 0f, null)
        pdfDocument.finishPage(page)
        // Definiujemy dane do zapisania w MediaStore
        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, "$fileName.pdf")
            put(MediaStore.MediaColumns.MIME_TYPE, "application/pdf")
            put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DOCUMENTS + "/MyAppPDFs")
        }
        // Zapisujemy plik w MediaStore
        val uri =
            context.contentResolver.insert(MediaStore.Files.getContentUri("external"), contentValues)
        uri?.let {
            var outputStream: OutputStream? = null
            try {
                outputStream = context.contentResolver.openOutputStream(it)
                pdfDocument.writeTo(outputStream!!)
            } catch (e: IOException) {
                e.printStackTrace()
                return false
            } finally {
                outputStream?.close()
                pdfDocument.close()
            }
            return true
        }
        pdfDocument.close()
        return false
    }

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
    }

    private fun checkAndRequestPermissions(context: Context): Boolean {
        var readMediaImagesPermission: String
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            readMediaImagesPermission = Manifest.permission.READ_MEDIA_IMAGES
        }
            else{
                readMediaImagesPermission = Manifest.permission.WRITE_EXTERNAL_STORAGE
        }
            return if (ContextCompat.checkSelfPermission(context, readMediaImagesPermission)
                != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    context as Activity,
                    arrayOf(readMediaImagesPermission),
                    REQUEST_CODE_WRITE_EXTERNAL_STORAGE
                )
                false
            }  else {
                true
        }
    }
}