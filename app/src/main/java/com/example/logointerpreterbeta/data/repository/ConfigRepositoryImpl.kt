package com.example.logointerpreterbeta.data.repository

import android.content.Context
import android.util.Log
import com.example.logointerpreterbeta.domain.models.Config
import com.example.logointerpreterbeta.domain.repository.ConfigRepository
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File
import java.io.FileNotFoundException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ConfigRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context
): ConfigRepository {
    override fun updateLastProjectJSON(newProjectName: String) {
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

    override fun readLastProjectJSON(): String? {
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
    override fun updateThemeJSON(newTheme: String) {
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
    override fun updateFontJSON(newFont: String) {
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
    override fun updateFontSizeJSON(newFontSize: Int) {
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
    override fun updateShowSuggestionsJSON(newShowSuggestions: Boolean) {
        val configFile = File(context.getExternalFilesDir(null), "config.json")

        val config = if (configFile.exists()) {
            val jsonString = configFile.readText()
            Gson().fromJson(jsonString, Config::class.java)
        } else {
            Config()
        }

        val updatedConfig = config.copy(showSuggestions = newShowSuggestions)

        val gson = Gson()
        val updatedJsonString = gson.toJson(updatedConfig)
        configFile.writeText(updatedJsonString)
    }
    override fun updateUseAutocorrectJSON(newUseAutocorrect: Boolean) {
        val configFile = File(context.getExternalFilesDir(null), "config.json")

        val config = if (configFile.exists()) {
            val jsonString = configFile.readText()
            Gson().fromJson(jsonString, Config::class.java)
        } else {
            Config()
        }

        val updatedConfig = config.copy(useAutocorrect = newUseAutocorrect)

        val gson = Gson()
        val updatedJsonString = gson.toJson(updatedConfig)
        configFile.writeText(updatedJsonString)
    }
    override fun readSettingsJSON(): Config? {
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
    override fun createConfigFile(context: Context) {
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