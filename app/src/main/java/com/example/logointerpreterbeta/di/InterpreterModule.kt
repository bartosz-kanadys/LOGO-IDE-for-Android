package com.example.logointerpreterbeta.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object InterpreterModule {

    @Provides
    fun provideIsDarkMode(): Boolean {
        return false
    }
}