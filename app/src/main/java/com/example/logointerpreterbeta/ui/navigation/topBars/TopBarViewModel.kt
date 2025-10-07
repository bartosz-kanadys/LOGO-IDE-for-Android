package com.example.logointerpreterbeta.ui.navigation.topBars

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.logointerpreterbeta.R
import com.example.logointerpreterbeta.framework.ImageExportManager
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.io.File
import java.io.FileOutputStream
import java.io.InputStreamReader
import java.io.OutputStreamWriter

@HiltViewModel
class TopBarViewModel @Inject constructor(
    private val exportManager: ImageExportManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(InterpreterTopBarUiState())
    val uiState: StateFlow<InterpreterTopBarUiState> = _uiState

    fun onSaveToFileRequested(content: String) {
        _uiState.update { it.copy(requestSave = content) }
    }

    fun onImageExportJpgRequested(context: Context, bitmap: Bitmap) {
        viewModelScope.launch {
            exportManager.checkPermissions(context)
            val success = exportManager.saveBitmapAsJpg(context,bitmap, "Logo_${System.currentTimeMillis()}")
            _uiState.update { it.copy(showToast = if (success) R.string.saved_jpg else R.string.not_able_to_save_jpg) }
        }
    }

    fun onImageExportPdfRequested(context: Context, bitmap: Bitmap) {
        viewModelScope.launch {
            exportManager.checkPermissions(context)
            val success = exportManager.saveBitmapAsPdf(context,bitmap, "Logo_${System.currentTimeMillis()}")
            _uiState.update { it.copy(showToast = if (success) R.string.saved_pdf else R.string.not_able_to_save_pdf) }
        }
    }

    fun onLoadFileRequested(context: Context, uri: Uri): String {
        val stringBuilder = StringBuilder()

        viewModelScope.launch {
            val inputStream = context.contentResolver.openInputStream(uri)
            val reader = BufferedReader(InputStreamReader(inputStream))
            reader.use {
                it.forEachLine { line ->
                    stringBuilder.appendLine(line)
                }
            }
            _uiState.update { it.copy(showToast =  R.string.file_readed_toast) }
        }
        return stringBuilder.toString()
    }

    fun onWriteFileRequested(context: Context, uri: Uri, code: String, fileName: String) {
        context.contentResolver.openOutputStream(uri)?.use { outputStream ->
            OutputStreamWriter(outputStream).use { writer ->
                writer.write(code)
            }
            _uiState.update { it.copy(showToast =  R.string.file_saved_info) }
        }
    }

    fun clearOneTimeEvents() {
        _uiState.update { it.copy(requestSave = null, showToast = null) }
    }

    fun shareImage(context: Context, bitmap: Bitmap) {
        try {
            // Step 1: Save bitmap to file
            val file = File(context.cacheDir, "shared_image.png")
            FileOutputStream(file).use { fos ->
                bitmap.compress(
                    Bitmap.CompressFormat.PNG,
                    100,
                    fos
                )
            }

            // Step 2: Get URI from FileProvider
            val uri = FileProvider.getUriForFile(
                context,
                "${context.packageName}.fileprovider",
                file
            )

            // Step 3: Create intent to share
            val shareIntent = Intent().apply {
                action = Intent.ACTION_SEND
                type = "image/png"
                putExtra(Intent.EXTRA_STREAM, uri)
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION) // Allow reading URI
            }

            // Run intent
            context.startActivity(
                Intent.createChooser(
                    shareIntent,
                    "Share image via"
                )
            )
        } catch (e: Exception) {
            e.printStackTrace()
            _uiState.update { it.copy(showToast =  R.string.failed_to_share_image) }
        }
    }
    fun shareCode(code: String, context: Context){
        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            type = "text/plain" // Typ MIME dla tekstu
            putExtra(
                Intent.EXTRA_TEXT,
                code
            ) // Text to share
        }

        // create chooser and run Intent
        context.startActivity(
            Intent.createChooser(
                shareIntent,
                "Share via"
            )
        )
    }
}

data class InterpreterTopBarUiState(
    val requestSave: String? = null,
    val showToast: Int? = null
)

