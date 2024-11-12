package com.example.logointerpreterbeta.functions.config

import android.content.Context
import android.util.Log
import com.example.logointerpreterbeta.Config
import com.google.gson.Gson
import java.io.File
import java.io.FileNotFoundException

fun createConfigFile(context: Context) {
    val configFile = File(context.getExternalFilesDir(null), "config.json")

    val config = Config()

    if (configFile.exists()) {
        return
    }

    val gson = Gson()
    val jsonString = gson.toJson(config)
    configFile.writeText(jsonString)

}

fun readLastModifiedProject(context: Context): String? {
    val configFile = File(context.getExternalFilesDir(null), "config.json")

    if (!configFile.exists()) {
        return null
    }

    return try {
        val jsonString = configFile.readText()

        val gson = Gson()
        val config = gson.fromJson(jsonString, Config::class.java)

        config.lastModifiedProject
    } catch (e: FileNotFoundException) {
        Log.e("dd", "dddd")
        null
    }
}

fun updateLastModifiedProjectJSON(context: Context, newProject: String) {
    val configFile = File(context.getExternalFilesDir(null), "config.json")

    val config = if (configFile.exists()) {
        val jsonString = configFile.readText()
        Gson().fromJson(jsonString, Config::class.java)
    } else {
        Config()
    }

    val updatedConfig = config.copy(lastModifiedProject = newProject)

    val gson = Gson()
    val updatedJsonString = gson.toJson(updatedConfig)
    configFile.writeText(updatedJsonString)
}