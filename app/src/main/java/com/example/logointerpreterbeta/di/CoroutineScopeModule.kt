package com.example.logointerpreterbeta.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
object CoroutineScopeModule {
    @Singleton
    @Provides
    fun provideCoroutineScope(): CoroutineScope {
        return CoroutineScope(Dispatchers.Default)
    }
}