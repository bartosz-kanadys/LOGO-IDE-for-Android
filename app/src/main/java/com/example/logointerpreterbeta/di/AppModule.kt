package com.example.logointerpreterbeta.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.logointerpreterbeta.data.repository.AndroidSystemThemeProvider
import com.example.logointerpreterbeta.data.repository.ThemeRepositoryImpl
import com.example.logointerpreterbeta.domain.repository.SystemThemeProvider
import com.example.logointerpreterbeta.domain.repository.ThemeRepository
import com.example.logointerpreterbeta.framework.ImageExportManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSystemThemeProvider(@ApplicationContext context: Context): SystemThemeProvider {
        return AndroidSystemThemeProvider(context)
    }

    @Provides
    @Singleton
    fun provideThemeRepository(
        dataStore: DataStore<Preferences>,
        systemThemeProvider: SystemThemeProvider
    ): ThemeRepository {
        return ThemeRepositoryImpl(dataStore, systemThemeProvider)
    }

    @Provides
    @Singleton
    fun provideExportManager(): ImageExportManager {
        return ImageExportManager()
    }

}