package com.example.logointerpreterbeta.di

import com.example.logointerpreterbeta.domain.interpreter.LogoDebugger
import com.example.logointerpreterbeta.domain.drawing.DrawingDelegate
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DebuggerModule {

    @Provides
    @Singleton
    fun provideLogoDebugger(delegate: DrawingDelegate): LogoDebugger {
        return LogoDebugger(delegate)
    }
}
