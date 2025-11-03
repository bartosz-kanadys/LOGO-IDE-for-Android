package com.example.logointerpreterbeta.domain.usecase.project

import com.example.logointerpreterbeta.domain.repository.ProjectRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetProjectsMapUseCase @Inject constructor(
    private val projectRepository: ProjectRepository
) {
    operator fun invoke(): Flow<Map<String, String>> {
        return projectRepository.getProjectsMap()
    }
}