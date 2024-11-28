package com.example.logointerpreterbeta.models

import android.annotation.SuppressLint
import android.content.Context
import android.os.Parcelable
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.parcelize.Parcelize
import java.io.InputStreamReader

@Parcelize
data class Tutorial(
    val name: String,
    val description: String,
    val introduction: String,
    val paragraphs: List<Paragraph>
) : Parcelable

@Parcelize
data class Paragraph(
    val content: String,
    val code: String
) : Parcelable

@SuppressLint("DiscouragedApi")
fun readTutorialsFromRaw(context: Context): List<Tutorial> {
    // Otwórz plik JSON z folderu res/raw
    val resourceId = context.resources.getIdentifier("tutorials", "raw", context.packageName)
    val inputStream = context.resources.openRawResource(resourceId)

    // Przekształć InputStream do obiektu Tutorial
    val reader = InputStreamReader(inputStream)
    val gson = Gson()

    // Deserializacja JSON do listy obiektów Tutorial
    val listType = object : TypeToken<List<Tutorial>>() {}.type
    return gson.fromJson(reader, listType)
}