package com.example.logointerpreterbeta.di

import com.example.logointerpreterbeta.domain.repository.ProjectRepository
import com.example.logointerpreterbeta.domain.repository.ConfigRepository
import com.example.logointerpreterbeta.domain.repository.FileRepository
import com.example.logointerpreterbeta.data.repository.ProjectRepositoryImpl
import com.example.logointerpreterbeta.data.repository.ConfigRepositoryImpl
import com.example.logointerpreterbeta.data.repository.FileRepositoryImpl
import com.example.logointerpreterbeta.data.repository.LibraryRepositoryImpl
import com.example.logointerpreterbeta.data.repository.SessionRepositoryImpl
import com.example.logointerpreterbeta.domain.repository.LibraryRepository
import com.example.logointerpreterbeta.domain.repository.SessionRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindProjectRepository(
        impl: ProjectRepositoryImpl
    ): ProjectRepository

    @Binds
    @Singleton
    abstract fun bindConfigRepository(
        impl: ConfigRepositoryImpl
    ): ConfigRepository

    @Binds
    @Singleton
    abstract fun bindFileRepository(
        impl: FileRepositoryImpl
    ): FileRepository

    @Binds
    @Singleton
    abstract fun bindLibraryRepository(
        impl: LibraryRepositoryImpl
    ): LibraryRepository

    @Binds
    @Singleton
    abstract fun bindSessionRepository(
        impl: SessionRepositoryImpl
    ): SessionRepository
}
