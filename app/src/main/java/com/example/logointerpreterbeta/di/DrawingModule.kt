package com.example.logointerpreterbeta.di

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Bitmap.createBitmap
import com.example.logointerpreterbeta.domain.drawing.DrawingDelegate
import com.example.logointerpreterbeta.ui.drawing.AndroidDrawingDelegate
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DrawingModule {

    @Provides
    fun provideDrawingDelegate(@ApplicationContext context: Context): DrawingDelegate {
        val bitmap = createBitmap(1000, 1000, Bitmap.Config.ARGB_8888)
        return AndroidDrawingDelegate(bitmap, context)
    }

    @Provides
    @Singleton
    fun provideAndroidDrawingDelegate(@ApplicationContext context: Context): AndroidDrawingDelegate {
        val bitmap = createBitmap(1000, 1000, Bitmap.Config.ARGB_8888)
        return AndroidDrawingDelegate(bitmap,context)
    }
}
