package com.example.logointerpreterbeta.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.preferencesDataStoreFile
import com.example.logointerpreterbeta.data.repository.ConfigRepositoryImpl
import com.example.logointerpreterbeta.domain.repository.ConfigRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import jakarta.inject.Singleton
import dagger.hilt.components.SingletonComponent


//val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

@Module
@InstallIn(SingletonComponent::class)
object ConfigModule {

    @Provides
    @Singleton
    fun providePreferencesDataStore(
        @dagger.hilt.android.qualifiers.ApplicationContext context: Context
    ) = PreferenceDataStoreFactory.create {
        context.preferencesDataStoreFile("config.preferences_pb")
    }

//    @Provides
//    @Singleton
//    fun provideConfigRepository(
//        dataStore: DataStore<Preferences>
//    ): ConfigRepository = ConfigRepositoryImpl(dataStore)
}