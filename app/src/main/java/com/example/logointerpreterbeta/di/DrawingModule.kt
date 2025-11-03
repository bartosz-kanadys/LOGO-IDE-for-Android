package com.example.logointerpreterbeta.di

import android.content.Context
import com.example.logointerpreterbeta.domain.drawing.DrawingDelegate
import com.example.logointerpreterbeta.domain.repository.ThemeRepository
import com.example.logointerpreterbeta.ui.drawing.AndroidDrawingDelegate
import com.example.logointerpreterbeta.ui.drawing.UIDrawingDelegate
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DrawingModule {
    @Provides
    @Singleton
    fun provideAndroidDrawingDelegate(@ApplicationContext context: Context, themeRepository: ThemeRepository): AndroidDrawingDelegate {
//        val isDarkMode = themeRepository.isDarkTheme()
        return AndroidDrawingDelegate(1000, 1000, context, false)
    }

    @Provides
    @Singleton
    fun provideDrawingDelegate(delegate: AndroidDrawingDelegate): DrawingDelegate {
        return delegate
    }

    @Provides
    @Singleton
    fun provideUIDrawingDelegate(delegate: AndroidDrawingDelegate): UIDrawingDelegate {
        return delegate
    }
}