package com.example.logointerpreterbeta.components.image_exporting

import android.content.Context
import android.graphics.Bitmap
import android.graphics.pdf.PdfDocument
import android.os.Environment
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

fun saveBitmapAsPdf(context: Context, bitmap: Bitmap, fileName: String): File? {
    // Tworzymy instancję PdfDocument
    val pdfDocument = PdfDocument()

    // Tworzymy stronę PDF o rozmiarze bitmapy
    val pageInfo = PdfDocument.PageInfo.Builder(bitmap.width, bitmap.height, 1).create()
    val page = pdfDocument.startPage(pageInfo)

    // Rysujemy bitmapę na stronie PDF
    val canvas = page.canvas
    canvas.drawBitmap(bitmap, 0f, 0f, null)

    // Zamykanie strony i dodawanie jej do dokumentu
    pdfDocument.finishPage(page)

    // Tworzenie pliku do zapisania PDF
    val pdfFile = File(context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), "$fileName.pdf")

    // Zapisywanie pliku PDF
    try {
        val outputStream = FileOutputStream(pdfFile)
        pdfDocument.writeTo(outputStream)
        outputStream.close()
    } catch (e: IOException) {
        e.printStackTrace()
        return null
    } finally {
        pdfDocument.close()
    }

    // Zwracamy plik PDF
    return pdfFile
}
