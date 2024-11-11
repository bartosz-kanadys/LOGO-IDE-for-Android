package com.example.logointerpreterbeta.ui.components.image_exporting

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.pdf.PdfDocument
import android.os.Environment
import android.provider.MediaStore
import java.io.IOException
import java.io.OutputStream

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