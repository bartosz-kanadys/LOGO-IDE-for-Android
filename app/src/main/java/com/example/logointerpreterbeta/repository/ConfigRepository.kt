package com.example.logointerpreterbeta.repository

import android.content.Context
import android.util.Log
import com.example.logointerpreterbeta.models.Config
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File
import java.io.FileNotFoundException
import javax.inject.Inject

class ConfigRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {
    fun updateLastProjectJSON(newProjectName: String) {
        val configFile = File(context.getExternalFilesDir(null), "config.json")

        val config = if (configFile.exists()) {
            val jsonString = configFile.readText()
            Gson().fromJson(jsonString, Config::class.java)
        } else {
            Config()
        }

        val updatedConfig = config.copy(lastModifiedProject = newProjectName)

        val gson = Gson()
        val updatedJsonString = gson.toJson(updatedConfig)
        configFile.writeText(updatedJsonString)
    }

    fun readLastProjectJSON(): String? {
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
    fun updateThemeJSON(newTheme: String) {
        val configFile = File(context.getExternalFilesDir(null), "config.json")

        val config = if (configFile.exists()) {
            val jsonString = configFile.readText()
            Gson().fromJson(jsonString, Config::class.java)
        } else {
            Config()
        }

        val updatedConfig = config.copy(currentTheme = newTheme)

        val gson = Gson()
        val updatedJsonString = gson.toJson(updatedConfig)
        configFile.writeText(updatedJsonString)
    }
    fun updateFontJSON(newFont: String) {
        val configFile = File(context.getExternalFilesDir(null), "config.json")

        val config = if (configFile.exists()) {
            val jsonString = configFile.readText()
            Gson().fromJson(jsonString, Config::class.java)
        } else {
            Config()
        }

        val updatedConfig = config.copy(currentFont = newFont)

        val gson = Gson()
        val updatedJsonString = gson.toJson(updatedConfig)
        configFile.writeText(updatedJsonString)
    }
    fun updateFontSizeJSON(newFontSize: Int) {
        val configFile = File(context.getExternalFilesDir(null), "config.json")

        val config = if (configFile.exists()) {
            val jsonString = configFile.readText()
            Gson().fromJson(jsonString, Config::class.java)
        } else {
            Config()
        }

        val updatedConfig = config.copy(currentFontSize = newFontSize)

        val gson = Gson()
        val updatedJsonString = gson.toJson(updatedConfig)
        configFile.writeText(updatedJsonString)
    }
    fun readSettingsJSON(): Config? {
        val configFile = File(context.getExternalFilesDir(null), "config.json")

        if (!configFile.exists()) {
            return null
        }

        return try {
            val jsonString = configFile.readText()

            val gson = Gson()
            val config = gson.fromJson(jsonString, Config::class.java)

            config
        } catch (e: FileNotFoundException) {
            Log.e("dd", "dddd")
            null
        }
    }
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
}